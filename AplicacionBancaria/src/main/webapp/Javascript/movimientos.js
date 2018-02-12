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
       alert("mandado al servidor");
        $.ajax({
        type: 'POST',
        url: "http://localhost:8080/AplicacionBancaria/movimientos",
        data: $("#formulario").serialize(),
        //datatype: 'json',
        success: function(data){
            // if (status != 500) {
            var datos = JSON.parse(data);

            $("#tabla").empty();        
            $("#tabla").append("<tr><th>Fecha</th><th>Descripcion</th><th>Importe</th></tr>");
            for(var i = 0;i<datos.length;i++){
                //var fecha = formatofecha(datos[i].mo_fec);
                $("#tabla").append('<tr><td>'+datos[i].mo_fec+'</td><td>'+datos[i].mo_des+'</td><td>'+datos[i].mo_imp+'</td></tr>');
            }//}
        }               
    });
    }
}   
     /*
    function funcion(){
        if(validarCliente()){//si el chequeo del cliente es correcto
           alert("hola");
            $.post("http://localhost:8080/AplicacionBancaria/movimientos",$("#datos").serialize(),//manda datos 
                function datos(data,status){alert("hola");
                    // if (status != 500) {
                    var datos = JSON.parse(data);
                    alert("hola");
                    $("#tabla").empty();        
                    $("#tabla").append("<tr><th>Fecha</th><th>Descripcion</th><th>Importe</th></tr>");
                    for(var i = 0;i<datos.length;i++){
                        //var fecha = formatofecha(datos[i].mo_fec);
                        $("#tabla").append('<tr><td>'+datos[i].mo_fec+'</td><td>'+datos[i].mo_des+'</td><td>'+datos[i].mo_imp+'</td></tr>');
                    }//}
                
                });//.fail(function(data) { 
                    //alert(data.responseText); 
                //});
        }
    }   
     
*/
 
//ajax
/*$(document).ready(function(){//cuando el documento se cargue
    
    $("#formulario").bind("submit",function(){
  
        $.ajax({
            type: 'POST',
            url: "http://localhost:8080/AplicacionBancaria/movimientos",
            data: $("#datos").serialize(),
            datatype: 'json',
            success: function(data){alert("hola");
                    // if (status != 500) {
                    var datos = JSON.parse(data);
                    alert("hola");
                    $("#tabla").empty();        
                    $("#tabla").append("<tr><th>Fecha</th><th>Descripcion</th><th>Importe</th></tr>");
                    for(var i = 0;i<datos.length;i++){
                        //var fecha = formatofecha(datos[i].mo_fec);
                        $("#tabla").append('<tr><td>'+datos[i].mo_fec+'</td><td>'+datos[i].mo_des+'</td><td>'+datos[i].mo_imp+'</td></tr>');
                    }//}
                
        }});
});
});
        
            
           // beforeSend: function(){
                
           // },
            //complete:function(data){
                /*
                btnEnviar.val("Enviar formulario");
                btnEnviar.attr("disabled");
           // },
            success: function(response){
                
                var response = JSON.parse(response);
                alert(response);

                $("#tabla").empty();
                $("#tabla").append("<tr><th>Fecha</th><th>Descripcion</th><th>Importe</th></tr>")
                for(var i = 0;i<datos.length;i++){
                    //var fecha = formatofecha(datos[i].mo_fec);
                    $("#tabla").append('<tr><td>'+datos[i].mo_fec+'</td><td>'+datos[i].mo_des+'</td><td>'+datos[i].mo_imp+'</td></tr>')
                }*//*
                $(function(){
                    $.each(response,function(i,item){
                        var tr = $('<tr>').append(
                            $('<td>').text(item.mo_fec),
                            $('<td>').text(item.mo_des),
                            $('<td>').text(item.mo_imp)
                        );
                       // console.log($tr.wrap(('<p>').html());
                    });
                });
            }
            //,error: function(data){
                
            //}
        });
    });
});*/
    
 