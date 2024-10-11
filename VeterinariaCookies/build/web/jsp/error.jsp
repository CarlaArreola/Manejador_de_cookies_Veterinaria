<%-- 
    Document   : error
    Created on : 30/09/2024, 09:14:18 PM
    Author     : carla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
<body>
    <div class="error-container">
        <h1>Oops, something went wrong!</h1>
        <p><%= request.getAttribute("error") %></p>
        <button class="back-button" onclick="history.back()">Go Back</button>
    </div>
</body>
</html>