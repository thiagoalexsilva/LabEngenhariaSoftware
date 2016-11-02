<%-- 
    Document   : cadastrar-vacinas-medicamentos
    Created on : 26/10/2016, 03:54:10
    Author     : ana
--%>

<%@page import="model.entity.VacinaMedicamento"%>
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
        
        <div class="container">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <div class="menu">
                    <a href="ver-racas.html"><input type="image" src="images/racas-button.png" class="menu-racas-button" /></a><br>
                    <a href="ver-vacinas-medicamentos.html"><input type="image" src="images/vacinas-medicamentos-button.png" class="menu-vacinas-button" /></a><br>
                    <input type="image" src="images/logout-button.png" class="logout-button" />
                </div>
                <div class="principal">
                    <form id="formmedicamentos" action="/AnyMais/medicamentos/cadastrado" method="POST">
                        <% if(session.getAttribute("raca") == null){ %>
                            <h3 class="title">Cadastrar Vacinas e Medicamentos</h3>
                        <% } 
                            else{ %>
                            <h3 class="title">Atualizar Vacinas e Medicamentos</h3>
                        <% } %>
                            <input type="radio" name="tipo-vacinaMedicamento" value="Vacina" class="cadastra-raca label-field-vacina"> Vacina
                            <input type="radio" name="tipo-vacinaMedicamento" value="Medicamento" class="cadastra-raca label-field-vacina"> Medcicamento
                            <br>
                            <input type="radio" name="tipo-pet" value="cachorro" class="cadastra-raca label-field-vacina"
                                   <% out.print(session.getAttribute("medicamento") != null && ((VacinaMedicamento) session.getAttribute("medicamento")).getTipoAnimal().toUpperCase().equals("CACHORRO") ? "checked" : ""); %> > Cachorro
                            <input type="radio" name="tipo-pet" value="gato" class="label-field-vacina"
                                   <% out.print(session.getAttribute("medicamento") != null && ((VacinaMedicamento) session.getAttribute("medicamento")).getTipoAnimal().toUpperCase().equals("GATO") ? "checked" : ""); %> > Gato<br>
                            <br>
                        
                            <p class="cadastra-vacina">Nome:
                                <input type="text" class="label-field-vacina" name="nome-medicamento"
                                       value="<% out.print(session.getAttribute("medicamento") != null ? ((VacinaMedicamento) session.getAttribute("medicamento")).getNome().toUpperCase() : ""); %>"></p>
                            
                            <p class="cadastra-vacina">Periodicidade:
                                <input type="number" name="periodicidade" class="label-periodo label-field-vacina" min="0"
                                       value="<% out.print(session.getAttribute("medicamento") != null ? ((VacinaMedicamento) session.getAttribute("medicamento")).getPeriodicidade() : 0); %>">
                                <select name="periodicidade" required class="label-periodo label-field-vacina">
                                <option></option>
                                <option value="Horas">Horas</option>
                                <option value="Dias">Dias</option>
                                <option value="Meses">Meses</option>
                              </select></p>
                            
                            <p class="cadastra-vacina">Observação:</p><br>
                                <textarea name="observacao" rows="4" cols="50" class="label-field-vacina">
                                    <% out.print(session.getAttribute("medicamento") != null ? ((VacinaMedicamento) session.getAttribute("medicamento")).getObservacao() : ""); %>
                                </textarea>
                                <br>
                                
                            <input type="submit" name="cancelar" class="button-cancelar" value="Cancelar">
                            <input type="submit" name="cadastrar" class="button-cadastrar"
                                   value="<% out.print(session.getAttribute("medicamento") == null ? "Cadastrar" : "Atualizar"); %>">
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
                
                cadastrar.addEventListener("click", function(e){
                    if(e.target.value == "Cadastrar"){
                        document.getElementById("formmedicamentos").action = "/AnyMais/medicamentos/cadastrado";
                        document.getElementById("formmedicamentos").submit();
                    }
                    else if(e.target.value == "Atualizar"){
                        document.getElementById("formmedicamentos").action = "/AnyMais/medicamentos/atualizado";
                        document.getElementById("formmedicamentos").submit();
                    }
                });
            }
            
            load();
        </script>                   
    </body>
</html>
