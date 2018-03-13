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
    <link rel="stylesheet/less" type="text/css" href="/AplicacionBancaria/less/lessinicio.less" />
    <!--<link rel="stylesheet" href="css/estilos.css" type="text/css"> -->
    
    
    <script src="//cdnjs.cloudflare.com/ajax/libs/less.js/3.0.0/less.min.js" ></script>        
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
    <script src="/AplicacionBancaria/Javascript/inicio.js"></script>
</head>
        
<body onload="cargar();">
    <div class="jumbotron jumbotron-fluid headera">
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-4 col-lg-3 headera">
                    <img alt="logo-boy" class="img-responsive" src="/AplicacionBancaria/img/logo-boy1.png" id="imgInicio">
                </div>
                <div class="col-12 col-md-8 col-lg-9 texto-header">
                    <h1>Aplicación Bancaria</h1>
                    <p class="parrafo-header" onclick="juego();">Si eres nuevo prueba suerte</p>
                </div> 
            </div> 
        </div>
    </div>
    <div class="container">
        <div class="row el-menu">
            <div class="col-12 col-md-12 mena">
                <nav class="navbar navbar-default menu">
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
        
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-1"></div>
                <div class="col-12 col-md-4">
                    <form action="${pageContext.request.contextPath}/login?op=op">
                        
                        
                        
                        <div class="form-group">
                            <span><h2>LOGIN</h2></span><br>
                            <label><b>Nombre:</b></label>
                            <input type="text" id="nombre" name="nombre" class="form-control" required/><br>   
                        </div>
                        <div class="form-group">
                            <label><b>Password:</b></label>
                            <input type="text" id="pass" name="pass" class="form-control" required/>
                            <div style="padding: 15px;margin: 15px;">
                                <button class="btn btn-primary" name="op" id="op" value="LOGIN">Login</button>
                            </div>
                        </div>
                        <div id="error1" onchange="cargar();"><span>${error1}</span></div>
                    </form>
                </div>
                    <div class="col-12 col-md-2"></div>
                <div class="col-12 col-md-4">
                    <form action="${pageContext.request.contextPath}/login?op=op">
                        <div class="form-group">
                            <span><h2>REGISTRAR</h2></span><br>
                            <label><b>Nombre:</b></label>
                            <input type="text" id="nombre" name="nombre" class="form-control" required/><br> 
                        </div>    
                        <div class="form-group">
                            <label><b>Password:</b></label>
                            <input type="text" id="pass" name="pass" class="form-control" required/>
                        </div>
                        <div style="padding: 15px;margin: 15px;">
                            <button class="btn btn-primary" name="op" id="op" value="REGISTRAR">Registrar</button>
                        </div>
                        <div id="error2" onchange="cargar();"><span>${error2}</span></div>
                        <div id="mensaje" onchange="cargar();"><span>${mensaje}</span></div>
                    </form>
                </div>
                        <div class="col-12 col-md-1"></div>
            </div>
        </div>
        
        <footer id="futer">
           
        </footer>
    </div>
</body>
</html>

