<%-- 
    Document   : verificar
    Created on : 9/10/2024, 08:27:04 PM
    Author     : carla
--%>

<%@page import="org.json.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verificar Animalito</title>
    </head>
    <body>
        <h1>Verificar Datos del Animalito</h1>

        <%
            Cookie[] cookies = request.getCookies();
            String datosVeterinaria = "";
            String tipoA  = "";
            String nombreA  = "";
            String diaLLegada   = "";
            String HoraLLegada  = "";
            String CostoConsulta  = "";

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("datosVeterinaria")) {
                        datosVeterinaria = java.net.URLDecoder.decode(cookie.getValue(), "UTF-8");
                        break;
                    }
                }
            }

            if (!datosVeterinaria.isEmpty()) {
                try {
                    JSONObject json = new JSONObject(datosVeterinaria);
                    tipoA  = json.optString("tipoA", "");
                    nombreA  = json.optString("nombreA", "");
                    diaLLegada  = json.optString("diaLLegada", "");
                    HoraLLegada    = json.optString("HoraLLegada", "");
                    CostoConsulta = json.optString("CostoConsulta", "");
                } catch (Exception e) {
                    out.println("<p>Error al procesar los datos de la cookie: " + e.getMessage() + "</p>");
                }
            }
        %>

        <form action="${pageContext.request.contextPath}/guardarServlet" method="post">
            <label for="nombre">Tipo de Animal:</label>
            <input type="text" id="tipoA" name="tipoA" value="<%= tipoA %>" required><br><br>

            <label for="descripcion">Nombre:</label>
            <input type="text" id="nombreA" name="nombreA" value="<%= nombreA %>" required><br><br>

            <label for="fecha">Día:</label>
            <input type="date" id="diaLLegada" name="diaLLegada" value="<%= diaLLegada %>" required><br><br>

            <label for="hora">Hora:</label>
            <input type="time" id="HoraLLegada" name="HoraLLegada" value="<%= HoraLLegada %>" required><br><br>

            <label for="precio">Costo de la consulta:</label>
            <input type="number" id="CostoConsulta" name="CostoConsulta" step="0.01" value="<%= CostoConsulta %>" required><br><br>

            <input type="submit" value="Guardar">
        </form>
    </body>
</html>

