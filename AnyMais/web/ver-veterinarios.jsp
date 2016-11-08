<%-- 
    Document   : ver-veterinario
    Created on : 07/11/2016, 11:21:00
    Author     : Erica
--%>

<%@page import="model.entity.Veterinario"%>
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
                    <a href="/AnyMais/ver-racas.jsp"><input type="image" src="/AnyMais/images/racas-button.png" class="menu-racas-button" /></a><br>
                    <a href="/AnyMais/ver-vacinas-medicamentos.jsp"><input type="image" src="/AnyMais/images/vacinas-medicamentos-button.png" class="menu-vacinas-button" /></a><br>
                    <input type="image" src="/AnyMais/images/logout-button.png" class="logout-button" />
                </div>
                <div class="principal">
                    <form id="formVeterinario" action="/AnyMais/veterinario" method="post">
                        <h3 class="title">Veterinários</h3>
                        <input type="text" name="nome" class="label-field-racas" value="<%= session.getAttribute("nome-raca") != null ? session.getAttribute("nome-raca") : "" %>">
                        <input type="image" name="procura-veterinario" src="/AnyMais/images/search.png" class="search-button" />
                        <input type="image" name="adicionar-veterinario" src="/AnyMais/images/stethoscope.png" class="adicionar-pet-button" />
                        <br>
                        <br>
                        <table border="1" class="table-racas">
                            <tr>
                                <th class="table-raca-title">Nome</th>
                                <th class="table-raca-title">CRMV</th>
                                <th class="table-raca-title"></th>
                                <th class="table-raca-title"></th>
                            </tr>
                            <input type="hidden" name="excluido" value=""/>
                            <input type="hidden" name="atualizado" value=""/>
                            <%
                                if (session.getAttribute("veterinario") instanceof Veterinario[]) {
                                    Veterinario[] veterinarios = (Veterinario[]) session.getAttribute("veterinario");
                                    for (Veterinario v : veterinarios) {
                                        int id = v.getIdVeterinario();
                            %>
                                        <tr>
                                            <td><%= v.getNome() %></td>
                                            <td><%= v.getCrmv() %><br></td>
                                            <td>
                                                <center><center><input name="atualizar" id="<%= v.getIdVeterinario() %>" type="image" src="/AnyMais/images/edit.png" class="excluir-button"></center>
                                            </td>
                                            <td>
                                                <center><input name="excluir" id="<%= v.getIdVeterinario() %>" type="image" src="/AnyMais/images/excluir.png" class="excluir-button"></center>
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
            function load() {

                // Links de exclusão de raças

                var excluir = document.getElementsByName("excluir");
                var i = 0;
                for (i = 0; i < excluir.length; i++) {
                    excluir[i].addEventListener('click', function (e) {
                        var confirma = window.confirm("Deseja confirmar exclusão?");
                        if(confirma){
                            document.getElementsByName("excluido")[0].value = e.target.id;
                            document.getElementById("formVeterinario").action = "/AnyMais/veterinario/excluido";
                            document.getElementById("formVeterinario").submit();
                        }
                    });
                }
                
                var atualizar = document.getElementsByName("atualizar");
                var i = 0;
                for (i = 0; i < atualizar.length; i++) {
                    atualizar[i].addEventListener('click', function (e) {
                        document.getElementsByName("atualizado")[0].value = e.target.id;
                        document.getElementById("formVeterinario").action = "/AnyMais/veterinario/atualizar";
                        document.getElementById("formVeterinario").submit();
                    });
                }
                
                var adicionarRaca = document.getElementsByName("adicionar-veterinario")[0];
                adicionarRaca.addEventListener("click", function(){
                    document.getElementById("formVeterinario").action = "/AnyMais/veterinario/cadastrar";
                    document.getElementById("formVeterinario").submit();
                });
                
                // Links de filtro de raças
                
                var filtros = [];
                filtros.push(document.getElementsByName("procura-veterinario")[0]);
                
                for (i = 0; i < filtros.length; i++) {
                    filtros[i].addEventListener("click", function () {
                        document.getElementById("formVeterinario").action = "/AnyMais/veterinario";
                        document.getElementById("formVeterinario").submit();
                    });
                }

            }

            load();

        </script>
    </body>
</html>
