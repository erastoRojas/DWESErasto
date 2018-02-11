<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body><h1>Alumnos</h1>
        <form action="AlumnosMySQLi.php?op=op" method="get">
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

        <script>
            function cargarAlumno(id,nombre,fecha,mayor){
                document.getElementById("id").value=id;
                document.getElementById("nombre").value=nombre;
                document.getElementById("fecha").value=fecha; 
                document.getElementById("mayor").checked=mayor;
            }
        </script>
        
        <?php

        $servername = "db4free.net:3307";
        $username = "oscarnovillo";
        $password = "c557ef";
        $database = "clasesdaw";

// Create connection
        $conn = new mysqli($servername, $username, $password, $database);

// Check connection
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        if(isset($_REQUEST["op"])){
        
            $op = $_REQUEST["op"];
            $id = $_REQUEST["idalumno"];
            $nombre = $_REQUEST["nombre"];  
            $date = new DateTime($_REQUEST["fecha"]);
            $fecha =  $date->format('Y-m-d H:i:s');

            if (!isset($_REQUEST["mayor"])) {
                $mayor = 0;
            }else {
                $mayor = 1;
            }

            switch ($op) {

                case "INSERT":
                    $statement = $conn->prepare("INSERT INTO ALUMNOS "
                                              . "(NOMBRE,FECHA_NACIMIENTO,MAYOR_EDAD)"
                                              . "VALUES(?,?,?)");

                    $statement->bind_param('ssi', $nombre, $fecha, $mayor);
                    $statement->execute();

                    break;

                case "UPDATE":
                    $statement = $conn->prepare("UPDATE ALUMNOS "
                                              . "SET NOMBRE = ?,FECHA_NACIMIENTO = ?, MAYOR_EDAD = ? "
                                              . "WHERE ID = ?");

                    $statement->bind_param('ssii', $nombre, $fecha, $mayor, $id);
                    $statement->execute();

                    break;
                case "DELETE":

                    $statement = $conn->prepare("DELETE FROM ALUMNOS "
                                              . "WHERE ID = (?)");

                    $statement->bind_param('i', $id);
                    $statement->execute();

                    break;
            }
        }
    
        $sql = "SELECT *
                FROM `ALUMNOS`";

        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
        ?>
        <table border="1">
            <?php
                while ($row = $result->fetch_assoc()) {
                    $date = new DateTime($_REQUEST["fecha"]);
                    $fechad =  $date->format('m-d-Y');
            ?>
                <tr>
                    <td>
                        <input type="button" value="cargar <?php echo $row['ID'] ?>" 
                               onclick="cargarAlumno(' <?php echo $row['ID'] ?>',
                                               '<?php echo $row['NOMBRE'] ?>',
                                               '<?php echo $fechad ?>',
                                               '<?php echo $row['MAYOR_EDAD'] ?>');"/>
                    </td> 
                    <td>
                        <?php echo $row['NOMBRE'] ?>
                    </td>
                    <td>
                        <?php echo $fechad ?>
                    </td>
                    <td>
                        <input type="checkbox" <?php if ($row['MAYOR_EDAD']) { ?>checked<?php } ?> />
                    </td>
                </tr>
            <?php 
                }
            ?>
        </table>
        <?php
        }
        $result->free();
        
        echo "Connected successfully";
        $conn->close();
        ?>
    </body>
</html>
