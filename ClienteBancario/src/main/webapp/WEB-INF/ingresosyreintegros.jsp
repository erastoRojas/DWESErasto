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
        <script src="Javascript/ingresosyreintegros.js"></script>
        <title>Ingresos y Reintegros</title>
        
         <style>
            .errorFormulario{
                display: none;
                color: red;
            }
            .errorTexto{
                display: none;
                color: red;
            }
            .errorRadio{
                display: none;
                color: red;
            }
        </style>
        
    </head>
    
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <h1>Ingresos y Reintegros</h1>
                    <form id="formulario" onsubmit="return validarCliente();">
                        <p>Elige una opción:</p>
                        <div class="radio">
                          <label><input type="radio" name="op" value="INGRESO" id="ingreso" class="ingreso">Ingreso</label>
                        </div>
                        <div class="radio">
                          <label><input type="radio" name="op" value="REINTEGRO" id="reintegro" class="reintegro">Reintegro</label>
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
                <div class="col-sm-6"><br><br>
                    <p id="respuesta"></p>
                </div>    
            </div>
        </div> 
    </body>
</html>
