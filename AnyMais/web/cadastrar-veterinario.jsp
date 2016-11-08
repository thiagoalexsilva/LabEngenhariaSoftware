<%-- 
    Document   : cadastrar-veterinario
    Created on : 07/11/2016, 11:28:50
    Author     : Erica
--%>

<%@page import="java.util.Calendar"%>
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
        
        <%  Veterinario veterinario = null;
            if(session.getAttribute("veterinario") != null){
                veterinario = ((Veterinario) session.getAttribute("veterinario"));
            } 
        %>
        
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

                <div class="principal-cadastra-veterinario">
                    <form id="formVeterinario" action="/AnyMais/veterinario/<%=veterinario == null ? "cadastrado" : "atualizado"%>" method="POST">
                        <% if(veterinario == null){ %>
                            <h3 class="title">Cadastrar Veterinário</h3>
                        <% } 
                            else{ %>
                            <h3 class="title">Atualizar Veterinário</h3>
                        <% } %>
                        <br>                
                        <p class="cadastra-raca">Nome: 
                            <input type="text" class="label-field-nome-veterinario" name="nome" required 
                                   value="<%= veterinario != null ? veterinario.getNome() : "" %>"> </p>
                        <p class="cadastra-raca">CRMV: 
                            <input type="text" class="label-field-crmv crmv" name="crmv" required 
                                   value="<%= veterinario != null ? veterinario.getCrmv(): "" %>"> </p>
                        <p class="cadastra-raca">Observação:</p>
                        <textarea name="observacao" rows="4" cols="50" class="text-observacao-veterinario"><%=veterinario != null ? veterinario.getObservacao() : "" %></textarea> 
                        <br><br>
                        <input type="button" name="cancelar" class="button-cancelar" value="Cancelar">
                        <input type="submit" name="cadastrar" class="button-cadastrar" 
                               value="<%=veterinario == null ? "Cadastrar" : "Atualizar"%>">
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
                        document.getElementById("formVeterinario").submit();
                    }
                    else if(e.target.value === "Atualizar"){
                        var confirma = window.confirm("Deseja confirmar atualização de dados?");
                        if(confirma){
                            document.getElementById("formVeterinario").submit();
                        }
                    }
                });
                
                cancelar.addEventListener("click", function(e){
                    var confirma = window.confirm("Deseja confirmar cancelamento? Os dados preenchidos serão perdidos.");
                    if(confirma){
                        document.getElementById("formVeterinario").action = "/AnyMais/veterinario";
                        document.getElementById("formVeterinario").submit();
                    }
                });

            }
            
            load();
        </script>
        <script src="/AnyMais/scripts/validacao.js?<%=Calendar.getInstance().getTime()%>"></script>
    </body>
</html>
