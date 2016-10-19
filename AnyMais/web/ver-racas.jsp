<%-- 
    Document   : ver-racas
    Created on : Oct 3, 2016, 7:14:51 PM
    Author     : Gustavo
--%>

<%@page import="model.Raca"%>
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
        <div>
            <% if (session.getAttribute("status") != null)
                    if (session.getAttribute("status").toString().equals("sucesso")) { %>
            Sucesso!
            <% } else if (session.getAttribute("status").equals("falha")) { %>
            Falha!
            <% }
            %>
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
                    <h3 class="title">Raças</h3>
                    <input type="text" class="label-field-racas" name="procuraRacas">
                    <input type="image" src="images/search.png" class="search-button" />
                    <a href="/AnyMais/racas/cadastrar"><input type="image" src="images/adicionar-pet.png" class="adicionar-pet-button" /></a>
                    <br>
                    <input type="checkbox" name="tipo-pet" class="raca-tipo-pet primeiro-pet" value="cachorro"> Cachorro
                    <input type="checkbox" name="tipo-pet" class="raca-tipo-pet" value="gato"> Gato

                    <input type="checkbox" name="porte-pet" class="porte-pet primeiro-porte" value="pequeno"> Pequeno
                    <input type="checkbox" name="porte-pet" class="porte-pet" value="medio"> Médio
                    <input type="checkbox" name="porte-pet" class="porte-pet" value="grande"> Grande<br>

                    <br><br>

                    <form id="formracas" action="/AnyMais/racas" method="post">
                    <table border="1" class="table-racas">
                        <tr>
                            <th class="table-raca-title">Espécie</th>
                            <th class="table-raca-title">Raça</th>
                            <th class="table-raca-title">Porte</th>
                            <th class="table-raca-title"></th>
                        </tr>
                        <input type="hidden" name="excluido" value=""/>
                        <%
                            if (session.getAttribute("racas") instanceof Raca[]) {
                                Raca[] racas = (Raca[]) session.getAttribute("racas");
                                for (Raca raca : racas) { 
                                    int id = raca.getId();
                        %>
                        <tr>
                            <td><% out.print(raca.getTipoAnimal()); %></td>
                            <td><% out.print(raca.getNomeRaca()); %><br></td>
                            <td><% out.print(raca.getPorte()); %></td>
                            <td>
                        <center><input name="image" id="<% out.print(raca.getId()); %>" type="image" src="images/excluir.png" class="excluir-button"></center>
                        </td>

                        </tr>
                        <%      }
                        } else {
                        %>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <%  }%>
                    </table>
                </div>
            </div>
            <div class="col-md-2"></div>
        </div>
        <div class="container c-footer">
            <footer></footer>
        </div>
        <script>
            
            
            
            var imagens = document.getElementsByName("image");
            var i=0;
            for(i=0; i<imagens.length; i++){
                imagens[i].addEventListener = function(e){
                    document.getElementsByName("excluido")[0].value = e.target.id;
                    document.getElementById("formracas").action = "/AnyMais/racas/excluido";
                    document.getElementById("formracas").submit();                
                };
            }

        </script>
                        
    </body>
</html>
