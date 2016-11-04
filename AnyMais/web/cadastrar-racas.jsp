<%-- 
    Document   : cadastrar-racas
    Created on : Oct 3, 2016, 9:09:14 PM
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

        <div class="container">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <div class="menu">
                    <a href="/AnyMais/ver-racas.html"><input type="image" src="images/racas-button.png" class="menu-racas-button" /></a><br>
                    <a href="/AnyMais/ver-vacinas-medicamentos.html"><input type="image" src="images/vacinas-medicamentos-button.png" class="menu-vacinas-button" /></a><br>
                    <input type="image" src="images/logout-button.png" class="logout-button" />
                </div>
                <div class="principal">
                    <form id="formRacas" action="/AnyMais/racas/cadastrado" method="POST">
                        <% if(session.getAttribute("raca") == null){ %>
                            <h3 class="title">Cadastrar Raça</h3>
                        <% } 
                            else{ %>
                            <h3 class="title">Atualizar Raça</h3>
                        <% } %>
                        <input type="radio" name="tipoAnimal" value="Cachorro" class="cadastra-raca" required
                               <% out.print(session.getAttribute("raca") != null && ((Raca) session.getAttribute("raca")).getTipoAnimal().getNomeTipoAnimal().toUpperCase().equals("CACHORRO") ? "checked" : ""); %> > Cachorro
                        <input type="radio" name="tipoAnimal" value="Gato" required
                               <% out.print(session.getAttribute("raca") != null && ((Raca) session.getAttribute("raca")).getTipoAnimal().getNomeTipoAnimal().toUpperCase().equals("GATO") ? "checked" : ""); %> > Gato<br>
                        <br>                    
                        <p class="cadastra-raca">Raça:
                            <input type="text" class="label-field-raca" name="nomeRaca" required
                                   value="<% out.print(session.getAttribute("raca") != null ? ((Raca) session.getAttribute("raca")).getNomeRaca().toUpperCase() : ""); %>"> </p>
                        <p class="cadastra-raca">Porte:
                            <select name="porte" class="seleciona-porte" required>
                                <option value="-" disabled <% out.print(session.getAttribute("raca") == null ? "selected" : ""); %> >-</option>
                                <option value="Pequeno" <% out.print(session.getAttribute("raca") != null && ((Raca) session.getAttribute("raca")).getPorte().toUpperCase().equals("PEQUENO") ? "selected" : ""); %> >Pequeno</option>
                                <option value="Medio" <% out.print(session.getAttribute("raca") != null && ((Raca) session.getAttribute("raca")).getPorte().toUpperCase().equals("MEDIO") ? "selected" : ""); %> >Médio</option>
                                <option value="Grande" <% out.print(session.getAttribute("raca") != null && ((Raca) session.getAttribute("raca")).getPorte().toUpperCase().equals("GRANDE") ? "selected" : ""); %> >Grande</option>
                            </select>
                        <p class="cadastra-raca">Observação:</p><br>
                        <textarea name="observacao" rows="4" cols="50"><% out.print(session.getAttribute("raca") != null ? ((Raca) session.getAttribute("raca")).getObservacao() : ""); %> </textarea> 
                        <br>
                        <input type="button" name="cancelar" class="button-cancelar" value="Cancelar">
                        <input type="submit" name="cadastrar" class="button-cadastrar" 
                               value="<%=session.getAttribute("raca") == null ? "Cadastrar" : "Atualizar"%>">
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
                    if(e.target.value == "Cadastrar"){
                        document.getElementById("formRacas").action = "/AnyMais/racas/cadastrado";
                        document.getElementById("formRacas").submit();
                    }
                    else if(e.target.value == "Atualizar"){
                        document.getElementById("formRacas").action = "/AnyMais/racas/atualizado";
                        document.getElementById("formRacas").submit();
                    }
                });
                
                cancelar.addEventListener("click", function(e){
                    document.getElementById("formRacas").action = "/AnyMais/racas";
                    document.getElementById("formRacas").submit();
                });

            }
            
            load();
        </script>
    </body>
</html>
