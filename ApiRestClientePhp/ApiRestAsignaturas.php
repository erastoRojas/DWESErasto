<?php

    require 'vendor/autoload.php';
    use GuzzleHttp\Client;  
    use GuzzleHttp\Exception\ClientException;
    
    $client = new Client();
    $uri = 'http://localhost:8282/ApiServidorJava/rest/apiAsignaturas';
    $usiAsignatura = "";
    
    if(isset($_REQUEST["op"])){
        
        $op = $_REQUEST["op"];
        $asignatura =  new \stdClass;
        $asignatura->id = $_REQUEST["idasignatura"];
        $asignatura->nombre = $_REQUEST["nombre"];
        $asignatura->curso = $_REQUEST["curso"];
        $asignatura->ciclo = $_REQUEST["ciclo"];
        
        switch ($op) {

            case "INSERT":
                $response = $client->put($uri, [
                'query' => [
                    'asignatura' => json_encode($asignatura)
                ]
                ]); 
                $mensaje = json_decode($response->getBody()); 
                break;

            case "UPDATE":
                $response = $client->post($uri, [
                'query' => [
                    'asignatura' => json_encode($asignatura)
                ]
                ]);
                $mensaje = json_decode($response->getBody());               
                break;
                
            case "DELETE":
                $response = $client->delete($uri, [
                'query' => [
                    'asignatura' => json_encode($asignatura)
                ]
                ]);
                //try  carch recoger excepcion
                $mensaje = json_decode($response->getBody()); 
                break;
            case "SUBJECTS":
                $response = $client->delete($uri, [
                'query' => [
                    'asignatura' => json_encode($asignatura)
                ]
                ]);
                break;    
        }
    }
    //presiento que aqui falta ago       
    $response = $client->get($uri);          
    $asignaturas = json_decode($response->getBody());  
    include "vista/vistaAsignaturas.php";  
  
    
    
    

