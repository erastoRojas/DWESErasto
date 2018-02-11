<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body><h1>Asignaturas</h1>
        <form action="AsignaturasPDO.php?op=op" method="get">
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

        <script>
            function cargarAlumno(id,nombre,curso,ciclo){
                document.getElementById("id").value=id;
                document.getElementById("nombre").value=nombre;
                document.getElementById("curso").value=curso; 
                document.getElementById("ciclo").value=ciclo;
            }
        </script>
<?php

$servername = "db4free.net:3307";
$username = "oscarnovillo";
$password = "c557ef";
$database = "clasesdaw";

try {
    $dsn = "mysql:host=$servername;dbname=$database";
    $dbh = new PDO($dsn,$username, $password);
} catch (PDOException $e){
    echo $e->getMessage();
}

    if(isset($_REQUEST["op"])){

        $op = $_REQUEST["op"];
        $id = $_REQUEST["idasignatura"];
        $nombre = $_REQUEST["nombre"];  
        $curso = $_REQUEST["curso"];
        $ciclo =  $_REQUEST["ciclo"];

        switch ($op) {

            case "INSERT":
                $stmt = $dbh->prepare("INSERT INTO ASIGNATURAS "
                                    . "(NOMBRE, CURSO, CICLO) "
                                    . "VALUES (:nombre, :curso, :ciclo)");

                $stmt->bindParam(':nombre', $nombre);
                $stmt->bindParam(':curso', $curso);
                $stmt->bindParam(':ciclo', $ciclo);
                $stmt->execute();
                
                break;
            
            case "UPDATE":
                $stmt = $dbh->prepare("UPDATE ASIGNATURAS SET "
                                    . "NOMBRE = :nombre, CURSO = :curso, CICLO = :ciclo "
                                    . "WHERE ID = :id");

                $stmt->bindParam(':id', $id);
                $stmt->bindParam(':nombre', $nombre);
                $stmt->bindParam(':curso', $curso);
                $stmt->bindParam(':ciclo', $ciclo);
                $stmt->execute();
                         
                break;
            
            case "DELETE":
                
                $stmt = $dbh->prepare("DELETE FROM ASIGNATURAS "
                                          . "WHERE ID = :id");

                $stmt->bindParam(':id', $id);
                $stmt->execute();
                
                break;
        }
    }
        
// FETCH_ASSOC
$stmt = $dbh->prepare("SELECT * FROM ASIGNATURAS");
// Especificamos el fetch mode antes de llamar a fetch()
$stmt->setFetchMode(PDO::FETCH_ASSOC);
// Ejecutamos
$stmt->execute();

// Mostramos los resultados
?>
<table border="1">
    <tr style="background-color: grey;">
        <td></td>
        <td>Nombre</td>
        <td>Curso</td>
        <td>Ciclo</td>
    </tr>
<?php
    while ($row = $stmt->fetch()) {
?>            
    <tr>
        <td>
            <input type="button" value="cargar <?php echo $row['id'] ?>" 
            onclick="cargarAlumno(' <?php echo $row['id'] ?>',
                    '<?php echo $row['NOMBRE'] ?>',
                    '<?php echo $row['CURSO'] ?>',
                    '<?php echo $row['CICLO'] ?>');"/>
        </td> 
        <td>
            <?php echo $row['NOMBRE'] ?>
        </td>
        <td>
            <?php echo $row['CURSO'] ?>
        </td>
        <td>
            <?php echo $row['CICLO'] ?>
        </td>
    </tr>
<?php 
    }
?>
</table>
<?php>

$dbh = null;
