<%-- 
    Document   : cadastrar-racas
    Created on : Oct 3, 2016, 9:09:14 PM
    Author     : Gustavo
--%>

<%@page import="java.util.Calendar"%>
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
        <%  Raca raca = null;
            if(session.getAttribute("raca") != null){
                raca = ((Raca) session.getAttribute("raca"));
            } 
        %>
        
        <div class="container">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <div class="menu">
                    <a href="/AnyMais/ver-racas.jsp"><input type="image" src="/AnyMais/images/racas-button.png" class="menu-racas-button" /></a><br>
                    <a href="/AnyMais/ver-vacinas-medicamentos.jsp"><input type="image" src="/AnyMais/images/vacinas-medicamentos-button.png" class="menu-vacinas-button" /></a><br>
                    <input type="image" src="/AnyMais/images/logout-button.png" class="logout-button" />
                </div>
                <div class="principal-cadastrar-racas">
                    <form id="formRacas" action="/AnyMais/racas/<%=raca == null ? "cadastrado" : "atualizado"%>" method="POST">
                        <% if(raca == null){ %>
                            <h3 class="title">Cadastrar Raça</h3>
                        <% } 
                            else{ %>
                            <h3 class="title">Atualizar Raça</h3>
                        <% } %>
                        <br>
                        <input type="radio" name="tipoAnimal" value="Cachorro" class="cadastra-raca" required 
                               <%= raca != null && raca.getTipoAnimal().getNomeTipoAnimal().toUpperCase().equals("CACHORRO") ? "checked" : "" %> > Cachorro
                        <input type="radio" name="tipoAnimal" value="Gato" required
                               <%= raca != null && raca.getTipoAnimal().getNomeTipoAnimal().toUpperCase().equals("GATO") ? "checked" : "" %> > Gato<br>
                        <br>                    
                        <p class="cadastra-raca">Raça:
                            <input type="text" class="label-field-raca" name="nomeRaca" required 
                                   value="<%= raca != null ? raca.getNomeRaca() : "" %>"> </p>
                        <p class="cadastra-raca">Porte:
                            <select name="porte" class="seleciona-porte" required>
                                <option value="-" disabled <%= raca == null ? "selected" : ""%> >-</option>
                                <option value="Pequeno" <%= raca != null && raca.getPorte().toUpperCase().equals("PEQUENO") ? "selected" : "" %> >Pequeno</option>
                                <option value="Medio" <%= raca != null && raca.getPorte().toUpperCase().equals("MEDIO") ? "selected" : "" %> >Médio</option>
                                <option value="Grande" <%= raca != null && raca.getPorte().toUpperCase().equals("GRANDE") ? "selected" : "" %> >Grande</option>
                            </select>
                        <p class="cadastra-raca">Observação:</p>
                        <textarea name="observacao" rows="4" cols="50" class="raca-observacao"><%=raca != null ? raca.getObservacao() : "" %></textarea> 
                        <br><br>
                        <input type="button" name="cancelar" class="button-cancelar" value="Cancelar">
                        <input type="submit" name="cadastrar" class="button-cadastrar" 
                               value="<%=raca == null ? "Cadastrar" : "Atualizar"%>">
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
                var form = document.getElementById("formRacas");
                
                form.addEventListener("submit", function(e){
                    if(cadastrar.value === "Cadastrar"){
                        document.getElementById("formRacas").submit();
                    }
                    else if(cadastrar.value === "Atualizar"){
                        var confirma = window.confirm("Deseja confirmar atualização de dados?");
                        if(confirma){
                            document.getElementById("formRacas").submit();
                        }
                        else{
                            e.preventDefault();
                        }
                    }
                });
                
                cancelar.addEventListener("click", function(e){
                    var confirma = window.confirm("Deseja confirmar cancelamento? Os dados preenchidos serão perdidos.");
                    if(confirma){
                        document.getElementById("formRacas").action = "/AnyMais/racas";
                        document.getElementById("formRacas").submit();
                    }
                });

            }
            
            load();
        </script>
        <script src="/AnyMais/scripts/validacao.js?<%=Calendar.getInstance().getTime()%>"></script>
    </body>
</html>
