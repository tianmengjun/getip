##通过修改nginx及tomcat配置使用getRemoteAddr()方法获取客户端IP
在web开发中获取用户的真实IP时，Java提供request.getRemoteAddr()方法；然而在web在服务器与客户端之间增加中间层(request路经的代理或负载均衡器)时,就不能用该方法获取到用户的真实IP,获取到的是中间层的地址。
解决的办法有多种，在此介绍使用nginx和tomcat做反向代理、负载均衡情况下，通过修改相关配置实现getRemoteAddr()获取IP的方法。
1.修改nginx的nginx.conf配置文件
在使用了nginx做反向代理时，nginx是可以获取客户端的真实IP，因此需要给nginx配置一些HTTP Header，将信息告诉服务器tomcat。
在nginx.conf配置的location 下添加如下配置:
{
proxy_set_header Host $host;
    proxy_set_header X-Real-IP $remote_addr;
    proxy_set_header REMOTE-HOST $remote_addr;
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
}
例：location / {
　　　proxy_pass http://127.0.0.1:8080;
　　　proxy_set_header Host $host;
      proxy_set_header X-Real-IP $remote_addr;
      proxy_set_header REMOTE-HOST $remote_addr;
      proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
	   proxy_set_header X-Forwarded-Proto $scheme;
}
解释:
Host：包含客户端真实的域名和端口号；
X-Real-IP：表示客户端真实的IP；
X-Forwarded-For：这个Header和X-Real-IP类似，但它在多层代理时会包含真实客户端及中间每个代理服务器的IP。
X-Forwarded-Proto：表示客户端真实的协议（http还是https）；
2.修改tomcat的server.xml配置文件
在tomcat的RemoteIpValve类中提供了mod_remoteip端口获取代理或负载平衡器的请求头信息（例如:“X-Forwarded-For”将IP地址列表替换为客户端IP地址和主机名）。
在<Host>标签下添加如下配置：
<Valve className="org.apache.catalina.valves.RemoteIpValve" 
internalProxies="192\.168\.0\.10|192\.168\.0\.11"        
        remoteIpHeader="x-forwarded-for" 
        proxiesHeader="x-forwarded-by" 
        protocolHeader="x-forwarded-proto"/>
注释:
remoteIpHeader:读取的Http Header的内容，其中包含请求端的IP地址；
internalProxies:正则表达式匹配内部代理的IP地址。如果IP出现在remoteIpHeader值中，它们将被信任并且不会出现在proxiesHeader值中；
proxiesHeader:由此valve创建的HTTP Header名称，用于保存已在传入中处理的代理列表 remoteIpHeader;
protocolHeader:读取请求端的协议(http/https)。
修改访问日志配置如下:
<Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
          prefix="localhost_access_log" suffix=".txt"
          pattern="x-forwarded-for: % {x-forwarded-for}i %h %t &quot;%r&quot; %s %b"/>
即可打印如下访问日志:
x-forwarded-for : - 113.200.107.138 [24/Jan/2018:10:00:10 +0800] "GET / HTTP/1.1" 200 175,其中的113.200.107.138就是客户端真实IP。


相关文档：https://tomcat.apache.org/tomcat-9.0-doc/api/org/apache/catalina/valves/RemoteIpValve.html
