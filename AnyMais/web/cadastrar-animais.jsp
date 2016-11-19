<%-- 
    Document   : cadastrar-animais
    Created on : Oct 3, 2016, 9:09:14 PM
    Author     : Gustavo
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.entity.Raca"%>
<%@page import="controller.GerenciarRacas"%>
<%@page import="java.util.Calendar"%>
<%@page import="model.entity.Animal"%>
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
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            Raca[] racas = null;
            if(session.getAttribute("racas") == null)
                racas = new Raca[0];
            else
                racas = ((Raca[]) session.getAttribute("racas"));
            
            Animal animal = null;
            if(session.getAttribute("animal") != null){
                animal = ((Animal) session.getAttribute("animal"));
            } 
        %>
        
        <div class="container">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <div class="menu">
                    <img src="/AnyMais/images/profile-image.png" class="menu-profile-image"/><br>
                    <a href="/AnyMais/usuario/atualizar"><input type="image" src="/AnyMais/images/editar-perfil-button.png" class="menu-editar-perfil" /></a><br>
                    <a href="/AnyMais/usuario/animais"><input type="image" src="/AnyMais/images/meus-pets-button.png" class="menu-meus-pets" /></a><br>
                    <a href="mensagens-cliente.html"><input type="image" src="/AnyMais/images/mensagens-button.png" class="menu-mensagens" /></a><br>
                    <a href="agendamentos-cliente.html"><input type="image" src="/AnyMais/images/agendamento-button.png" class="menu-agendamento" /></a><br>
                    <a href="minhas-avaliacoes.html"><input type="image" src="/AnyMais/images/minhas-avaliacoes-button.png" class="menu-minhas-avaliacoes" /></a><br>
                    <a href="/AnyMais/logout"><input type="image" src="/AnyMais/images/logout-button.png" class="logout-button" /></a><br>
                    <a href="/AnyMais/usuario/encerrar"><input id="encerrar" type="image" src="/AnyMais/images/excluir-button.png" class="logout-button" /></a>
                </div>
                <a href="/AnyMais/home-petshop.jsp"><input type="image" src="/AnyMais/images/home-button2.png" class="menu2-home" /></a>
                
                <div class="principal-cadastrar-racas">
                    <form id="formAnimais" action="/AnyMais/usuario/animais/<%=animal == null ? "cadastrado" : "atualizado"%>" method="POST">
                        <% if(session.getAttribute("atualizar") == null){ %>
                            <h3 class="title">Cadastrar Pet</h3>
                        <% } 
                            else{ %>
                            <h3 class="title">Atualizar Pet</h3>
                        <% } %>
                        <input type="button" name="cancelar" class="button-cancelar" value="Cancelar">
                        <input type="submit" name="cadastrar" class="button-cadastrar" 
                               value="<%=animal == null ? "Cadastrar" : "Atualizar"%>">
                        <br>
                        <input type="radio" id="tipoAnimal" name="tipoAnimal" value="Cachorro" class="cadastra-raca" required 
                               <%= session.getAttribute("tipoAnimal") != null ? (session.getAttribute("tipoAnimal").toString().toUpperCase().equals("CACHORRO") ? "checked" : "")
                                       : (animal != null && animal.getTipoAnimal().getNomeTipoAnimal().toUpperCase().equals("CACHORRO") ? "checked" : "") %> > Cachorro
                        <input type="radio" id="tipoAnimal" name="tipoAnimal" value="Gato" required
                               <%= session.getAttribute("tipoAnimal") != null ? (session.getAttribute("tipoAnimal").toString().toUpperCase().equals("GATO") ? "checked" : "")
                                       : (animal != null && animal.getTipoAnimal().getNomeTipoAnimal().toUpperCase().equals("GATO") ? "checked" : "") %> > Gato<br>
                        <br>                    
                        <p class="cadastra-raca">Nome:
                            <input type="text" class="label-field-cadstrar-animal-raca" name="nomeAnimal" required 
                                   value="<%= session.getAttribute("nomeAnimal") != null ? session.getAttribute("nomeAnimal") : animal != null ? animal.getNome() : "" %>"> </p>
                        
                        <p class="cadastra-raca">Data de Nascimento:
                            <input type="text" class="label-field-raca data" name="dataNascimentoAnimal" required 
                                   value="<%= session.getAttribute("dataNascimentoAnimal") != null ? session.getAttribute("dataNascimentoAnimal") : animal != null ? sdf.format(animal.getDataNascimento()): "" %>"> </p>
                        
                        <p class="cadastra-raca">Raça:
                            <select name="racaAnimal" class="seleciona-porte" required>
                                <option value="-" disabled <%= session.getAttribute("racaAnimal") != null ? session.getAttribute("racaAnimal") : animal == null ? "selected" : ""%> >-</option>
                                <%  
                                    if(racas != null)
                                        for(Raca raca : racas){
                                            boolean racaSelecionada = (animal != null && animal.getRaca().getIdRaca() == raca.getIdRaca());
                                            if((session.getAttribute("tipoAnimal") != null && session.getAttribute("tipoAnimal").toString().toUpperCase().equals(raca.getTipoAnimal().getNomeTipoAnimal().toUpperCase()))){ 
                                %>
                                                <option value="<%= raca.getIdRaca() %>" <%= session.getAttribute("racaAnimal") != null ? session.getAttribute("racaAnimal") : animal != null && animal.getRaca().getIdRaca() == raca.getIdRaca() ? "selected" : "" %> <%=racaSelecionada ? "required" : "" %>><%= raca.getNomeRaca()%></option>
                                <%      
                                            }
                                        }
                                %>
                            </select> </p>
                        
                        <p class="cadastra-raca">Peso:
                            <input type="text" class="label-field-raca flutuante" name="pesoAnimal" required 
                                   value="<%= session.getAttribute("pesoAnimal") != null ? session.getAttribute("pesoAnimal") : animal != null ? animal.getPeso(): "" %>"> </p>
                        <p class="cadastra-raca numero">Tamanho:
                            <input type="text" class="label-field-raca flutuante" name="tamanhoAnimal" required 
                                   value="<%= session.getAttribute("tamanhoAnimal") != null ? session.getAttribute("tamanhoAnimal") : animal != null ? animal.getTamanho(): "" %>"> </p>
                        <p class="cadastra-raca">Cor:
                            <input type="text" class="label-field-raca" name="corAnimal" required 
                                   value="<%= session.getAttribute("corAnimal") != null ? session.getAttribute("corAnimal") : animal != null ? animal.getCor(): "" %>"> </p>
                        <p class="cadastra-raca">Sexo:
                            <input type="radio" name="sexoAnimal" value="F" class="cadastra-raca" required 
                                   <%= session.getAttribute("sexoAnimal") != null ? (session.getAttribute("sexoAnimal").toString().toUpperCase().equals("F") ? "checked" : "") : animal != null && animal.getSexo().toUpperCase().equals("F") ? "checked" : "" %> > Fêmea
                            <input type="radio" name="sexoAnimal" value="M" class="cadastra-raca" required
                                   <%= session.getAttribute("sexoAnimal") != null ? (session.getAttribute("sexoAnimal").toString().toUpperCase().equals("M") ? "checked" : "") : animal != null && animal.getSexo().toUpperCase().equals("M") ? "checked" : "" %> > Macho<br>
                         </p>
                        <p class="cadastra-raca">Descrição:
                            <input type="text" class="label-field-raca" name="descricaoAnimal" required 
                                   value="<%= session.getAttribute("descricaoAnimal") != null ? session.getAttribute("descricaoAnimal") : animal != null ? animal.getDescricao(): "" %>"> </p>
                        <br><br>
                        
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
                var tipoAnimal1 = document.getElementsByName("tipoAnimal")[0];
                var tipoAnimal2 = document.getElementsByName("tipoAnimal")[1];
                var form = document.getElementById("formAnimais");
                
                tipoAnimal1.addEventListener("click", function(e){
                    if(cadastrar.value === 'Cadastrar')
                        document.getElementById("formAnimais").action = "/AnyMais/usuario/animais/cadastrar/carregaracas";
                    else if(cadastrar.value === 'Atualizar')
                        document.getElementById("formAnimais").action = "/AnyMais/usuario/animais/atualizar/carregaracas";
                    document.getElementById("formAnimais").submit();
                });

                tipoAnimal2.addEventListener("click", function(e){
                    if(cadastrar.value === 'Cadastrar')
                        document.getElementById("formAnimais").action = "/AnyMais/usuario/animais/cadastrar/carregaracas";
                    else if(cadastrar.value === 'Atualizar')
                        document.getElementById("formAnimais").action = "/AnyMais/usuario/animais/atualizar/carregaracas";
                    document.getElementById("formAnimais").submit();
                });
        
                /*cadastrar.addEventListener("submit", function(e){
                    if(e.target.value === "Cadastrar"){
                        var confirma = window.confirm("Deseja confirmar inserção de dados?");
                        if(confirma){
                            document.getElementById("formAnimais").action = "/AnyMais/usuario/animais/cadastrado";
                            document.getElementById("formAnimais").submit();
                        }
                    }
                    else if(e.target.value === "Atualizar"){
                        var confirma = window.confirm("Deseja confirmar atualização de dados?");
                        if(confirma){
                            document.getElementById("formAnimais").action = "/AnyMais/usuario/animais/atualizado";
                            document.getElementById("formAnimais").submit();
                        }
                    }
                });*/
                
                form.addEventListener("submit", function(e){
                    if(cadastrar.value === "Cadastrar"){
                        var confirma = window.confirm("Deseja confirmar inserção de dados?");
                        if(confirma){
                            document.getElementById("formAnimais").action = "/AnyMais/usuario/animais/cadastrado";
                            document.getElementById("formAnimais").submit();
                        }
                        else{
                            e.preventDefault();
                        }
                    }
                    else if(cadastrar.value === "Atualizar"){
                        var confirma = window.confirm("Deseja confirmar atualização de dados?");
                        if(confirma){
                            document.getElementById("formAnimais").action = "/AnyMais/usuario/animais/atualizado";
                            document.getElementById("formAnimais").submit();
                        }
                        else{
                            e.preventDefault();
                        }
                    }
                });
                
                cancelar.addEventListener("click", function(e){
                    var confirma = window.confirm("Deseja confirmar cancelamento? Os dados preenchidos serão perdidos.");
                    if(confirma){
                        document.getElementById("formAnimais").action = "/AnyMais/usuario/animais";
                        document.getElementById("formAnimais").submit();
                    }
                });

            }
            
            load();
        </script>
        <script src="/AnyMais/scripts/validacao.js?<%=Calendar.getInstance().getTime()%>"></script>
    </body>
</html>
