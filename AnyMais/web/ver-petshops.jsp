<%-- 
    Document   : ver-petshops
    Created on : Oct 3, 2016, 7:14:51 PM
    Author     : Gustavo
--%>

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
                <div class="menu menu-cliente">
                    <img src="/AnyMais/images/profile-image.png" class="menu-profile-image-usuario"/><br>
                    <a href="/AnyMais/usuario/atualizar"><input type="image" src="/AnyMais/images/editar-perfil-button.png" class="menu-editar-perfil" /></a><br>
                    <a href="/AnyMais/usuario/animais"><input type="image" src="/AnyMais/images/meus-pets-button.png" class="menu-meus-pets" /></a><br>
                    <a href="mensagens-cliente.html"><input type="image" src="/AnyMais/images/mensagens-button.png" class="menu-mensagens" /></a><br>
                    <a href="agendamentos-cliente.html"><input type="image" src="/AnyMais/images/agendamento-button.png" class="menu-agendamento" /></a><br>
                    <a href="minhas-avaliacoes.html"><input type="image" src="/AnyMais/images/minhas-avaliacoes-button.png" class="menu-minhas-avaliacoes" /></a><br>
                    <a href="/AnyMais/logout"><input type="image" src="/AnyMais/images/logout-button.png" class="logout-button" /></a><br>
                    <a href="/AnyMais/usuario/encerrar"><input id="encerrar" type="image" src="/AnyMais/images/excluir-button.png" class="logout-button" /></a>
                </div>
                <a href="home-cliente.html"><input type="image" src="/AnyMais/images/home-button2.png" class="menu2-home-usuario" /></a>
                <a href="amigos.html"><input type="image" src="/AnyMais/images/amigos-button.png" class="menu2-amigos-usuario" /></a>
                <a href="petshop.html"><input type="image" src="/AnyMais/images/petshop-button.png" class="menu2-petshop-usuario" /></a><br>
                
                <div class="principal principal-cliente">
                    <form id="formusuarios" action="/AnyMais/pesquisapetshops" method="post">
                        <h3 class="title">Procurar PetShops</h3>
                        <p class="text-procura-petshop"> Nome: <input type="text" name="nome-usuario" class="label-field-procura-petshop" value="<%= session.getAttribute("nome-usuario") != null ? session.getAttribute("nome-usuario") : "" %>"> </p>
                        <p class="text-procura-petshop"> E-mail: <input type="text" name="email-usuario" class="label-field-procura-petshop" value="<%= session.getAttribute("email-usuario") != null ? session.getAttribute("email-usuario") : "" %>"> </p>
                        <p class="text-procura-petshop"> Bairro: <input type="text" name="bairro-usuario" class="label-field-procura-petshop" value="<%= session.getAttribute("bairro-usuario") != null ? session.getAttribute("bairro-usuario") : "" %>"> 
                            <input type="image" name="procuraPetshops" src="/AnyMais/images/search.png" class="search-button" /></p>
                        <br>

                        <table border="1" class="table-racas">
                            <tr>
                                <th class="table-raca-title">Nome</th>
                                <th class="table-raca-title">Email</th>
                                <th class="table-raca-title">Bairro</th>
                            </tr>
                            <%
                                if (session.getAttribute("petshops") instanceof Usuario[]) {
                                    Usuario[] usuarios = (Usuario[]) session.getAttribute("petshops");
                                    for (Usuario usuario : usuarios) {
                                        int id = usuario.getIdPessoa();
                            %>
                                        <tr>
                                            <td><a href="/AnyMais/petshop/<%=id%>"><%= usuario.getPessoa().getNome() %></a></td>
                                            <td><%= usuario.getConta().getEmail() %><br></td>
                                            <td><%= usuario.getPessoa().getBairro() %></td>
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

                // Links de filtro de raças
                
                var filtros = [];
                filtros.push(document.getElementsByName("procuraPetshops")[0]);
                
                for (i = 0; i < filtros.length; i++) {
                    filtros[i].addEventListener("click", function () {
                        document.getElementById("formusuarios").action = "/AnyMais/pesquisapetshops";
                        document.getElementById("formusuarios").submit();
                    });
                }

            }

            load();

        </script>
        <script src="/AnyMais/scripts/validacao.js"></script>
    </body>
</html>
