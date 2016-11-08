<%-- 
    Document   : ver-servicos
    Created on : Nov 5, 2016, 3:41:05 PM
    Author     : Gustavo
--%>

<%@page import="model.entity.TipoServico"%>
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
                <div class="menu menu-cliente">
                    <img src="/AnyMais/images/profile-image.png" class="menu-profile-image"/><br>
                    <a href="/AnyMais/editar-cadastro-petshop.jsp"><input type="image" src="/AnyMais/images/editar-perfil-button.png" class="menu-editar-perfil" /></a><br>
                    <a href="/AnyMais/servicos.jsp"><input type="image" src="/AnyMais/images/servicos-button.png" class="menu-servicos" /></a><br>
                    <a href="/AnyMais/mensagens-petshop.jsp"><input type="image" src="/AnyMais/images/mensagens-button.png" class="menu-mensagens" /></a><br>
                    <a href="/AnyMais/agendamentos-petshop.jsp"><input type="image" src="/AnyMais/images/agendamento-button.png" class="menu-agendamento" /></a><br>
                    <a href="/AnyMais/"><input type="image" src="/AnyMais/images/logout-button.png" class="logout-button" /></a>
                </div>
                <a href="/AnyMais/home-petshop.jsp"><input type="image" src="/AnyMais/images/home-button2.png" class="menu2-home" /></a>
                <!--<a href="/AnyMais/avaliacoes-petshop.jsp"><input type="image" src="/AnyMais/images/avaliacoes-button.png" class="menu2-avaliacoes" /></a>-->

                
                <div class="principal-servicos">
                    <form id="formservicos" action="/AnyMais/petshop/servicos" method="post">
                        <h3 class="title">Meus Serviços</h3>
                        <input type="text" name="nomeServico" class="label-field-servico" value="<%= session.getAttribute("nomeServico") != null ? session.getAttribute("nomeServico") : "" %>">
                        <input type="image" name="procuraServicos" src="/AnyMais/images/search.png" class="search-button" />
                        <input type="image" name="adicionarServico" src="/AnyMais/images/adicionar-servicos.png" class="adicionar-pet-button" />
                        <br>

                        <br><br>

                        <table border="1" class="table-tipoServicos">
                            <tr>
                                <th class="table-raca-title">Serviço</th>
                                <th class="table-raca-title">Duração</th>
                                <th class="table-raca-title">Valor</th>
                                <th class="table-raca-title"></th>
                                <th class="table-raca-title"></th>
                            </tr>
                            <input type="hidden" name="excluido" value=""/>
                            <input type="hidden" name="atualizado" value=""/>
                            <%
                                if (session.getAttribute("servicos") instanceof TipoServico[]) {
                                    TipoServico[] tiposServico = (TipoServico[]) session.getAttribute("servicos");
                                    for (TipoServico tipoServico : tiposServico) {
                                        int id = tipoServico.getIdTipoServico();
                            %>
                                        <tr>
                                            <td><%= tipoServico.getNome() %></td>
                                            <td><%= tipoServico.getDuracao() %><br></td>
                                            <td><%= tipoServico.getValor() %></td>
                                            <td>
                                                <center><input name="excluir" id="<%= tipoServico.getIdTipoServico() %>" type="image" src="/AnyMais/images/excluir.png" class="excluir-button"></center>
                                            </td>
                                            <td>
                                                <center><input name="atualizar" id="<%= tipoServico.getIdTipoServico() %>" type="image" src="/AnyMais/images/edit.png" class="excluir-button"></center>
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
                            document.getElementById("formservicos").action = "/AnyMais/petshop/servicos/excluido";
                            document.getElementById("formservicos").submit();
                        }
                    });
                }
                
                var atualizar = document.getElementsByName("atualizar");
                var i = 0;
                for (i = 0; i < atualizar.length; i++) {
                    atualizar[i].addEventListener('click', function (e) {
                        document.getElementsByName("atualizado")[0].value = e.target.id;
                        document.getElementById("formservicos").action = "/AnyMais/petshop/servicos/atualizar";
                        document.getElementById("formservicos").submit();
                    });
                }
                
                var adicionarRaca = document.getElementsByName("adicionarServico")[0];
                adicionarRaca.addEventListener("click", function(){
                    document.getElementById("formservicos").action = "/AnyMais/petshop/servicos/cadastrar";
                    document.getElementById("formservicos").submit();
                });
                
                // Links de filtro de raças
                
                var filtros = [];
                filtros.push(document.getElementsByName("procuraServicos")[0]);
                
                for (i = 0; i < filtros.length; i++) {
                    filtros[i].addEventListener("click", function () {
                        document.getElementById("formservicos").action = "/AnyMais/petshop/servicos";
                        document.getElementById("formservicos").submit();
                    });
                }

            }

            load();

        </script>
    </body>
</html>
