<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Starting App...</title>
<script>

var URL = "kaonkaon://product.html#malvern;6";
var MARKET = "http://a.app.qq.com/o/simple.jsp?pkgname=com.tencent.android.qqdownloader";
var ITUNES = "itms://itunes.apple.com/us/app/kaon-interactive-3d-product/id525051513?mt=8&uo=4";
var QR = "http://goo.gl/gz07g"; // this should be a shortened link back to this page

function onLoad() {

    if (navigator.userAgent.match(/Android/)) {

        if (navigator.userAgent.match(/Chrome/)) {

            // Jelly Bean with Chrome browser
            setTimeout(function() {
                if (!document.webkitHidden)
                    window.location = MARKET;
            }, 1000);

            window.location = URL;

        } else {

            // Older Android browser
            var iframe = document.createElement("iframe");
            iframe.style.border = "none";
            iframe.style.width = "1px";
            iframe.style.height = "1px";
            var t = setTimeout(function() {
                window.location = MARKET;
            }, 1000);
            iframe.onload = function () { clearTimeout(t) };
            iframe.src = URL;
            document.body.appendChild(iframe);

        }

     } else if (navigator.userAgent.match(/iPhone|iPad|iPod/)) {

         // IOS
         setTimeout(function() {
             if (!document.webkitHidden)
                 window.location = ITUNES;
         }, 25);

         window.location = URL;

     } else {

         // Not mobile
         var img = document.createElement("img");
         img.src = "https://chart.googleapis.com/chart?chs=300x300&cht=qr&chl="+encodeURIComponent(QR);
         document.body.appendChild(img);
     }
}
</script>
  </head>
  <body onload="onLoad()">
  </body>
</html>