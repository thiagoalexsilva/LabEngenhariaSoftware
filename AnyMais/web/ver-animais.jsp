<%-- 
    Document   : ver-animais
    Created on : Oct 3, 2016, 7:14:51 PM
    Author     : Gustavo
--%>

<%@page import="model.entity.Animal"%>
<%@page import="model.entity.Usuario"%>
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
    <body>
        <div class="container c-header">
            <header>
                <img src="/AnyMais/images/logo.png" class="img-responsive logo-header"/>
            </header>
        </div>
        <%  
            boolean sucesso = false;
            boolean falha = false;
            
            if (session.getAttribute("status") != null){
                if (session.getAttribute("status").toString().equals("sucesso")) { 
                    sucesso = true;
                } else if (session.getAttribute("status").equals("falha")) { 
                    falha = true;
                }
            }
        %>        
        <div class="mensagem <%=sucesso ? "sucesso" : falha ? "falha" : ""%>">
            <%=sucesso ? "Sucesso!" : falha ? "Falha!" : ""%>
        </div>
        <div class="container">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <div class="menu">
                    <a href="/AnyMais/racas"><input type="image" src="/AnyMais/images/racas-button.png" class="menu-racas-button" /></a><br>
                    <a href="/AnyMais/vacinasMedicamentos"><input type="image" src="/AnyMais/images/vacinas-medicamentos-button.png" class="menu-vacinas-button" /></a><br>
                    <input type="image" src="/AnyMais/images/logout-button.png" class="logout-button" />
                </div>
                <div class="principal-usuarios">
                    <form id="formanimais" action="/AnyMais/usuario/animais" method="post">
                        <h3 class="title">Animais</h3>
                        <input type="image" name="adicionar-animal" src="/AnyMais/images/adicionar-pet.png" class="adicionar-pet-button" />
                        <br><br>

                        <table border="1" class="table-racas">
                            <tr>
                                <th class="table-raca-title">Nome</th>
                                <th class="table-raca-title">Tipo</th>
                                <th class="table-raca-title">Raça</th>
                                <th></th>
                                <th></th>
                            </tr>
                            <input type="hidden" name="excluido" value=""/>
                            <input type="hidden" name="atualizado" value=""/>
                            <%
                                if (session.getAttribute("animais") instanceof Animal[]) {
                                    Animal[] animais = (Animal[]) session.getAttribute("animais");
                                    for (Animal animal : animais) {
                                        int id = animal.getIdAnimal();
                            %>
                                        <tr>
                                            <td><%= animal.getNome() %></td>
                                            <td><%= animal.getTipoAnimal().getNomeTipoAnimal() %><br></td>
                                            <td><%= animal.getRaca().getNomeRaca() %></td>
                                            <td>
                                                <center><input name="excluir" id="<%= animal.getIdAnimal() %>" type="image" src="/AnyMais/images/excluir.png" class="excluir-button"></center>
                                            </td>
                                            <td>
                                                <center><input name="atualizar" id="<%= animal.getIdAnimal() %>" type="image" src="/AnyMais/images/edit.png" class="excluir-button"></center>
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
            function load() {

                var excluir = document.getElementsByName("excluir");
                var i = 0;
                for (i = 0; i < excluir.length; i++) {
                    excluir[i].addEventListener('click', function (e) {
                        var confirma = window.confirm("Deseja confirmar exclusão?");
                        if(confirma){
                            document.getElementsByName("excluido")[0].value = e.target.id;
                            document.getElementById("formanimais").action = "/AnyMais/usuario/animais/excluido";
                            document.getElementById("formanimais").submit();
                        }
                    });
                }
                
                var atualizar = document.getElementsByName("atualizar");
                var i = 0;
                for (i = 0; i < atualizar.length; i++) {
                    atualizar[i].addEventListener('click', function (e) {
                        document.getElementsByName("atualizado")[0].value = e.target.id;
                        document.getElementById("formanimais").action = "/AnyMais/usuario/animais/atualizar";
                        document.getElementById("formanimais").submit();
                    });
                }
                
                var adicionarAnimal = document.getElementsByName("adicionar-animal")[0];
                adicionarAnimal.addEventListener("click", function(){
                    document.getElementById("formanimais").action = "/AnyMais/usuario/animais/cadastrar";
                    document.getElementById("formanimais").submit();
                });

            }

            load();

        </script>
        <script src="/AnyMais/scripts/validacao.js"></script>
    </body>
</html>
