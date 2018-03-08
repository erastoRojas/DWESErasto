<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="/Javascript/aperturaCuentas.js"></script>
        <title>Apertura Cuenta</title>
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
                    
                    var sub = n_cuenta.substr(9,1);//recoge el ultimo dígito  
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
        
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <h1>Apertura Cuenta</h1>
                    <form onsubmit="return comprobarCliente();">
                        <div class="form-group">       
                            <label><b>Introduce nº de cuenta:</b></label>
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
