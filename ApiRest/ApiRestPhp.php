<?php

    require 'vendor/autoload.php';
    use GuzzleHttp\Client;  
    use GuzzleHttp\Exception\ClientException;
    
    $client = new Client();
    $uri = 'http://localhost:8282/basedatos1Erasto/rest/apiAlumnos';

    if(isset($_REQUEST["op"])){
        
            switch ($op) {

                case "INSERT":
                    $alumno =  new \stdClass;
                    $alumno->nombre = $_REQUEST["nombre"];
                    $date = new DateTime($_REQUEST["fecha"]);
                    $alumno->fecha_nacimiento = $date->format('Y-m-d');
                    if (!isset($_REQUEST["mayor"])) {
                        $alumno->mayor_edad = 0;
                    }else {
                        $alumno->mayor_edad = 1;
                    }
                    $response = $client->put($uri, [
                    'query' => [
                        'alumno' => json_encode($alumno)
                    ]
                    ]); 
                    
                    break;

                case "UPDATE":
                    

                    break;
                case "DELETE":

                    

                    break;
            }
        }
            
            $response = $client->get($uri);          
            $alumnos = json_decode($response->getBody());  
            include "vista/vistaAlumnos.php";  
        
        
/*
    $alumno =  new \stdClass;
    $alumno->id = 10;
    $alumno->nombre="Juan";
    
    echo "<br>"."POSTaaa"."<br>";
    $response = $client->post($uri, [
    'form_params' => [
        'alumno' => json_encode($alumno)
        
    ]
]);          
   
    $alumno = json_decode($response->getBody());  
    echo $alumno->id . " ".$alumno->nombre; 
    
    
    echo "<br>"."PUT"."<br>";
    $response = $client->put($uri, [
    'query' => [
        'alumno' => json_encode($alumno)
        
    ]
]);   
    $alumno = json_decode($response->getBody());  
    echo $alumno->id . " ".$alumno->nombre; 
    
    
    echo "<br>"."DELETE"."<br>";
    try{
    $response = $client->delete($uri, [
    'query' => [
        'alumno' => json_encode($alumno)
        
    ]
    ]);  }catch (ClientException $exception)   
    {
        echo $exception->getCode();
        $alumno = json_decode($exception->getResponse()->getBody());  
    }
    
    
    //$alumno = json_decode($response->getBody());  
    echo $alumno->id . " ".$alumno->nombre; 
    */
    
    
   ?> 
    
    
    
