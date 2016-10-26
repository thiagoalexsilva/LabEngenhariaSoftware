<%-- 
    Document   : home-cliente
    Created on : 26/10/2016, 03:58:58
    Author     : Erica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Any+</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="styles/style.css">
    </head>
    <body>
        <div class="container c-header">
            <header>
                <img src="images/logo.png" class="img-responsive logo-header"/>
            </header>
        </div>
        
        <div class="container">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <div class="menu menu-cliente">
                    <img src="images/profile-image.png" class="menu-profile-image"/><br>
                    <a href="editar-cadastro-cliente.html"><input type="image" src="images/editar-perfil-button.png" class="menu-editar-perfil" /></a><br>
                    <a href="meus-pets.html"><input type="image" src="images/meus-pets-button.png" class="menu-meus-pets" /></a><br>
                    <a href="mensagens-cliente.html"><input type="image" src="images/mensagens-button.png" class="menu-mensagens" /></a><br>
                    <a href="agendamentos-cliente.html"><input type="image" src="images/agendamento-button.png" class="menu-agendamento" /></a><br>
                    <a href="minhas-avaliacoes.html"><input type="image" src="images/minhas-avaliacoes-button.png" class="menu-minhas-avaliacoes" /></a><br>
                    <input type="image" src="images/logout-button.png" class="logout-button" />
                </div>
                <a href="home-cliente.html"><input type="image" src="images/home-button2.png" class="menu2-home" /></a>
                <a href="amigos.html"><input type="image" src="images/amigos-button.png" class="menu2-amigos" /></a>
                <a href="petshop.html"><input type="image" src="images/petshop-button.png" class="menu2-petshop" /></a><br>
                <div class="principal principal-cliente">
                    
                </div>
            </div>
            <div class="col-md-2"></div>
        </div>
        <div class="container c-footer">
            <footer></footer>
        </div>
    </body>
</html>
