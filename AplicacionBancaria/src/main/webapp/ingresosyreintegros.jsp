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
        <title>IES Enrique Tierno Galvan</title>
        <meta charset="UTF-8">
        <meta name="author" content="Erasto Rojas">
        <meta name="keywords" content="instituto,enrique,tierno,galvan">
        <meta name="description" content="Proyecto Aplicacion Bancaria"> 
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet/less" type="text/css" href="less/lessIngresosYReintegros.less" />
        <!--<link rel="stylesheet" href="css/cssMovimientos.css" type="text/css">-->
        
        <script src="//cdnjs.cloudflare.com/ajax/libs/less.js/3.0.0/less.min.js" ></script>        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
        <script src="Javascript/ingresosyreintegros.js"></script>
    </head>
    
            
<body>
    <div class="jumbotron jumbotron-fluid headera">
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-4 col-lg-3 headera">
                    <img alt="logo-boy" class="img-responsive" src="img/logo-boy-2.png" id="imgInicio">
                </div>
                <div class="col-12 col-md-8 col-lg-9 headera">
                    <h1>Aplicación Bancaria</h1>
                    <p>Bienvenido</p>
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
                        <a class="navbar-brand" href="http://localhost:8282/AplicacionBancaria/menuInicio.jsp">Home</a>
                    </div>                
                      <ul class="nav navbar-nav">
                        <li><a href="#">Apertura Cuentas</a></li>
                        <li><a href="http://localhost:8282/AplicacionBancaria/ingresosYReintegros">Ingresos y Reintegros</a></li>
                        <li><a href="http://localhost:8282/AplicacionBancaria/movimientos">Movimientos</a></li>
                        <li><a href="#">Borrar Cuenta</a></li>
                      </ul>
                    </div>
                </nav>
            </div> 
        </div>
    </div>
    <div>
        <p id="respuesta"></p>
    </div>     
    <div class="container">
        <div class="row">
            <div class="col-sm-2"></div>
            <div class="col-sm-8" id="ingresosYreintegros">
                <h1>Ingresos y Reintegros</h1>
                <form id="formulario" onsubmit="return validarCliente();">
                    <div class="col-sm-6">
                        <div class="radio">
                          <label><input type="radio" name="op" value="INGRESO" id="ingreso" class="ingreso">Ingreso</label>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="radio">
                          <label><input type="radio" name="op" value="REINTEGRO" id="reintegro" class="reintegro">Reintegro</label>
                        </div>
                    </div>
                    <span id="errorRadio" class="errorTexto">Debe seleccionar una opción</span>
                    <div class="form-group">       
                        <label><b>nº Cuenta:</b></label>
                        <input type="text" id="n_cuenta" name="n_cuenta" class="form-control"/>
                        <span id="errorCuenta" class="errorFormulario">El numero de cuenta no es correcto</span>
                    </div>
                    <div class="form-group">       
                        <label><b>Importe:</b></label>
                        <input type="text" id="importe" name="importe" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label for="comment">Descripción:</label>
                        <textarea class="form-control" rows="5" id="descripcion" name="descripcion" required></textarea>
                        <span id="errorTexto" class="errorTexto">Debe añadir una descripción</span>
                    </div>
                    <input type="button" id="botonListado" value="Buscar"/>
                </form>
            </div>
               
        </div>
    </div> 
        <footer id="futer">
            <div id="cerrar"><a href="http://localhost:8282/AplicacionBancaria/login?op=CERRAR">Cerrar Aplicación</a></div>
        </footer>
    </body>
</html>
