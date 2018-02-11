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
            
            function cargarAlumno(id,nombre){
                document.getElementById("idAlumno").value = id;
                document.getElementById("nAlumno").value = nombre;
            }
            
            function cargarAsignatura(id,asignatura){
                document.getElementById("idAsig").value = id;
                document.getElementById("nAsig").value = asignatura;
            } 
           
            </script>
    </head>
    <body>
        <table>
            <tr>
                <td><h2>ALUMNOS</h2></td>
                <td><h2>ASIGNATURAS</h2></td>
            </tr>
            <tr>
                <td>
                    <select id="al" onchange="cargarAlumno(this.value, this.options[this.selectedIndex].innerHTML)">
                        <option disabled selected value> -- selecciona una opción -- </option>
                        <c:forEach items="${alumnos}" var="alumno"> 
                            <option value='${alumno.id}'>${alumno.nombre}</option>
                        </c:forEach> 
                    </select>
                </td>
                <td>
                    <select id="as" onchange="cargarAsignatura(this.value, this.options[this.selectedIndex].innerHTML)">
                        <option disabled selected value> -- selecciona una opción -- </option>
                        <c:forEach items="${asignaturas}" var="asignatura"> 
                            <option value='${asignatura.id}'>${asignatura.nombre}</option>
                        </c:forEach> 
                    </select>
                </td>
            </tr>
        </table>          
        
            <br>
        <form action="${pageContext.request.contextPath}/secure/notas?op=op" method="get">
            <label></label>
            <label><b>Nombre:</b></label>
            <input type="hidden" id="idAlumno" name="idAlumno" size="1" value="${idAlu}">
            <input type="text" name="nombreAlu" id="nAlumno" value="${nomAlu}">
            <label><b>Modulo:</b></label>
            <input type="hidden" value="${idAsig}" id="idAsig" name="idAsig"/>
            <input type="text" value="${nAsig}" name="nombreAsig" id="nAsig">
            <label><b>Nota:</b></label>
            <input type="text" value="${nota.nota}" id="nota" name="nota" size="1">
            <div style="border-bottom: 2px solid;padding: 15px"></div>
            <div style="padding: 15px;margin: 15px;">
                <button name="op" id="op" value="cargar">Cargar</button>
                <button name="op" id="op" value="guardar" onclick="">Guardar</button>
                <button name="op" id="op" value="borrar">Borrar</button>  
            </div>
        </form>
            <br><br>
                <a href="http://localhost:8080/basedatos1Erasto/login?op=CERRAR">Cerrar</a>
        <c:out value="${mensaje}"></c:out>
    </body>
</html>
