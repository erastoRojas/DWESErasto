function opcionTitular(lista){
    if(lista.options[lista.selectedIndex].value == 1){
        document.getElementById("opciones2Titular").style.display = "block"; 
    }
    if(lista.options[lista.selectedIndex].value == 0){
        document.getElementById("opciones2Titular").style.display = "none"; 
    }
    
}


function comprobarCliente(){
    return true;    
} 

function comprobarCuenta(){
    return true;    
}
   
//ajax
$(document).ready(function(){//cuando el documento se cargue
    $("#botonFormu").submit(function(){//la pulsar sobre el boton
       funcion();   
    });
    
    $("#botonFormu").click(function(){//la pulsar sobre el boton
       funcion();  
    });
    
    $("#botonFormu2").submit(function(){//la pulsar sobre el boton
       funcion2();   
    });
    
    $("#botonFormu2").click(function(){//la pulsar sobre el boton
       funcion2();  
    });
    
})   
    function funcion(){
        if(comprobarCliente()){//si el chequeo del cliente es correcto
            
            $.ajax({
            type: 'POST',
            url: "http://localhost:8282/AplicacionBancaria/secure/aperturaCuentas",
            data: $("#formulario").serialize(),

            success: function(data){
                
                var datos = JSON.parse(data);
                document.getElementById("cl_nom").value = datos.cl_nom;
                document.getElementById("cl_dni").value = datos.cl_dni;
                document.getElementById("cl_dir").value = datos.cl_dir;
                document.getElementById("cl_tel").value = datos.cl_tel;
                document.getElementById("cl_ema").value = datos.cl_ema;
                document.getElementById("cl_fna").value = datos.cl_fna;
                document.getElementById("cl_fcl").value = datos.cl_fcl;
                document.getElementById("cl_ncu").value = datos.cl_ncu;
                document.getElementById("cl_sal").value = datos.cl_sal;
                
                
                document.getElementById("formu2").style.display = "block";         
            },
            error: function(xhr)
            {//faltan diferenciar errores
                document.getElementById("formu2").style.display = "block";
                document.getElementById("respuesta").style.color = "red";
                document.getElementById("respuesta").innerHTML = xhr.responseText;
            }
            });
}}

    function funcion2(){
        if(comprobarCuenta()){//si el chequeo del cliente es correcto
            
            var continuar = confirm ("desea continuar?");
            
            if(continuar){
            
            $.ajax({
            type: 'POST',
            url: "http://localhost:8282/AplicacionBancaria/secure/aperturaCuentas",
            data: $("#formulario2").serialize(),

            success: function(data){
        
                document.getElementById("respuesta").innerHTML = data;           
            },
            error: function(xhr)
            {
                //document.getElementById("respuesta").style.display = "block";
                document.getElementById("respuesta").style.color = "red";
                document.getElementById("respuesta").innerHTML = xhr.responseText;
            }
            });
    } 
}}

