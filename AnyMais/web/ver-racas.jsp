<%-- 
    Document   : ver-racas
    Created on : Oct 3, 2016, 7:14:51 PM
    Author     : Gustavo
--%>

<%@page import="model.entity.Raca"%>
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
                    <a href="/AnyMais/ver-racas.jsp"><input type="image" src="/AnyMais/images/racas-button.png" class="menu-racas-button" /></a><br>
                    <a href="/AnyMais/ver-vacinas-medicamentos.jsp"><input type="image" src="/AnyMais/images/vacinas-medicamentos-button.png" class="menu-vacinas-button" /></a><br>
                    <input type="image" src="/AnyMais/images/logout-button.png" class="logout-button" />
                </div>
                <div class="principal-racas">
                    <form id="formracas" action="/AnyMais/racas" method="post">
                        <h3 class="title">Raças</h3>
                        <input type="text" name="nome-raca" class="label-field-racas" value="<%= session.getAttribute("nome-raca") != null ? session.getAttribute("nome-raca") : "" %>">
                        <input type="image" name="procuraRacas" src="/AnyMais/images/search.png" class="search-button" />
                        <input type="image" name="adicionar-raca" src="/AnyMais/images/adicionar-pet.png" class="adicionar-pet-button" />
                        <br>
                        <input type="checkbox" name="tipo-pet-c" class="raca-tipo-pet primeiro-pet" value="cachorro" 
                               <%= session.getAttribute("tipo-pet-c") != null ? "checked" : "" %> > <filtro class="raca-filtro">Cachorro</filtro>
                        <input type="checkbox" name="tipo-pet-g" class="raca-tipo-pet" value="gato"
                               <%= session.getAttribute("tipo-pet-g") != null ? "checked" : "" %> > <filtro class="raca-filtro">Gato</filtro>

                        <input type="checkbox" name="porte-pet-p" class="porte-pet primeiro-porte" value="pequeno" 
                               <%= session.getAttribute("porte-pet-p") != null ? "checked" : "" %> > <filtro class="raca-filtro">Pequeno</filtro>
                        <input type="checkbox" name="porte-pet-m" class="porte-pet" value="medio" 
                               <%= session.getAttribute("porte-pet-m") != null ? "checked" : "" %> > <filtro class="raca-filtro">Médio</filtro>
                        <input type="checkbox" name="porte-pet-g" class="porte-pet" value="grande" 
                               <%= session.getAttribute("porte-pet-g") != null ? "checked" : "" %> > <filtro class="raca-filtro">Grande</filtro><br>

                        <br><br>

                        <table border="1" class="table-racas">
                            <tr>
                                <th class="table-raca-title">Espécie</th>
                                <th class="table-raca-title">Raça</th>
                                <th class="table-raca-title">Porte</th>
                                <th class="table-raca-title"></th>
                                <th class="table-raca-title"></th>
                            </tr>
                            <input type="hidden" name="excluido" value=""/>
                            <input type="hidden" name="atualizado" value=""/>
                            <%
                                if (session.getAttribute("racas") instanceof Raca[]) {
                                    Raca[] racas = (Raca[]) session.getAttribute("racas");
                                    for (Raca raca : racas) {
                                        int id = raca.getIdRaca();
                            %>
                                        <tr>
                                            <td><%= raca.getTipoAnimal().getNomeTipoAnimal() %></td>
                                            <td><%= raca.getNomeRaca() %><br></td>
                                            <td><%= raca.getPorte() %></td>
                                            <td>
                                                <center><input name="excluir" id="<%= raca.getIdRaca() %>" type="image" src="/AnyMais/images/excluir.png" class="excluir-button"></center>
                                            </td>
                                            <td>
                                                <center><input name="atualizar" id="<%= raca.getIdRaca() %>" type="image" src="/AnyMais/images/edit.png" class="excluir-button"></center>
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
                            document.getElementById("formracas").action = "/AnyMais/racas/excluido";
                            document.getElementById("formracas").submit();
                        }
                    });
                }
                
                var atualizar = document.getElementsByName("atualizar");
                var i = 0;
                for (i = 0; i < atualizar.length; i++) {
                    atualizar[i].addEventListener('click', function (e) {
                        document.getElementsByName("atualizado")[0].value = e.target.id;
                        document.getElementById("formracas").action = "/AnyMais/racas/atualizar";
                        document.getElementById("formracas").submit();
                    });
                }
                
                var adicionarRaca = document.getElementsByName("adicionar-raca")[0];
                adicionarRaca.addEventListener("click", function(){
                    document.getElementById("formracas").action = "/AnyMais/racas/cadastrar";
                    document.getElementById("formracas").submit();
                });
                
                // Links de filtro de raças
                
                var filtros = [];
                filtros.push(document.getElementsByName("procuraRacas")[0]);
                filtros.push(document.getElementsByName("tipo-pet-c")[0]);
                filtros.push(document.getElementsByName("tipo-pet-g")[0]);
                filtros.push(document.getElementsByName("porte-pet-p")[0]);
                filtros.push(document.getElementsByName("porte-pet-m")[0]);
                filtros.push(document.getElementsByName("porte-pet-g")[0]);
                
                for (i = 0; i < filtros.length; i++) {
                    filtros[i].addEventListener("click", function () {
                        document.getElementById("formracas").action = "/AnyMais/racas";
                        document.getElementById("formracas").submit();
                    });
                }

            }

            load();

        </script>
        <script src="/AnyMais/scripts/validacao.js"></script>
    </body>
</html>
