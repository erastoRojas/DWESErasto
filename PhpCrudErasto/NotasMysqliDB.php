<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body> 
        <?php
        require_once 'vendor/autoload.php';
        $servername = "db4free.net:3307";
        $username = "oscarnovillo";
        $password = "c557ef";
        $database = "clasesdaw";
        
        $db = new MysqliDb($servername, $username, $password, $database);
           
        $nombreAl="";
        $nombreAsig="";
        $nota="";
        
        if(isset($_REQUEST["op"])){
            $op = $_REQUEST["op"];
            $idAl = $_REQUEST["idAlumno"];
            $nombreAl = $_REQUEST["nombreAl"];  
            $idAsig = $_REQUEST["idAsig"];
            $nombreAsig =  $_REQUEST["nombreAsig"];
            $nota =  $_REQUEST["nota"];
        
            switch ($op) {

                case "CHARGE":

                    $db->where ("ID_ALUMNO",$idAl);
                    $db->where ("ID_ASIGNATURA",$idAsig);
                    $muestra_nota = $db->getOne("NOTAS");
                    $nota = $muestra_nota['NOTA'];

                    break;

                case "INSERT":
                    $data = Array(
                        'ID_ALUMNO' => $idAl,
                        'ID_ASIGNATURA' => $idAsig,
                        'NOTA' => $nota
                        );

                    $ok = $db->insert('NOTAS', $data);
                    if(!$ok){
                        $db->getLastError();
                    }

                    break;

                case "UPDATE":
                    $data = Array (
                        'ID_ALUMNO' => $idAl,
                        'ID_ASIGNATURA' => $idAsig,
                        'NOTA' => $nota,
                        );
                    $db->where ("ID_ALUMNO",$idAl);
                    $db->where ("ID_ASIGNATURA",$idAsig);
                        if ($db->update ('NOTAS', $data)){
                            echo $db->count . ' records were updated';
                        }
                        else{
                            echo 'update failed: ' . $db->getLastError();
                        }
                    break;

                case "DELETE":

                    $db->where('ID_ALUMNO',$idAl);
                    $db->where('ID_ASIGNATURA',$idAsig);
                    if($db->delete('NOTAS')) echo 'successfully deleted';

                    break;
            }
        }
        
        $alumnos = $db->get('ALUMNOS');
        $asignaturas = $db->get('ASIGNATURAS');
        $notas = $db->get('NOTAS');

        ?>
        <table>
            <tr>
                <td><h2>ALUMNOS</h2></td>
                <td><h2>ASIGNATURAS</h2></td>
            </tr>
            <tr>     
                <td>
                    <select id="al" onchange="cargarAlumno(this.value, this.options[this.selectedIndex].innerHTML)">
                        <option disabled selected value> -- selecciona una opción -- </option>
                        <?php foreach ($alumnos as $alumno) { ?> 
                            <option value='<?php echo $alumno['ID'] ?>'><?php echo $alumno['NOMBRE'] ?></option>
                        <?php } ?>
                    </select>
                </td>
                <td>
                    <select id="al" onchange="cargarAsignatura(this.value, this.options[this.selectedIndex].innerHTML)">
                        <option disabled selected value> -- selecciona una opción -- </option>
                        <?php foreach ($asignaturas as $asignatura) { ?> 
                            <option value='<?php echo $asignatura['id'] ?>'><?php echo $asignatura['NOMBRE'] ?></option>
                        <?php } ?>
                    </select>
                </td>
            </tr>      
        </table>
        
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
         <form action="NotasMysqliDB.php?op=op" method="get">
            <label></label>
            <label><b>Nombre:</b></label>
            <input type="hidden" id="idAlumno"  value="" name="idAlumno" size="1">
            <input type="text" value="<?php echo $nombreAl ?>" name="nombreAl" id="nombreAl">
            <label><b>Modulo:</b></label>
            <input type="hidden" id="idAsig" value="" name="idAsig"/>
            <input type="text" value="<?php echo $nombreAsig ?>" name="nombreAsig" id="nombreAsig">
            <label><b>Nota:</b></label>
            <input type="text" value="<?php echo $nota ?>" id="nota" name="nota" size="1">
            <div style="border-bottom: 2px solid;padding: 15px"></div>
            <div style="padding: 15px;margin: 15px;">
                <button name="op" id="op" value="CHARGE">Cargar</button>
                <button name="op" id="op" value="INSERT">Insertar</button>
                <button name="op" id="op" value="UPDATE">Actualizar</button>
                <button name="op" id="op" value="DELETE">Borrar</button>  
            </div>
        </form>
        
<?php
    foreach ($notas as $nota) {
        echo $nota['ID_ALUMNO'];
        echo $nota['ID_ASIGNATURA'];
        echo $nota['NOTA'];?> <br> <?php
    }
        $db->disconnect();

        echo "FIN";
        // put your code here
        ?>
    </body>
</html>
