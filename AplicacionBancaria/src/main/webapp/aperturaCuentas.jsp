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
        <link rel="stylesheet/less" type="text/css" href="less/lessMovimientos.less" />
        <!--<link rel="stylesheet" href="css/cssMovimientos.css" type="text/css">-->
        
        <script src="//cdnjs.cloudflare.com/ajax/libs/less.js/3.0.0/less.min.js" ></script>        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
        <script src="/Javascript/aperturaCuentas.js"></script>
    </head>
    
    <body>
        <script>
            function comprobarCliente(){
                
                var error = false;
                var n_cuenta = document.getElementById("n_cuenta").value;
                var cl_dni = document.getElementById("dni").value;
                var correcto = 0;
                var reg_cuenta = new RegExp("^[0-9]{10}$");
                var reg_dni = new RegExp("^[0-9]{8}[a-z]$");
                
                if(reg_cuenta.test(n_cuenta)){//comprueba la cadena n_cuenta
                    
                    var sub = n_cuenta.substr(9,1);//recoge el ultimo dÃ­gito  
                    var n_cuenta_aux = 0;

                    for(var i = 0; i<n_cuenta.length-1;i++){//suma los 9 primeros digitos de la cadena
                        n_cuenta_aux += n_cuenta.charAt(i);
                    }
                    var resto = n_cuenta_aux % 9;//saca el resto de la division

                    if(resto != sub){//compara
                        error = true;
                    }
                }else{error = true;}
            
                if(!reg_dni.test(cl_dni)){//comprueba dni 
                    error = true;
                }
                
                if(error == true){
                    return true;
                }else{return false;}
            }
        </script>
    <div class="jumbotron jumbotron-fluid headera">
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-4 col-lg-3 headera">
                    <img alt="logo-boy" class="img-responsive" src="img/logo-boy-2.png" id="imgInicio">
                </div>
                <div class="col-12 col-md-8 col-lg-9 headera">
                    <h1>AplicaciÃ³n Bancaria</h1>
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
                        <a class="navbar-brand" href="http://localhost:8080/AplicacionBancaria/menuInicio.jsp">Home</a>
                    </div>                
                      <ul class="nav navbar-nav">
                        <li><a href="http://localhost:8080/AplicacionBancaria/secure/aperturaCuentas">Apertura Cuentas</a></li>
                        <li><a href="http://localhost:8080/AplicacionBancaria/secure/ingresosYReintegros">Ingresos y Reintegros</a></li>
                        <li><a href="${utils.Constantes.URLmovimientos}">Movimientos</a></li>
                        <li><a href="#">Borrar Cuenta</a></li>
                      </ul>
                    </div>
                </nav>
            </div> 
        </div>
    </div>    
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <h1>Apertura Cuenta</h1>
                    <form onsubmit="return comprobarCliente();">
                        <div class="form-group">       
                            <label><b>Introduce nÂº de cuenta:</b></label>
                            <input type="text" id="n_cuenta" name="n_cuenta" class="form-control"/>
                        </div>
                        <div class="form-group"> 
                            <label><b>Introduce DNI:</b></label>
                            <input type="text" id="dni" name="dni" class="form-control"/>
                        </div>
                        <button id="comprobacion" type="button" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
        </div> 
    </body>
</html>
