<%-- 
    @author Erasto Rojas Sánchez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="utils.Constantes" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <body>
        <c:out value="${mensaje}"></c:out>
        <form action="${pageContext.request.contextPath}/login?op=op" method="get">
            <span><h2>REGISTRAR</h2></span><br>
            <label><b>Nombre:</b></label>
            <input type="text" id="nombre" name="nombre" required/><br>
            <label><b>Email:</b></label>
            <input type="text" id="email" name="email" required/><br>
            <label><b>Password:</b></label>
            <input type="text" id="pwd" name="pwd" required/>
            <div style="padding: 15px;margin: 15px;">
                <button name="op" id="op" value="REGISTRAR">Registrar</button>
            </div>
            <div style="border-bottom: 2px solid;padding: 15px"></div>
        </form>
        <form action="${pageContext.request.contextPath}/login?op=op" method="get">
            <span><h2>LOGIN</h2></span><br>
            <label><b>Nombre:</b></label>
            <input type="text" id="nombre" name="nombre" required/><br>           
            <label><b>Password:</b></label>
            <input type="text" id="pwd" name="pwd" required/>
            <div style="padding: 15px;margin: 15px;">
                <button name="op" id="op" value="LOGIN">Login</button>
            </div>
            <div style="border-bottom: 2px solid;padding: 15px"></div>
        </form>
        
            <a href="http://localhost:8080/basedatos1Erasto/secure/alumnos">Alumnos</a>
            <a href="http://localhost:8080/basedatos1Erasto/secure/asignaturas">Asignaturas</a>
            <a href="http://localhost:8080/basedatos1Erasto/secure/notas">Notas</a>
            <br><br>
            <a href="http://localhost:8080/basedatos1Erasto/login?op=CERRAR">Cerrar</a>
    </body>
</html>
