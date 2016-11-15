<%-- 
    Document   : editar-login
    Created on : Nov 15, 2016, 6:17:11 PM
    Author     : Gustavo
--%>

<%@page import="java.util.Calendar"%>
<%@page import="model.entity.Usuario"%>
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
        <%  Usuario usuario = null;
            if(session.getAttribute("usuario") != null){
                usuario = ((Usuario) session.getAttribute("usuario"));
            } 
        %>
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
                <div class="principal-cadastrar-racas">
                    <form id="formlogin" action="/AnyMais/usuario/atualizar" method="POST">
                        E-mail: 
                        <input type="text" class="label-field coluna1 email" name="email" required>
                        <br/>
                        Confirmar E-mail: 
                        <input type="text" class="label-field coluna2 email" name="confirmarEmail" required>
                        <br/>
                        <br/>
                        Senha: 
                        <input type="password" class="label-field coluna1" name="senha" required>
                        <br/>
                        Confirmar Senha:
                        <input type="password" class="label-field coluna2" name="confirmarSenha" required>
                        
                        <br><br>
                        <input type="button" name="cancelar" class="button-cancelar" value="Cancelar">
                        <input type="submit" name="cadastrar" class="button-cadastrar" 
                               value="Atualizar">
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
                var form = document.getElementById("formlogin");
                
                form.addEventListener("submit", function(e){
                    if(cadastrar.value === "Atualizar"){
                        var confirma = window.confirm("Deseja confirmar atualização de dados?");
                        if(confirma){
                            document.getElementById("formlogin").action = "/AnyMais/loginatualizado";
                            document.getElementById("formlogin").submit();
                        }
                        else{
                            e.preventDefault();
                        }
                    }
                });
                
                cancelar.addEventListener("click", function(e){
                    var confirma = window.confirm("Deseja confirmar cancelamento? Os dados preenchidos serão perdidos.");
                    if(confirma){
                        document.getElementById("formlogin").action = "/AnyMais/usuario/atualizar";
                        document.getElementById("formlogin").submit();
                    }
                });

            }
            
            load();
        </script>
        <script src="/AnyMais/scripts/validacao.js?<%=Calendar.getInstance().getTime()%>"></script>
    </body>
</html>
