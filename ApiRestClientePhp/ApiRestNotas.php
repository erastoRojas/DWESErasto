<?php

    require 'vendor/autoload.php';
    use GuzzleHttp\Client;  
    use GuzzleHttp\Exception\ClientException;
    
    $client = new Client();
    $uri = 'http://localhost:8282/ApiServidor/rest/apiNotas';
    $usiAsignatura = "";
    
    if(isset($_REQUEST["op"])){
        
        $op = $_REQUEST["op"];
        
        $nota =  new \stdClass;
        $alumno =  new \stdClass;
        $asignatura =  new \stdClass;
        
        $alumno->nombreAl = $_REQUEST["nombreAl"];
        $asignatura->nombreAsig = $_REQUEST["nombreAsig"];
        $idAlu = $_REQUEST["idAlumno"];
        $nota->idAlumno = $idAlu;
        $idAsi = $_REQUEST["idAsig"];
        $nota->idAsignatura = $idAsi;
        $nota->nota = $_REQUEST["nota"];
        
        switch ($op) {
            
            case "CHARGE":
                $response = $client->get($uri, [
                'query' => [
                    'nota' => json_encode($nota)
                ]
                ]); 
                break;

            case "INSERT":
                $response = $client->put($uri, [
                'query' => [
                    'nota' => json_encode($nota)
                ]
                ]); 
                $mensaje = json_decode($response->getBody());        
                break;

            case "UPDATE":
                $response = $client->post($uri, [
                'query' => [
                    'nota' => json_encode($nota)
                ]
                ]);
                $mensaje = json_decode($response->getBody()); 
                break;
                
            case "DELETE":
                $response = $client->delete($uri, [
                'query' => [
                    'nota' => json_encode($nota)
                ]
                ]);
                $mensaje = json_decode($response->getBody()); 
                break;   
        }
    }
    if(!isset($_REQUEST["op"]) || $op != "CHARGE"){
        //presiento que aqui falta ago       
        $response = $client->get($uri);  
    }
    $datos = json_decode($response->getBody());  
    //var_dump($datos);
    include "vista/vistaNotas.php";  
  
    
    