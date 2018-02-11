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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
        <div class="container">
        <h1>Clientes</h1>
        <table class="table table-striped">
            
        <c:forEach items="${clientes}" var="cliente">  
            <tr>
                <td>
                    
                </td> 
                <td>
                    ${cliente.cl_nom}
                </td>
                <td>
                    <fmt:formatDate value="${cliente.cl_fna}" pattern="dd-MM-yyyy"/>
                </td>
                <td>
                    
                </td>
            </tr>
        </c:forEach>  
        </table>
        </div>
           
        
    </body>
</html>