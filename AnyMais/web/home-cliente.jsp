<%-- 
    Document   : home-cliente
    Created on : 26/10/2016, 03:58:58
    Author     : Erica
--%>

<%@page import="model.entity.Usuario"%>
<%@page import="model.entity.Pessoa"%>
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
        <link rel="stylesheet" type="text/css" href="styles/style.css">
    </head>
    <body>
        <div class="container c-header">
            <header>
                <img src="images/logo.png" class="img-responsive logo-header"/>
            </header>
        </div>
        <%  
            Usuario usuario = ((Usuario) session.getAttribute("usuario"));
            
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
        <div>
            
        </div>
        <div class="container">
            <form id="formusuario" action="/AnyMais/usuario" method="post">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <div class="menu menu-cliente">
                    <img src="/AnyMais/images/profile-image.png" class="menu-profile-image-usuario"/><br>
                    <a id="atualizar" href="/AnyMais/usuario/atualizar"><input type="image" src="/AnyMais/images/editar-perfil-button.png" class="menu-editar-perfil" /></a><br>
                    <a id="animais" href="/AnyMais/usuario/animais"><input type="image" src="/AnyMais/images/meus-pets-button.png" class="menu-meus-pets" /></a><br>
                    <a id="mensagens" href="mensagens-cliente.html"><input type="image" src="/AnyMais/images/mensagens-button.png" class="menu-mensagens" /></a><br>
                    <a id="agendamentos" href="agendamentos-cliente.html"><input type="image" src="/AnyMais/images/agendamento-button.png" class="menu-agendamento" /></a><br>
                    <a id="avaliacoes" href="minhas-avaliacoes.html"><input type="image" src="/AnyMais/images/minhas-avaliacoes-button.png" class="menu-minhas-avaliacoes" /></a><br>
                    <a id="logout" href="/AnyMais/logout"><input type="image" src="/AnyMais/images/logout-button.png" class="logout-button" /></a><br>
                    <a id="encerrar" href="/AnyMais/usuario/encerrar"><input id="encerrar" type="image" src="/AnyMais/images/excluir-button.png" class="logout-button" /></a>
                </div>
                <a id="usuario" href="/AnyMais/usuario"><input type="image" src="/AnyMais/images/home-button2.png" class="menu2-home-usuario" /></a>
                <a id="amigos" href="amigos.html"><input type="image" src="/AnyMais/images/amigos-button.png" class="menu2-amigos-usuario" /></a>
                <a id="petshops" href="petshop.html"><input type="image" src="/AnyMais/images/petshop-button.png" class="menu2-petshop-usuario" /></a><br>
                <div class="principal principal-cliente">
                    <h3 class="saudacao-cliente">
                        <% if (usuario != null){ %>
                        Oieeee, <%= usuario != null ? usuario.getPessoa().getNome() : "" %>.
                    <% } %>
                    </h3>
                </div>
            </div>
            <div class="col-md-2"></div>
            </form>
        </div>
        <div class="container c-footer">
            <footer></footer>
        </div>
        <script>
            function load(){
                var i=0;
                var a_tags = document.getElementsByTagName("a");
                var form = document.getElementById("formusuario");
                
                for(i=0; i<a_tags.length; i++ ){
                    a_tags[i].addEventListener("click", function(e){
                        form.action = e.currentTarget.href;
                        form.submit();
                    });
                }
        
                var encerrar = document.getElementById("encerrar");
                encerrar.addEventListener("click", function(e){
                    var confirma = window.confirm("Deseja confirmar encerramento de conta?");
                    if(confirma){
                        document.getElementById("formusuario").action = "/AnyMais/usuario/encerrar";
                        document.getElementById("formusuario").submit();
                    }
                });
            }
            
            load();
        </script>
    </body>
</html>
