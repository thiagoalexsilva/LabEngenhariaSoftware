<%-- 
    Document   : newjsp
    Created on : Oct 2, 2016, 11:21:49 PM
    Author     : Gustavo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            out.println(session.getAttribute("beyblade"));
            %>
    </body>
</html>
