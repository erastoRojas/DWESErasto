function validarCliente(){
    var n_cuenta = document.getElementById("n_cuenta").value;
    var fecha_inicio = new Date(document.getElementById("fecha_inicio").value);
    var fecha_fin = new Date(document.getElementById("fecha_fin").value);
                
    var reg = new RegExp("^[0-9]{10}$");
    var error = false;
    var sub = n_cuenta.substr(9,1);//recoge el ultimo dígito              
    var n_cuenta_aux = 0; 
    var fecha_aux = new Date();
    
    for(var i = 0; i<n_cuenta.length-1;i++){//comprobacion suma digitos == resto
        n_cuenta_aux += n_cuenta.charAt(i);
    }
    var resto = n_cuenta_aux % 9; 
    
    if(!reg.test(n_cuenta)){//comprueba RegExp n_cuenta
        document.getElementById("errorCuenta").style.display = "block";
        error = true;
    }else{document.getElementById("errorCuenta").style.display = "none";}
    
    if(resto != sub){//comprueba el resto
        document.getElementById("errorCuenta").style.display = "block";
        error = true;
    }
    
    if(isNaN(fecha_inicio.getTime())){//comprueba que se hayan rellenado los campos
        document.getElementById("fecha1Vacia").style.display = "block";
        error = true;
    }else{document.getElementById("fecha1Vacia").style.display = "none";}
    
    if(isNaN(fecha_fin.getTime())){//comprueba que se hayan rellenado los campos
         document.getElementById("fecha2Vacia").style.display = "block";
         error = true;
    }else{document.getElementById("fecha2Vacia").style.display = "none";}
    
    if(fecha_inicio > fecha_fin){//comprueba la fecha de inicio y fin 
        document.getElementById("errorFecha").style.display = "block";
        error = true;
    }else{document.getElementById("errorFecha").style.display = "none";}
        
    if(fecha_inicio > fecha_aux){//comprueba que la fechaInicio no sea posterior a la actual
        document.getElementById("fecha1mal").style.display = "block";
        error = true;
    }else{document.getElementById("fecha1mal").style.display = "none";}
    
    if(fecha_fin > fecha_aux){//comprueba que la fechaFin no sea posterior a la actual
        document.getElementById("fecha2mal").style.display = "block";
        error = true;
    }else{document.getElementById("fecha2mal").style.display = "none";}
    
    if(error == true){
       return false;
    }else{return true;}             
}

//ajax
$(document).ready(function(){//cuando el documento se cargue
    $("#formulario").submit(function(){//al pulsar Enter
       funcion();   
    });
    
    $("#botonListado").click(function(){//la pulsar sobre el boton
       funcion();  
    });
    
});   

function funcion(){
    if(validarCliente()){//si el chequeo del cliente es correcto
       
        $.ajax({
        type: 'POST',
        url: "http://localhost:8282/AplicacionBancaria/movimientos",
        data: $("#formulario").serialize(),
        
        success: function(data){
            
                var datos = JSON.parse(data);
                
                $("#tabla").empty();
                document.getElementById("errorServlet").style.display = "none";
                $("#tabla").append("<tr><th>Fecha</th><th>Descripcion</th><th>Importe</th></tr>");
                for(var i = 0;i<datos.length;i++){
                    
                    $("#tabla").append('<tr><td>'+datos[i].mo_fec+'</td><td>'+datos[i].mo_des+'</td><td>'+datos[i].mo_imp+"€"+'</td></tr>');
                }               
        },
        error: function(xhr)
        {
            $("#tabla").empty();
            document.getElementById("errorServlet").style.display = "block";
            document.getElementById("errorServlet").style.color = "red";
            document.getElementById("errorServlet").innerHTML = xhr.responseText;
        }
        });
    } 
}
