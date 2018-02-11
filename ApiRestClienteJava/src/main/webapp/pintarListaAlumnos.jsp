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
        <script>
            
            function cargarAlumno(id,nombre,fecha,mayor){
                document.getElementById("id").value=id;
                document.getElementById("nombre").value=nombre;
                document.getElementById("fecha").value=fecha; 
                document.getElementById("mayor").checked=mayor;
            }   
            
            </script>
    </head>
    <body>
        <h1>ALUMNOS</h1>
        
            <br>
        <form action="${pageContext.request.contextPath}/alumnos?op=op" method="get">
            <label></label>
            <input type="hidden" id="id" name="idalumno"/>
            <label><b>Nombre:</b></label>
            <input type="text" id="nombre" name="nombre"/>
            <label><b>Fecha:</b></label>
            <input type="text" id="fecha" name="fecha"/>
            <label><b>Mayor de edad:</b></label>
            <input type="checkbox" id="mayor" name="mayor"><br>
            <div style="border-bottom: 2px solid;padding: 15px"></div>
            <div style="padding: 15px;margin: 15px;">
                <button name="op" id="op" value="GETALL">Mostrar</button>
                <button name="op" id="op" value="INSERT">Insertar</button>
                <button name="op" id="op" value="UPDATE">Actualizar</button>
                <button name="op" id="op" value="DELETE">Borrar</button>
            </div>
        </form>
            <table border="1">
            <c:out value="${mensaje}"></c:out>
        <c:forEach items="${alumnos}" var="alumno">  
            <tr>
                <td>
                    <input type="button" value="cargar ${alumno.id}" 
                           onclick="cargarAlumno('${alumno.id}',
                           '${fn:escapeXml(fn:replace(alumno.nombre,"'", "\\'"))}',
                           '<fmt:formatDate value="${alumno.fecha_nacimiento}" pattern="dd-MM-yyyy"/>',
                           ${alumno.mayor_edad});"/>
                </td> 
                <td>
                    ${alumno.nombre}
                </td>
                <td>
                    <fmt:formatDate value="${alumno.fecha_nacimiento}" pattern="dd-MM-yyyy"/>
                </td>
                <td>
                    <input type="checkbox" <c:if test="${alumno.mayor_edad}" >checked</c:if> />
                </td>
            </tr>
        </c:forEach>  
        </table>
    </body>
</html>
