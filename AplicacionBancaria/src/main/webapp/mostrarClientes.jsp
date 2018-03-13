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
        <link rel="stylesheet/less" type="text/css" href="/AplicacionBancaria/less/lessMovimientos.less" />
        <!--<link rel="stylesheet" href="css/cssMovimientos.css" type="text/css">-->
        
        <script src="//cdnjs.cloudflare.com/ajax/libs/less.js/3.0.0/less.min.js" ></script>        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
        <script src="/AplicacionBancaria/Javascript/mostrarClientes.js"></script>
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
                    <p>Conectado:</p>
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
                        <li><a href="http://localhost:8080/AplicacionBancaria/secure/movimientos">Movimientos</a></li>
                        <li><a href="http://localhost:8080/AplicacionBancaria/secure/clientes">Mostrar Clientes</a></li>
                        <li><a href="#">Borrar Cuenta</a></li>
                      </ul>
                    </div>
                </nav>
            </div> 
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-sm-2"></div>
            <div class="col-sm-8">
                <div id="tituloMovimientos"><h1>Clientes:</h1>
                    <form id="clientes" name="clientes" value="clientes"></form>
            </div> 
            <div class="col-sm-2"></div>
        </div><br><br>
        
    </div> 
        <div class="row">
            <div class="col-sm-2"></div>
            <div class="col-sm-8" id="contenedorTabla">
                <table class="table table-striped" id="tabla">
                    <tr>
                    <tbody>
                    <th>
                        <p>Nombre:</p>
                    </th>
                    <th>
                        <p>Dni:</p>
                    </th>
                    <th>
                        <p>Teléfono:</p>
                    </th>
                    <th>
                        <p>Email:</p>
                    </th>
                    <th>
                        <p>Nº Cuentas:</p>
                    </th>
                    <th>
                        <p>Saldo:</p>
                    </th>
                        
                    </tbody>
                        
                        
                    </tr>    
                    
        <c:out value="${mensaje}"></c:out>
        <c:forEach items="${clientes}" var="cliente">  
            <tr>
                
                <td>
                    ${cliente.cl_nom}
                </td>
                <td>
                    ${cliente.cl_dni}
                </td>
                <td>
                    ${cliente.cl_tel}
                </td>
                <td>
                    ${cliente.cl_ema}
                </td>
                <td>
                    ${cliente.cl_ncu}
                </td>
                <td>
                    ${cliente.cl_sal}
                </td>
                
            </tr>
        </c:forEach>  
        </table>
                    
                    
              
                <span id="errorServlet"></span>
            </div>
        <div class="col-sm-2">
                <div id="cerrar"><a href="http://localhost:8080/AplicacionBancaria/login?op=CERRAR">Cerrar Aplicación</a></div>
        </div>
        </div>
    <footer id="futer">
        <div id="cerrar"><a href="http://localhost:8080/AplicacionBancaria/login?op=CERRAR">Cerrar Aplicación</a></div>
    </footer>
</body>
</html>
