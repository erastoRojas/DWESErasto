<%-- 
    @author Erasto Rojas SÃ¡nchez
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
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <meta name="author" content="Erasto Rojas">
        <meta name="keywords" content="instituto,enrique,tierno,galvan">
        <meta name="description" content="Proyecto Aplicacion Bancaria"> 
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet/less" type="text/css" href="/AplicacionBancaria/less/lessAperturaCuentas.less" />
        <!--<link rel="stylesheet" href="css/cssMovimientos.css" type="text/css">-->
        
        <script src="//cdnjs.cloudflare.com/ajax/libs/less.js/3.0.0/less.min.js" ></script>        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
        <script src="/AplicacionBancaria/Javascript/aperturaCuentas.js"></script>
    </head>
    
    <body>
        
    <div class="jumbotron jumbotron-fluid headera">
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-4 col-lg-3 headera">
                    <img alt="logo-boy" class="img-responsive" src="/AplicacionBancaria/img/logo-boy-2.png" id="imgInicio">
                </div>
                <div class="col-12 col-md-8 col-lg-9 headera">
                    <h1>Aplicación Bancaria</h1>
                    <p>Conectado</p>
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
                        <li><a href="http://localhost:8282/AplicacionBancaria/secure/aperturaCuentas">Apertura Cuentas</a></li>
                        <li><a href="http://localhost:8282/AplicacionBancaria/secure/ingresosYReintegros">Ingresos y Reintegros</a></li>
                        <li><a href="${URLmovimientos}">Movimientos</a></li>
                        <li><a href="http://localhost:8282/AplicacionBancaria/secure/clientes">Mostrar Clientes</a></li>
                        <li><a href="#">Borrar Cuenta</a></li>
                      </ul>
                    </div>
                </nav>
            </div> 
        </div>
    </div>
    <div>    
    </div> 
    <div class="container">
        <div class="row">
            <div class="col-sm-4">
                <h1>Apertura Cuenta</h1>
                <form id="formulario" onsubmit="return comprobarCliente();">
                    <div class="form-group">       
                        <label><b>Introduce DNI del titular:</b></label>
                        <input type="text" id="dni" name="dni" class="form-control"/>
                        <input type="hidden" name="op" value="DNI"/>
                        <span id="errorDni" class="errorDni">Falta Javascript</span>
                    </div>
                    <button id="botonFormu" type="button" class="btn btn-primary">Submit</button>
                </form>
                <div id="cerrar"><a href="http://localhost:8282/AplicacionBancaria/login?op=CERRAR">Cerrar Aplicación</a></div>
            </div>
            <div class="col-sm-8" style="display:none;" id="formu2">
                <form id="formulario2" onsubmit="return comprobarCuenta();" name="formu">
                    <div class="col-sm-6">
                        <h1>Datos del Cliente</h1>
                        <p id="respuesta"></p>
                        <div class="form-group">       
                            <input type="hidden" name="op" value="CUENTA"/>
                            <label><b>Nombre:</b></label>
                            <input type="text" id="cl_nom" name="cl_nom" class="form-control"/>
                            <label><b>Dni:</b></label>
                            <input type="text" id="cl_dni" name="cl_dni" class="form-control"/>
                            <label><b>Direccion:</b></label>
                            <input type="text" id="cl_dir" name="cl_dir" class="form-control"/>
                            <label><b>Teléfono:</b></label>
                            <input type="text" id="cl_tel" name="cl_tel" class="form-control"/>
                            <label><b>Email:</b></label>
                            <input type="text" id="cl_ema" name="cl_ema" class="form-control"/>
                            <label><b>Fecha de Nacimiento:</b></label>
                            <input type="date" id="cl_fna" name="cl_fna" class="form-control"/>
                            <label><b>Fecha creación de Cliente:</b></label>
                            <input type="date" id="cl_fcl" name="cl_fcl" class="form-control"/>
                            <label><b>Numero de Cuentas:</b></label>
                            <input type="text" id="cl_ncu" name="cl_ncu" class="form-control"/>
                            <label><b>Saldo:</b></label>
                            <input type="text" id="cl_sal" name="cl_sal" class="form-control"/>
                        </div>        
                    </div>
                    <div class="col-sm-6">
                        <h1>Datos de la Cuenta</h1>
                        <div class="form-group">
                        <label><b>Número de cuenta:</b></label>
                            <input type="text" id="n_cuenta" name="n_cuenta" class="form-control"/>
                            <label><b>Saldo de apertura:</b></label>
                            <input type="text" id="saldo_apertura" name="saldo_apertura" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="sel1">Segundo titular:</label>
                            <select class="form-control" name="miLista" onchange="opcionTitular(this)">
                                <option value="0">no</option>
                                <option value="1">si</option>
                            </select>
                        </div>
                        <div class="form-group" style="display:none;" id="opciones2Titular">
                            <div class="form-group">       
                                <input type="hidden" name="op" value="CUENTA"/>
                                <label for="sel1">Datos del 2 titular:</label>
                                <label><b>Nombre:</b></label>
                                <input type="text" id="cl_nom2" name="cl_nom2" class="form-control"/>
                                <label><b>Dni:</b></label>
                                <input type="text" id="cl_dni2" name="cl_dni2" class="form-control"/>
                                <label><b>Direccion:</b></label>
                                <input type="text" id="cl_dir2" name="cl_dir2" class="form-control"/>
                                <label><b>Teléfono:</b></label>
                                <input type="text" id="cl_tel2" name="cl_tel2" class="form-control"/>
                                <label><b>Email:</b></label>
                                <input type="text" id="cl_ema2" name="cl_ema2" class="form-control"/>
                                <label><b>Fecha de Nacimiento:</b></label>
                                <input type="text" id="cl_fna2" name="cl_fna2" class="form-control"/>
                                <label><b>Fecha creación de Cliente:</b></label>
                                <input type="text" id="cl_fcl2" name="cl_fcl2" class="form-control"/>
                                <label><b>Numero de Cuentas:</b></label>
                                <input type="text" id="cl_ncu2" name="cl_ncu2" class="form-control"/>
                                <label><b>Saldo:</b></label>
                                <input type="text" id="cl_sal2" name="cl_sal2" class="form-control"/>
                            </div>
                        </div>
                        <button id="botonFormu2" type="button" class="btn btn-primary">Submit</button>
                    </div> 
                </form>
            </div>
            <p id="respuesta1"></p>
        </div>
    </div> 
    <footer id="futer">
        
    </footer>
</body>
</html>
