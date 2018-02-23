<%-- 
    Document   : inicio.jsp
    Created on : 23-feb-2018, 13:35:45
    Author     : DAW
--%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <title>Apertura Cuenta</title>
    </head>
    
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <h1>APLICACION BANCARIA</h1>
                    <form action="${pageContext.request.contextPath}/login?op=op" method="get">
                        <span><h2>REGISTRAR</h2></span><br>
                        <label><b>Nombre:</b></label>
                        <input type="text" id="nombre" name="nombre" required/><br>
                        <label><b>Email:</b></label>
                        <input type="text" id="email" name="email" required/><br>
                        <label><b>Password:</b></label>
                        <input type="text" id="pwd" name="pwd" required/>
                        <div style="padding: 15px;margin: 15px;">
                            <button name="op" id="op" value="REGISTRAR">Registrar</button>
                        </div>
                        <div style="border-bottom: 2px solid;padding: 15px"></div>
                    </form>
                    <form action="${pageContext.request.contextPath}/login?op=op" method="get">
                        <span><h2>LOGIN</h2></span><br>
                        <label><b>Nombre:</b></label>
                        <input type="text" id="nombre" name="nombre" required/><br>           
                        <label><b>Password:</b></label>
                        <input type="text" id="pwd" name="pwd" required/>
                        <div style="padding: 15px;margin: 15px;">
                            <button name="op" id="op" value="LOGIN">Login</button>
                        </div>
                        <div style="border-bottom: 2px solid;padding: 15px"></div>
                    </form>
                </div>
            </div>
        </div> 
    </body>
</html>
