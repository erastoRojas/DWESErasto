<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title></title>
    </head>
    <body>
        <div class="container">
        <table table table-striped>
            <tr>
                <td><h2>ALUMNOS</h2></td>
                <td><h2>ASIGNATURAS</h2></td>
            </tr>
            <tr>     
                <td>
                    <select id="al" onchange="cargarAlumno(this.value, this.options[this.selectedIndex].innerHTML)">
                        <option disabled selected value> -- selecciona una opción -- </option>
                        <?php foreach ($datos[0] as $datoAlu) { ?> 
                            <option value='<?php echo $datoAlu->id ?>'><?php echo $datoAlu->nombre ?></option>
                        <?php } ?>
                    </select>
                </td>
                <td>
                    <select id="al" onchange="cargarAsignatura(this.value, this.options[this.selectedIndex].innerHTML)">
                        <option disabled selected value> -- selecciona una opción -- </option>
                        <?php foreach ($datos[1] as $datoAsi) { ?> 
                            <option value='<?php echo $datoAsi->id ?>'><?php echo $datoAsi->nombre ?></option>
                        <?php } ?>
                    </select>
                </td>
            </tr>      
        </table>
        </div>
        <script>
            
            function cargarAlumno(id,nombre,fecha,mayor){
                document.getElementById("idAlumno").value=id;
                document.getElementById("nombreAl").value=nombre;
                document.getElementById("fecha").value=fecha; 
                document.getElementById("mayor").checked=mayor;
            }
            
            function cargarAsignatura(id,nombre,curso,ciclo){
                document.getElementById("idAsig").value=id;
                document.getElementById("nombreAsig").value=nombre;
                document.getElementById("curso").value=curso; 
                document.getElementById("ciclo").value=ciclo;
            }
        </script> 
        <br>
        <div class="container">
        <form action="http://localhost:8000/ApiRestNotas.php?op=op" method="get">
            <label><b>Nombre:</b></label>
            <input type="hidden" id="idAlumno" value="<?php if(isset ($idAlu)){echo $idAlu;}?>" name="idAlumno" size="1">
            <input type="text" value="<?php if(isset ($alumno)){echo $alumno->nombreAl;} ?>" name="nombreAl" id="nombreAl">
            <label><b>Modulo:</b></label>
            <input type="hidden" id="idAsig" value="<?php if(isset ($idAsi)){echo $idAsi;}?>" name="idAsig"/>
            <input type="text" value="<?php if(isset ($asignatura)){echo $asignatura->nombreAsig;} ?>" name="nombreAsig" id="nombreAsig">
            <label><b>Nota:</b></label>
            <input type="text" value="<?php if(isset($datos[2]->nota)){
                                                echo $datos[2]->nota;
                                            } ?>                           
                   " id="nota" name="nota" size="1"><br><br>
            <div style="color: red;"><?php if(isset($datos[2]->mensaje)){echo $datos[2]->mensaje;} ?><?php if(isset($mensaje)){echo $mensaje->mensaje;} ?></div>
            <div style="border-bottom: 2px solid;padding: 15px"></div>
            <div style="padding: 15px;margin: 15px;">
                <button name="op" id="op" value="CHARGE">Cargar</button>
                <button name="op" id="op" value="INSERT">Insertar Nota</button>
                <button name="op" id="op" value="UPDATE">Actualizar Nota</button>
                <button name="op" id="op" value="DELETE">Borrar Nota</button>  
            </div>
        </form>
        <div></div>
        </div>
        
        
<?php
 /*   foreach ($notas as $nota) {
        echo $nota['ID_ALUMNO'];
        echo $nota['ID_ASIGNATURA'];
        echo $nota['NOTA'];?> <br> <?php
    }
        $db->disconnect();

        echo "FIN";
        // put your code here
       */ ?>
    </body>
</html>


