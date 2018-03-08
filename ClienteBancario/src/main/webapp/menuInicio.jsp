<%-- 
    Document   : menuInicio
    Created on : 28-feb-2018, 9:46:07
    Author     : erasto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="es">
    <head>
    <title>IES Enrique Tierno Galvan</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/cssMenuInicio.css" type="text/css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

    </head>
    <body>
        <div class="jumbotron jumbotron-fluid headera">
            <div class="container">
              <div class="row">
                <div class="col-12 col-md-4 col-lg-2 headera">
                  <img alt="logo-boy" class="img-responsive" src="img/logo-boy-2.png">
                </div>
                <div class="col-12 col-md-5 col-lg-7 headera">
                    <h1>Aplicacion bancaria</h1>
                    <p>Menú del empleado</p>
                </div>
                <div class="col-12 col-md-3 headera">
                    <p>Empleado:</p>
                    <div style="color:blue;"><c:out value="${usuario}"></c:out></div><br>
                    
                    <a  href="#">Cerrar sesión</a>
                </div>
              </div> 
            </div>
        </div>
        <div class="container">
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#">Apertura Cuentas</a>
                        <a class="navbar-brand" href="#">Ingresos y Reintegros</a>
                        <a class="navbar-brand" href="#">Movimientos</a>
                        <a class="navbar-brand" href="#">Cerrar Cuentas</a>
                    </div>
                </div>
            </nav>
        </div>
  
<!--col-sm-4 col-md-4 col-lg-4 col-xl-4"--> <!--offset-xl-5-->
	</body>

</html>