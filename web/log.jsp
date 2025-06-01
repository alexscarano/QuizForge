<%-- 
    Document   : log
    Created on : May 31, 2025, 1:53:30 PM
    Author     : alexa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <%= web.AppListener.initializeLog.replace("\n", "<br>") %>
    </body>
</html>
