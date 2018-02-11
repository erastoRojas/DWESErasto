function validarCliente(){
    var n_cuenta = document.getElementById("n_cuenta").value;
                
    var reg = new RegExp("^[0-9]{10}$");
    var error = false;
    var sub = n_cuenta.substr(9,1);//recoge el ultimo dígito              
    var n_cuenta_aux = 0; 
    
    for(var i = 0; i<n_cuenta.length-1;i++){//comprobacion suma digitos == resto
        n_cuenta_aux += n_cuenta.charAt(i);
    }
    var resto = n_cuenta_aux % 9; 
    
    if(!reg.test(n_cuenta)){//comprueba la cadena n_cuenta
        document.getElementById("errorCuenta").style.display = "block";
        error = true;
    }else{document.getElementById("errorCuenta").style.display = "none";}
    
    if(resto != sub){//comprueba el resto
        document.getElementById("errorCuenta").style.display = "block";
        error = true;
    }
    
    if(error == true){
       return false;
    }else{return true;}             
} 
   
//ajax
$(document).ready(function(){//cuando el documento se cargue
    $("#datos").submit(function(){//la pulsar sobre el boton
       funcion();   
    });
    
    $("#botonListado").click(function(){//la pulsar sobre el boton
       funcion();  
    });
    
})   
    function funcion(){
        if(validarCliente()){//si el chequeo del cliente es correcto
            alert("mandado al servidor");
            /*
            $.post("",$("#datos").serialize(),//manda datos 
                function(data,status){
                    var datos = JSON.parse(data);
                    
                    //trabajo con los datos
                    
                    $("#tabla").empty();
                    
                    //excepciones
                   
                    $("#tabla").append("<tr><th>Fecha</th><th>Descripcion</th><th>Importe</th></tr>")
                    for(var i = 0;i<datos.length;i++){
                        //var fecha = formatofecha(datos[i].mo_fec);
                        $("#tabla").append('<tr><td>'+datos[i].mo_fec+'</td><td>'+datos[i].mo_des+'</td><td>'+datos[i].mo_imp+'</td></tr>')
                    }
                });*/
        }
    }
     


