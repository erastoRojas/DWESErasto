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
        <h1>Asignaturas</h1>
        <form action="http://localhost:8000/ApiRestAsignaturas.php" method="get">
            <label></label>
            <input type="hidden" id="id" name="idasignatura"/>
            <label><b>Nombre:</b></label>
            <input type="text" id="nombre" name="nombre"/>
            <label><b>Curso:</b></label>
            <input type="text" id="curso" name="curso"/>
            <label><b>Ciclo:</b></label>
            <input type="text" id="ciclo" name="ciclo"><br>
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
            
            function cargarAsignatura(id,nombre,curso,ciclo){
                document.getElementById("id").value=id;
                document.getElementById("nombre").value=nombre;
                document.getElementById("curso").value=curso; 
                document.getElementById("ciclo").value=ciclo;
            }
        </script>
        
        <div class="container">
            <div style="color: red;"><?php if(isset($mensaje)){echo $mensaje->mensaje;} ?></div>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID Asignatura</th>
                        <th>Nombre</th>
                        <th>Curso</th>
                        <th>Ciclo</th>
                    </tr>
                </thead>
                <tbody>
               <?php foreach ($asignaturas as $asignatura)
                    { 
                   ?>
                    <tr style="cursor:pointer">
                        <td>
                            <input type="button" value="cargar <?php echo $asignatura->id ?>" 
                               onclick="cargarAsignatura(' <?php echo $asignatura->id ?>',
                                               '<?php echo $asignatura->nombre ?>',
                                               '<?php echo $asignatura->curso ?>',
                                               '<?php echo $asignatura->ciclo ?>');"/>
                        </td>
                        <td>
                            <?php echo $asignatura->nombre ?>
                        </td> 
                        <td>
                            <?php echo $asignatura->curso ?>
                        </td> 
                        <td>
                            <?php echo $asignatura->ciclo ?>
                        </td>
                    </tr>
    <?php } ?>
                </tbody>            
            </table>
        </div>
    </body>
</html>
        
        
