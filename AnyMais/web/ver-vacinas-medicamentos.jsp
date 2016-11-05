<%-- 
    Document   : ver-vacinas-medicamentos
    Created on : 26/10/2016, 03:56:56
    Author     : ana
--%>
<%@page import="model.entity.VacinasMedicamentos"%>
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
                    <form id="formmedicamentos" action="/AnyMais/medicamentos" method="post">
                    <h3 class="title">Vacinas e Medicamentos</h3>
                    <input type="text" name="nome-medicamento" class="label-field-racas" name="procuraMedicamentos" value="<% out.print(session.getAttribute("nome-medicamento") != null ? session.getAttribute("nome-medicamento") : ""); %>">
                    <input type="image" name="procurar-medicamento" src="images/search.png" class="search-button" />
                    <input type="image" name="adicionar-medicamento" src="images/adicionar-vacina.png" class="adicionar-medicamento-button" />
                    <br>
                    <input type="checkbox" name="tipo-pet" class="raca-tipo-pet primeiro-pet" value="cachorro"
                           <% out.print(session.getAttribute("tipo-pet-c") != null ? "checked" : ""); %> > Cachorro
                    <input type="checkbox" name="tipo-pet" class="raca-tipo-pet" value="gato"
                           <% out.print(session.getAttribute("tipo-pet-g") != null ? "checked" : ""); %> > Gato
                    
                    <br><br>
                    
                    <table border="1" class="table-racas">
                        <tr>
                          <th class="table-raca-title">Espécie</th>
                          <th class="table-raca-title">Nome</th>
                          <th class="table-raca-title">Periodicidade</th>
                          <th class="table-raca-title"></th>
                        </tr>
                        <input type="hidden" name="excluido" value=""/>
                        <input type="hidden" name="atualizado" value=""/>
                          
                        
                        <%
                                if (session.getAttribute("medicamentos") instanceof VacinasMedicamentos[]) {
                                    VacinasMedicamentos[] medicamentos = (VacinasMedicamentos[]) session.getAttribute("medicamentos");
                                    for (VacinasMedicamentos medicamento : medicamentos) {
                                        int id = medicamento.getIdVacinasMedicamentos();
                            %>
                        <tr>
                          <td><% out.print(medicamento.getTipoAnimal()); %></td>
                          <td><% out.print(medicamento.getNome()); %></td>
                          <td><% out.print(medicamento.getPeriodicidade()); %></td>
                          <td>
                            <center><input name="excluir" id="<% out.print(medicamento.getIdVacinasMedicamentos()); %>" type="image" src="images/excluir.png" class="excluir-button" /></center>
                          </td>
                          <td>
                            <center><input name="atualizar" id="<% out.print(medicamento.getIdVacinasMedicamentos()); %>" type="image" src="images/edit.png" class="excluir-button" /></center>
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
                        document.getElementsByName("excluido")[0].value = e.target.id;
                        document.getElementById("formmedicamentos").action = "/AnyMais/medicamentos/excluido";
                        document.getElementById("formmedicamentos").submit();
                    });
                }
                
                var atualizar = document.getElementsByName("atualizar");
                var i = 0;
                for (i = 0; i < atualizar.length; i++) {
                    atualizar[i].addEventListener('click', function (e) {
                        document.getElementsByName("atualizado")[0].value = e.target.id;
                        document.getElementById("formmedicamentos").action = "/AnyMais/medicamentos/atualizar";
                        document.getElementById("formmedicamentos").submit();
                    });
                }
                
                var adicionarRaca = document.getElementsByName("adicionar-medicamento")[0];
                adicionarRaca.addEventListener("click", function(){
                    document.getElementById("formmedicamentos").action = "/AnyMais/medicamentos/cadastrar";
                    document.getElementById("formmedicamentos").submit();
                });
                
                // Links de filtro de raças
                
                var filtros = [];
                filtros.push(document.getElementsByName("procurar-medicamento)[0]);
                filtros.push(document.getElementsByName("tipo-pet-c")[0]);
                filtros.push(document.getElementsByName("tipo-pet-g")[0]);
                
                for (i = 0; i < filtros.length; i++) {
                    filtros[i].addEventListener("click", function () {
                        document.getElementById("formmedicamentos").action = "/AnyMais/medicamentos";
                        document.getElementById("formmedicamentos").submit();
                    });
                }

            }

            load();

        </script>
    </body>
</html>