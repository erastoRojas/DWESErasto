<%-- 
    Document   : menuInicio
    Created on : 08-mar-2018, 12:33:56
    Author     : erasto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="utils.Constantes" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <title>IES Enrique Tierno Galvan</title>
    <meta charset="UTF-8">
    <meta name="author" content="Erasto Rojas">
    <meta name="keywords" content="instituto,enrique,tierno,galvan">
    <meta name="description" content="Proyecto Aplicacion Bancaria"> 
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet/less" type="text/css" href="/AplicacionBancaria/less/lessMenuInicio.less" />
    <!--<link rel="stylesheet" href="css/estilos.css" type="text/css"> -->
    
    
    <script src="//cdnjs.cloudflare.com/ajax/libs/less.js/3.0.0/less.min.js" ></script>        
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
    <script src="/AplicacionBancaria/Javascript/menuInicio.js"></script>
</head>
        
<body onload="cargar('${nombre}');">
    <div class="jumbotron jumbotron-fluid headera">
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-4 col-lg-3 headera">
                    <img alt="logo-boy" class="img-responsive" src="/AplicacionBancaria/img/logo-boy-2.png" id="imgInicio">
                </div>
                <div class="col-12 col-md-8 col-lg-9 texto-header">
                    <h1>Aplicación Bancaria</h1>
                    <p class="parrafo-header">Bienvenido</p>
                    <div><a href="#"><span>${nombre}</span></a></div>
                </div> 
            </div> 
        </div>
    </div>
    <div class="container">
        <div class="row el-menu">
            <div class="col-12 col-md-12 mena">
                <nav class="navbar navbar-default">
                    <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#">Home</a>
                    </div>
                      <ul class="nav navbar-nav">
                        <li><a href="http://localhost:8080/AplicacionBancaria/secure/aperturaCuentas">Apertura Cuentas</a></li>
                        <li><a href="http://localhost:8080/AplicacionBancaria/secure/ingresosYReintegros">Ingresos y Reintegros</a></li>
                        <li><a href="http://localhost:8080/AplicacionBancaria/secure/movimientos">Movimientos</a></li>
                        <li><a href="http://localhost:8080/AplicacionBancaria/secure/clientes">Mostrar Clientes</a></li>
                        <li><a href="#">Borrar Cuenta</a></li>
                      </ul>
                    </div>
                </nav>
            </div> 
        </div>
        
        <div id="conectado"></div>
        
        <footer id="futer">
            <div id="cerrar"><a href="http://localhost:8080/AplicacionBancaria/login?op=CERRAR">Cerrar Aplicación</a></div>
        </footer>
    </div>
</body>
</html>
