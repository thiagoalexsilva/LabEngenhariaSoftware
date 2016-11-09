<%-- 
    Document   : index
    Created on : Oct 3, 2016, 10:40:20 PM
    Author     : Gustavo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Any+</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/AnyMais/styles/style.css">
</head>
<body class="body-home">
    <div class="container">
        <div class="col-md-4"></div>
        <div class="col-md-4 area-login">
            <img src="/AnyMais/images/logo.png" class="img-responsive center-block"/>
            <br><br>
            <form action="/Anymais/login">
                <input type="text" class="label-field" name="email" placeholder="E-mail" required>
                <br><br>
                <input type="password" class="label-field" name="senha" placeholder="Senha" required>
                <br><br><br>
                <input type="submit" class="label-field" value="Login">
                <br>
                <center>
                    <a href="/AnyMais/cadastrar-usuario.jsp" class="cadastrar-link">Cadastre-se</a> |
                    <a href="" class="senha-link">Esqueci a senha</a>
                </center>
            </form> 

        </div>
        <div class="col-md-4"></div>
    </div>
</body>
</html>
