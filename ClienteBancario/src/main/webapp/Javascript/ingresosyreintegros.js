function validarCliente(){
    var n_cuenta = document.getElementById("n_cuenta").value;
    var text_area = document.getElementById("descripcion").value;
    
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
    
    if(text_area == ''){//comprueba que haya una descripcion seleccionada
        document.getElementById("errorTexto").style.display = "block";
        error = true;
    }else{document.getElementById("errorTexto").style.display = "none";}
    
    if(document.getElementById("ingreso").checked == false && document.getElementById("reintegro").checked == false){
        document.getElementById("errorRadio").style.display = "block";
        error = true;
    }else{document.getElementById("errorRadio").style.display = "none";}
    
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
            
            var continuar = confirm ("desea continuar?");
            
            if(continuar){
            
            document.getElementById("formulario").style.action = "http://localhost:8080/ClienteBancario/IngresosYReintegros";
            
            
            $.ajax({
            type: 'POST',
            url: "http://localhost:8080/ClienteBancario/ingresosYReintegros",
            data: $("#formulario").serialize(),

            success: function(data){
                    
                document.getElementById("respuesta").style.color = "blue";
                document.getElementById("respuesta").innerHTML = data;           
            },
            error: function(xhr)
            {
                document.getElementById("respuesta").style.display = "block";
                document.getElementById("respuesta").style.color = "red";
                document.getElementById("respuesta").innerHTML = xhr.responseText;
            }
            });
    } 
}}


