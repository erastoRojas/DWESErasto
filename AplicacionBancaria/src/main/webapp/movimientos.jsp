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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="Javascript/movimientos.js"></script>
        <title>Movimientos</title>
        <style>
            .errorFormulario{
                display: none;
                color: red;
            }
            .errorFecha{
                display: none;
                color:red;
            }
            .fecha1Vacia{
                display: none;
                color: red; 
            }
            .fecha2Vacia{
                display: none;
                color: red; 
            }
            .fecha1mal{
                display: none;
                color: red; 
            }
            .fecha2mal{
                display: none;
                color: red; 
            }
            .errorServlet{
                color: red;
            }
        </style>
    </head>

    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <h1>Movimientos</h1>
                    <form id="formulario" onsubmit="return validarCliente();">
                        <div class="form-group">       
                            <label><b>nº Cuenta:</b></label>
                            <input type="text" id="n_cuenta" name="n_cuenta" class="form-control"/>
                            <span id="errorCuenta" class="errorFormulario">El numero de cuenta no es correcto</span>
                        </div>
                        <div class="form-group"> 
                            <label><b>Fecha inicio:</b></label>
                            <input type="date" id="fecha_inicio" name="fecha_inicio" class="form-control"/>
                            <span id="fecha1Vacia" class="errorFecha">Introduce una fecha</span>
                            <span id="fecha1mal" class="fecha1mal">La fecha no puede ser posterior a la actual</span>
                        </div>
                        <div class="form-group">
                            <label><b>Fecha fin:</b></label>
                            <input type="date" id="fecha_fin" name="fecha_fin" class="form-control"/>
                            <span id="fecha2Vacia" class="errorFecha">Introduce una fecha</span>
                            <span id="errorFecha" class="errorFecha">Las fechas no son correctas</span>
                            <span id="fecha2mal" class="fecha2mal">La fecha no puede ser posterior a la actual</span>
                        </div>
                        <input type="button" id="botonListado" name="botonListado" value="Enviar"/>
                    </form>
                </div>
                <div class="col-sm-4"><br><br>
                    <table class="table table-striped" id="tabla"> 
                    </table>
                    <span id="errorServlet"></span>
                </div>    
            </div>
        </div> 
    </body>
</html>
