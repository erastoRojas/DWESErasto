
document.addEventListener("load",funcion,false);

function funcion(){
       
        $.ajax({
        type: 'POST',
        url: "http://localhost:8080/AplicacionBancaria/secure/clientes",
        data: $("#clientes").serialize(),
        
        success: function(data){
            
                var datos = JSON.parse(data);alert(data);
                var cont2 = 0;
                
                $("#tabla").empty();
                $("#tabla").addClass("table");
                $("#tabla").addClass("table-striped");
                document.getElementById("errorServlet").style.display = "none";
                $("#tabla").append("<tbody><tr><th>Fecha</th><th>Hora</th><th>Descripcion</th><th>Importe</th><th>Saldo</th></tr></tbody>");
                
                for(var i = 0;i<datos.length;i++){
                    
                    var hora = "";
                    
                    $("#tabla").append('<tr><td>'+datos[i].cl_dni+'</td><td>'+hora+'</td><td>'+datos[i].cl_nom+'</td><td id="'+cont2+'">'+datos[i].cl_tel+"€"+'</td><td>'+datos[i].cl_sal+"€"+'</td></tr>');
                    
                    var cuenta = cont2+'';
                    if(datos[i].cl_sal <0){
                        
                        document.getElementById(cuenta).style.color = "red";
                    }else{
                        document.getElementById(cuenta).style.color = "blue";
                    }cont2++;
                    
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

