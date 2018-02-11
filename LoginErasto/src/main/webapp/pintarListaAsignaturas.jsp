<%-- 
    @author Erasto Rojas Sánchez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="utils.Constantes" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            
            function cargarAsignatura(id,nombre,curso,ciclo){
                document.getElementById("id").value=id;
                document.getElementById("nombre").value=nombre;
                document.getElementById("curso").value=curso; 
                document.getElementById("ciclo").value=ciclo;
            }   
            
            </script>
    </head>
    <body>
        <h1>ASIGNATURAS</h1>
        <table border="1">
            <p><c:out value="${mensaje}"></c:out></p>
        <c:forEach items="${asignaturas}" var="asignatura">  
            <tr>
                <td>
                    <input type="button" value="cargar ${asignatura.id}" 
                           onclick="cargarAsignatura('${asignatura.id}','${asignatura.nombre}',
                           '${asignatura.curso}','${asignatura.ciclo}'
                           );"/>
                </td> 
                <td>
                    ${asignatura.nombre}
                </td>
                <td>
                    ${asignatura.curso}
                </td>
                <td>
                    ${asignatura.ciclo}
                </td>
            </tr>
        </c:forEach>  
        </table>
            <br>
        <form action="${pageContext.request.contextPath}/secure/asignaturas?op=op" method="get">
            <label></label>
            <input type="hidden" id="id" name="idasignatura"/>
            <label><b>Nombre:</b></label>
            <input type="text" id="nombre" name="nombre"/>
            <label><b>Curso:</b></label>
            <input type="text" id="curso" name="curso"/>
            <label><b>Ciclo:</b></label>
            <input type="text" id="ciclo" name="ciclo"/>
            <br>
            <div style="border-bottom: 2px solid;padding: 15px"></div>
            <div style="padding: 15px;margin: 15px;">
                <button name="op" id="op" value="GETALL">Mostrar</button>
                <button name="op" id="op" value="INSERT">Insertar</button>
                <button name="op" id="op" value="UPDATE">Actualizar</button>
                <button name="op" id="op" value="DELETE">Borrar</button>
            </div>
        </form>
            <br><br>
                <a href="http://localhost:8080/basedatos1Erasto/login?op=CERRAR">Cerrar</a>
            
    </body>
</html>
