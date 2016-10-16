<%-- 
    Document   : cadastrar-racas
    Created on : Oct 3, 2016, 9:09:14 PM
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
                <div class="menu">
                    <a href="ver-racas.html"><input type="image" src="images/racas-button.png" class="menu-racas-button" /></a><br>
                    <a href="ver-vacinas-medicamentos.html"><input type="image" src="images/vacinas-medicamentos-button.png" class="menu-vacinas-button" /></a><br>
                    <input type="image" src="images/logout-button.png" class="logout-button" />
                </div>
                <div class="principal">
                    <h3 class="title">Cadastrar Raças</h3>
                    <input type="radio" name="tipo-pet" value="cachorro" class="cadastra-raca"> Cachorro
                    <input type="radio" name="tipo-pet" value="gato"> Gato<br>
                    <br>
                    <form action="">
                        <p class="cadastra-raca">Raça:
                            <input type="text" class="label-field-raca" name="nome-raca"> </p>
                        <p class="cadastra-raca">Porte:
                            <select name="porte" class="seleciona-porte">
                                <option value="-">-</option>
                                <option value="pequeno">Pequeno</option>
                                <option value="medio">Médio</option>
                                <option value="grande">Grande</option>
                            </select>
                        <p class="cadastra-raca">Observação:</p><br>
                        <textarea name="observacao" rows="4" cols="50"></textarea> 
                        <br>
                        <input type="submit" class="button-cancelar" value="Cancelar">
                        <input type="submit" class="button-cadastrar" value="Cadastrar">
                        <br>
                    </form> 
                </div>
            </div>
            <div class="col-md-2"></div>
        </div>
        <div class="container c-footer">
            <footer></footer>
        </div>
    </body>
</html>