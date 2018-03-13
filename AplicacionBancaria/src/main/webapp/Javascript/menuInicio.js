function cargar(nombre){
    
    var fecha = new Date();
    
    var hora = fecha.getHours()+":"+fecha.getMinutes()+":"+fecha.getSeconds();

    $("#conectado").fadeIn("slow");
    $("#conectado").html("Hola: "+nombre+". <br>Te has conectado a las: "+hora);

    setTimeout(function(){ $("#conectado").fadeOut("slow"); }, 3000);
}
 