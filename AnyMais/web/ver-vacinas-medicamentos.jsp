<%-- 
    Document   : ver-vacinas-medicamentos
    Created on : 26/10/2016, 03:56:56
    Author     : ana
--%>
<%@page import="model.entity.VacinaMedicamento"%>
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
                    <h3 class="title">Vacinas e Medicamentos</h3>
                    <input type="text" class="label-field-racas" name="procuraRacas">
                    <input type="image" src="images/search.png" class="search-button" />
                    <a href="cadastrar-vacinas-medicamentos.html"><input type="image" src="images/adicionar-vacina.png" class="adicionar-pet-button" /></a>
                    <br>
                    <input type="checkbox" name="tipo-pet" class="raca-tipo-pet primeiro-pet" value="cachorro"> Cachorro
                    <input type="checkbox" name="tipo-pet" class="raca-tipo-pet" value="gato"> Gato
                    
                    <br><br>
                    
                    <table border="1" class="table-racas">
                        <tr>
                          <th class="table-raca-title">Espécie</th>
                          <th class="table-raca-title">Nome</th>
                          <th class="table-raca-title">Periodicidade</th>
                          <th class="table-raca-title"></th>
                        </tr>
                        <tr>
                          <td>Cachorro</td>
                          <td>Bagovac Raiva<br></td>
                          <td>12 meses</td>
                          <td>
                            <center><input type="image" src="images/excluir.png" class="excluir-button" /></center>
                          </td>
                        </tr>
                        <tr>
                          <td></td>
                          <td></td>
                          <td></td>
                          <td></td>
                        </tr>
                      </table>
                </div>
            </div>
            <div class="col-md-2"></div>
        </div>
        <div class="container c-footer">
            <footer></footer>
        </div>
    </body>
</html>