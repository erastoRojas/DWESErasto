<?php

    require 'vendor/autoload.php';
    use GuzzleHttp\Client;  
    use GuzzleHttp\Exception\ClientException;
    
    $client = new Client();
    $uri = 'http://localhost:8282/ApiServidor/rest/apiAlumnos';
    
    $apikey = "1234";

    if(isset($_REQUEST["op"])){
        
        $op = $_REQUEST["op"];
        
        $alumno =  new \stdClass;
        $alumno->id = $_REQUEST["idalumno"];
        $alumno->nombre = $_REQUEST["nombre"];
        $date = new DateTime($_REQUEST["fecha"]);
        $fecha =  $date->format('Y-m-d');
        $alumno->fecha_nacimiento = $fecha;

        if (!isset($_REQUEST["mayor"])) {
            $alumno->mayor_edad = 0;
        }else {
            $alumno->mayor_edad = 1;
        }
            
        switch ($op) {

            case "INSERT":
                $response = $client->put($uri, [
                'query' => [
                    'alumno' => json_encode($alumno)
                ]
                ]);
                $mensaje = json_decode($response->getBody()); 
                break;

            case "UPDATE":
                $response = $client->post($uri, [
                'query' => [
                    'alumno' => json_encode($alumno)
                ]
                ]);
                $mensaje = json_decode($response->getBody());
                break;
                
            case "DELETE":
                $response = $client->delete($uri, [
                   /* 'headers' => [
                    'apikey' => $apikey
                ],*/
                'query' => [
                    'alumno' => json_encode($alumno)
                ]
                ]);
                $mensaje = json_decode($response->getBody());
                break;
        }
    }     
    $response = $client->get($uri, [
                /*'headers' => [
                    'apikey' => $apikey
                ]*/
                ]);          
    $alumnos = json_decode($response->getBody());  
    include "vista/vistaAlumnos.php";  
  
    
    
    
