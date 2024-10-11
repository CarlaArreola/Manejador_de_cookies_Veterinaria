<%-- 
    Document   : registro
    Created on : 30/09/2024, 09:14:44 PM
    Author     : carla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registro de Animalitos</title>
</head>
<body>
    <h1>Registrar Animalitos:</h1>
    <br>
    <form action="../registrarServlet" method="POST">
        <label for="nombre">Tipo de Animal:</label>
        <input type="text" id="tipoA" name="tipoA" required><br><br>
        
        <label for="descripcion">Nombre:</label>
        <textarea id="nombreA" name="nombreA" rows="4" cols="50" required></textarea><br><br>
        
        <label for="fecha">Día:</label>
        <input type="date" id="diaLLegada" name="diaLLegada" required><br><br>
        
        <label for="hora">Hora:</label>
        <input type="time" id="HoraLLegada" name="HoraLLegada" required><br><br>
        
        <label for="precio">Costo de la consulta:</label>
        <input type="number" id="CostoConsulta" name="CostoConsulta" required><br><br>
        
        <input type="submit" value="Registrar Animalito">
    </form>
</body>
</html>

