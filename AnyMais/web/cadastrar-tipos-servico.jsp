<%-- 
    Document   : cadastrar-tipos-servico
    Created on : Oct 3, 2016, 9:09:14 PM
    Author     : Gustavo
--%>

<%@page import="model.entity.TipoServico"%>
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
        <%  TipoServico tipoServico = null;
            if(session.getAttribute("servico") != null){
                tipoServico = ((TipoServico) session.getAttribute("servico"));
            } 
        %>
        
        <div class="container">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <div class="menu">
                    <a href="/AnyMais/petshop/servicos"><input type="image" src="/AnyMais/images/raca-button.png" class="menu-tiposServico-button" /></a><br>
                    <a href="/AnyMais/ver-vacinas-medicamentos.html"><input type="image" src="/AnyMais/images/vacinas-medicamentos-button.png" class="menu-vacinas-button" /></a><br>
                    <input type="image" src="/AnyMais/images/logout-button.png" class="logout-button" />
                </div>
                <div class="principal">
                    <form id="formservico" action="/AnyMais/petshop/servicos/<%=tipoServico == null ? "cadastrado" : "atualizado"%>" method="POST">
                        <% if(tipoServico == null){ %>
                            <h3 class="title">Cadastrar Serviço</h3>
                        <% } 
                            else{ %>
                            <h3 class="title">Atualizar Serviço</h3>
                        <% } %>
                        <br>                    
                        <p class="cadastra-raca">Serviço:
                            <input type="text" class="label-field-raca" name="nomeServico" required 
                                   value="<%= tipoServico != null ? tipoServico.getNome() : "" %>"> </p>
                        <p class="cadastra-raca">Duração:
                            <input type="text" class="label-field-raca" name="duracao" required 
                                   value="<%= tipoServico != null ? Integer.toString(tipoServico.getDuracao()) : "" %>"> </p>
                        <p class="cadastra-raca">Valor:
                            <input type="text" class="label-field-raca" name="valor" required 
                                   value="<%= tipoServico != null ? Double.toString(tipoServico.getValor()) : "" %>"> </p>
                        <p class="cadastra-raca">Observação:</p><br>
                        <textarea name="observacao" rows="4" cols="50"><%=tipoServico != null ? tipoServico.getObservacao() : "" %></textarea> 
                        <br>
                        <input type="button" name="cancelar" class="button-cancelar" value="Cancelar">
                        <input type="submit" name="cadastrar" class="button-cadastrar" 
                               value="<%=tipoServico == null ? "Cadastrar" : "Atualizar"%>">
                        <br>
                    </form> 
                </div>
            </div>
            <div class="col-md-2"></div>
        </div>
        <div class="container c-footer">
            <footer></footer>
        </div>
        <script>
            function load(){
                var cadastrar = document.getElementsByName("cadastrar")[0];
                var cancelar = document.getElementsByName("cancelar")[0];
                
                cadastrar.addEventListener("submit", function(e){
                    if(e.target.value === "Cadastrar"){
                        document.getElementById("formservico").submit();
                    }
                    else if(e.target.value === "Atualizar"){
                        var confirma = window.confirm("Deseja confirmar atualização de dados?");
                        if(confirma){
                            document.getElementById("formservico").submit();
                        }
                    }
                });
                
                cancelar.addEventListener("click", function(e){
                    var confirma = window.confirm("Deseja confirmar cancelamento? Os dados preenchidos serão perdidos.");
                    if(confirma){
                        document.getElementById("formservico").action = "/AnyMais/petshop/servicos";
                        document.getElementById("formservico").submit();
                    }
                });

            }
            
            load();
        </script>
    </body>
</html>
