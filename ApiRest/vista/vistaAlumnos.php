<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
        <h1>Alumnos</h1>
        <form action="http://localhost:8000/ApiRestPhp.php?op=op" method="get">
            <label></label>
            <input type="hidden" id="id" name="idalumno"/>
            <label><b>Nombre:</b></label>
            <input type="text" id="nombre" name="nombre"/>
            <label><b>Fecha:</b></label>
            <input type="text" id="fecha" name="fecha"/>
            <label><b>Mayor de edad:</b></label>
            <input type="checkbox" id="mayor" name="mayor"><br>
            <div style="border-bottom: 2px solid;padding: 15px"></div>
            <div style="padding: 15px;margin: 15px;">

                <button name="op" id="op" value="INSERT">Insertar</button>
                <button name="op" id="op" value="UPDATE">Actualizar</button>
                <button name="op" id="op" value="DELETE">Borrar</button>
            </div>
        </form>
        </div>

        <script>
            
            function insertar(){
                document.getElementById("formulario").method = "put";
            }
            
            function cargarAlumno(id,nombre,fecha,mayor){
                document.getElementById("id").value=id;
                document.getElementById("nombre").value=nombre;
                document.getElementById("fecha").value=fecha; 
                document.getElementById("mayor").checked=mayor;
            }
        </script>
        
        <div class="container">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID Alumno</th>
                        <th>Nombre</th>
                        <th>Fecha Nacimiento</th>
                        <th>Mayor de Edad</th>
                    </tr>
                </thead>
                <tbody>
               <?php foreach ($alumnos as $alumno)
    { 
                   
                   $date = new DateTime($alumno->fecha_nacimiento);
                   $fechad =  $date->format('m-d-Y');
                   ?>
                    <tr style="cursor:pointer">
                        <td>
                            <input type="button" value="cargar <?php echo $alumno->id ?>" 
                               onclick="cargarAlumno(' <?php echo $alumno->id ?>',
                                               '<?php echo $alumno->nombre ?>',
                                               '<?php echo $fechad ?>',
                                               '<?php echo $alumno->mayor_edad ?>');"/>
                        </td>
                        <td>
                            <?php echo $alumno->nombre ?>
                        </td> 
                        <td>
                            <?php echo $fechad ?>
                        </td> 
                        <td>
                           <input type="checkbox" <?php if ($alumno->mayor_edad) { ?>checked<?php } ?> />
                        </td>
                    </tr>
    <?php } ?>
                </tbody>            
            </table>
        </div>
    </body>
</html>
        
        
        <?php