<%-- 
    Document   : cadastrar-vacinas-medicamentos
    Created on : 26/10/2016, 03:54:10
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
    <link rel="stylesheet" type="text/css" href="/AnyMais/styles/style.css">
</head>
    <body>
        <div class="container c-header">
            <header>
                <img src="/AnyMais/images/logo.png" class="img-responsive logo-header"/>
            </header>
        </div>
        
        <%  VacinasMedicamentos vacinaMedicamento = null;
            if(session.getAttribute("medicamento") != null){
                vacinaMedicamento = ((VacinasMedicamentos) session.getAttribute("medicamento"));
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
                <div class="principal">
                    <form id="formVacinasMedicamentos" action="/AnyMais/vacinasMedicamentos/<%=vacinaMedicamento == null ? "cadastrado" : "atualizado"%>" method="POST">
                        <% if(vacinaMedicamento == null){ %>
                            <h3 class="title">Cadastrar Vacinas e Medicamentos</h3>
                        <% } 
                            else{ %>
                            <h3 class="title">Atualizar Vacinas e Medicamentos</h3>
                        <% } %>
                            <input type="radio" name="tipoVacinaMedicamento" value="Vacina" class="cadastra-raca label-field-vacina" required
                                   <%= vacinaMedicamento != null && vacinaMedicamento.getTipoVacinaMedicamento().getNomeTipoVacinaMedicamento().toUpperCase().equals("VACINA") ? "checked" : "" %> > Vacina
                            <input type="radio" name="tipoVacinaMedicamento" value="Medicamento" class="cadastra-raca label-field-vacina" required
                                   <%= vacinaMedicamento != null && vacinaMedicamento.getTipoVacinaMedicamento().getNomeTipoVacinaMedicamento().toUpperCase().equals("MEDICAMENTO") ? "checked" : "" %> > Medicamento
                            <br>
                            <input type="radio" name="tipoAnimal" value="Cachorro" class="cadastra-raca label-field-vacina" required
                                   <%= vacinaMedicamento != null && vacinaMedicamento.getTipoAnimal().getNomeTipoAnimal().toUpperCase().equals("CACHORRO") ? "checked" : "" %> > Cachorro
                            <input type="radio" name="tipoAnimal" value="Gato" class="label-field-vacina" required
                                   <%= vacinaMedicamento != null && vacinaMedicamento.getTipoAnimal().getNomeTipoAnimal().toUpperCase().equals("GATO") ? "checked" : "" %> > Gato<br>
                            <br>
                        
                            <p class="cadastra-vacina">Nome:
                                <input type="text" class="label-field-vacina" name="nome" required
                                       value="<%= vacinaMedicamento != null ? vacinaMedicamento.getNome() : "" %>"></p>
                            
                            <p class="cadastra-vacina">Periodicidade:
                                <input type="number" name="periodicidade" class="label-periodo label-field-vacina" min="0" required
                                       value="<%= vacinaMedicamento != null ? vacinaMedicamento.getPeriodicidade(): "" %>">
                                <select name="tempo" required class="label-periodo label-field-vacina" required>
                                <option></option>
                                <option value="-" disabled <%= vacinaMedicamento == null ? "selected" : ""%>></option>
                                <option value="Horas" <%= vacinaMedicamento != null && vacinaMedicamento.getTempo().toUpperCase().equals("HORAS") ? "selected" : "" %> >Horas</option>
                                <option value="Dias" <%= vacinaMedicamento != null && vacinaMedicamento.getTempo().toUpperCase().equals("DIAS") ? "selected" : "" %> >Dias</option>
                                <option value="Meses" <%= vacinaMedicamento != null && vacinaMedicamento.getTempo().toUpperCase().equals("MESES") ? "selected" : "" %> >Meses</option>
                              </select></p>
                            
                            <p class="cadastra-vacina">Observação:</p><br>
                                <textarea name="observacao" rows="4" cols="50" class="label-field-vacina"><%=vacinaMedicamento != null ? vacinaMedicamento.getObservacao() : "" %></textarea>
                                <br>
                                
                            <input type="submit" name="cancelar" class="button-cancelar" value="Cancelar">
                            <input type="submit" name="cadastrar" class="button-cadastrar"
                                   value="<%=vacinaMedicamento == null ? "Cadastrar" : "Atualizar"%>">
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
                
                cadastrar.addEventListener("click", function(e){
                    if(e.target.value == "Cadastrar"){
                        document.getElementById("formVacinasMedicamentos").submit();
                    }
                    else if(e.target.value == "Atualizar"){
                        var confirma = window.confirm("Deseja confirmar atualização de dados?");
                        if(confirma){
                            document.getElementById("formVacinasMedicamentos").submit();
                        }
                    }
                });
                
                cancelar.addEventListener("click", function(e){
                    var confirma = window.confirm("Deseja mesmo cancelar? Os dados preenchidos serão perdidos.");
                    if(confirma){
                        document.getElementById("formVacinasMedicamentos").action = "/AnyMais/vacinasMedicamentos";
                        document.getElementById("formVacinasMedicamentos").submit();
                    }
                });
            }
            
            load();
        </script>                   
    </body>
</html>
