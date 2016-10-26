<%-- 
    Document   : home-petshop
    Created on : 25/10/2016, 23:31:58
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
                    <a href="editar-cadastro-petshop.html"><input type="image" src="images/editar-perfil-button.png" class="menu-editar-perfil" /></a><br>
                    <a href="servicos.html"><input type="image" src="images/servicos-button.png" class="menu-servicos" /></a><br>
                    <a href="mensagens-cliente.html"><input type="image" src="images/mensagens-button.png" class="menu-mensagens" /></a><br>
                    <a href="agendamentos-cliente.html"><input type="image" src="images/agendamento-button.png" class="menu-agendamento" /></a><br>
                    <input type="image" src="images/logout-button.png" class="logout-button" />
                </div>
                <a href="home-petshop.html"><input type="image" src="images/home-button2.png" class="menu2-home" /></a>
                <a href="avaliacoes.html"><input type="image" src="images/avaliacoes-button.png" class="menu2-avaliacoes" /></a>
                <div class="principal principal-petshop">
                    
                </div>
            </div>
            <div class="col-md-2"></div>
        </div>
        <div class="container c-footer">
            <footer></footer>
        </div>
    </body>
</html>
