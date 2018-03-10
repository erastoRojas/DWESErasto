function comprobarCliente(){
                
    var n_cuenta = document.getElementById("n_cuenta").value;
    var cl_dni = document.getElementById("cl_dni").value;
    var reg_cuenta = new RegExp("^[0-9]{10}$");
    var reg_dni = new RegExp("^[0-9]{8}[a-z]$");
    var error = false;
    var sub = n_cuenta.substr(9,1);//recoge el ultimo dígito  
    var n_cuenta_aux = 0;
    
    for(var i = 0; i<n_cuenta.length-1;i++){//suma los 9 primeros digitos de la cadena
            n_cuenta_aux += n_cuenta.charAt(i);
    }
    var resto = n_cuenta_aux % 9;//saca el resto de la division
    
    if(!reg_cuenta.test(n_cuenta)){//comprueba la cadena n_cuenta
        error = true;
    }
     
    if(resto != sub){//compara el resto
        error = true;
    }

    if(!reg_dni.test(cl_dni)){//comprueba dni 
        error = true;
    }

    if(error == true){
        return true;
    }else{return false;}
}
//AJAX 
$(document).ready(function(){//carga la funcion cuando cargue el documento
    $("#comprobacion").click(function(){//onclick en comprobacion
        if(comprobarMovimientos()){//si la funcion que comprueba el cliente devuelve true:
            $.post("http://localhost:8080/AplicacionBancaria/Movimientos",$("#datos").serialize(),//manda los datos del formulario via post 
                function(data,status){//esta funcion recoge la respuesta del sevidor
                    
                    var datos = JSON.parse(data);
                    
                    $("#tabla").empty();//borra la tabla
                    $("#error").fadeOut(100, function(){
                        
                    if(status != 200){
                        switch (status){
                            case 500:
                                alert(data);
                                $("#error").html("error muy malo");
                                $("#error").fadeIn(100);
                            case 300:
                                $("#error").html("error por todavia");
                                $("#error").fadeIn(300);
                        }
                    }else{
                       $("#tabla").append(""); 
                    }
                        
                    });
                    
                }
            
            );
        }
    });
});
     