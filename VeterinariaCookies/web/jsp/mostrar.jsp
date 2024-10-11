<%-- 
    Document   : mostrar
    Created on : 9/10/2024, 08:26:53 PM
    Author     : carla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingrese los datos del Animalito:</title>
        <script>
            function eliminarCookie() {
                document.cookie = "datosVeterinaria=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
                alert('Cookie eliminada');
                window.location.href = "./index.jsp";
            }
        </script>
    </head>
    <body>
        <br>
    <c:if test="${not empty nombreA}">
        <p><strong>Tipo de Animal: </strong> ${tipoA}</p>
        <p><strong>Nombre: </strong> ${nombreA}</p>
        <p><strong>Día: </strong> ${diaLLegada}</p>
        <p><strong>Hora: </strong> ${HoraLLegada}</p>
        <p><strong>Costo de la consulta: </strong> ${CostoConsulta}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/guardarServlet" method="get">
        <input type="submit" value="Realizar Consulta">
    </form>

    <button type="button" onclick="eliminarCookie()">Eliminar Cookie</button>

</body>
</html>
