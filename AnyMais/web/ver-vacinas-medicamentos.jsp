<%-- 
    Document   : ver-vacinas-medicamentos
    Created on : 26/10/2016, 03:56:56
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
                <div class="menu">
                    <a href="/AnyMais/ver-racas.jsp"><input type="image" src="/AnyMais/images/racas-button.png" class="menu-racas-button" /></a><br>
                    <a href="/AnyMais/ver-vacinas-medicamentos.jsp"><input type="image" src="/AnyMais/images/vacinas-medicamentos-button.png" class="menu-vacinas-button" /></a><br>
                    <input type="image" src="/AnyMais/images/logout-button.png" class="logout-button" />
                </div>
                <div class="principal-vacinasMedicamentos">
                    <form id="formVacinasMedicamentos" action="/AnyMais/vacinasMedicamentos" method="post">
                    <h3 class="title">Vacinas e Medicamentos</h3>
                    <input type="text" name="nome" class="label-field-racas" value="<% out.print(session.getAttribute("nome") != null ? session.getAttribute("nome") : ""); %>">
                    <input type="image" name="procurar-vacinas-medicamentos" src="/AnyMais/images/search.png" class="search-button" />
                    <input type="image" name="adicionar-vacinas-medicamentos" src="/AnyMais/images/adicionar-vacina.png" class="adicionar-medicamento-button" />
                    <br>
                    <input type="checkbox" name="tipo-pet-c" class="raca-tipo-pet primeiro-pet" value="cachorro"
                           <% out.print(session.getAttribute("tipo-pet-c") != null ? "checked" : ""); %> > <filtro class="vacina-medicamento-filtro">Cachorro</filtro>
                    <input type="checkbox" name="tipo-pet-g" class="raca-tipo-pet" value="gato"
                           <% out.print(session.getAttribute("tipo-pet-g") != null ? "checked" : ""); %> > <filtro class="vacina-medicamento-filtro">Gato</filtro>
                    
                    
                    <input type="checkbox" name="tipo-vacina" class="raca-tipo-pet primeiro-pet" value="vacina"
                           <% out.print(session.getAttribute("tipo-vacina") != null ? "checked" : ""); %> > <filtro class="vacina-medicamento-filtro">Vacina</filtro>
                    <input type="checkbox" name="tipo-medicamento" class="raca-tipo-pet" value="medicamento"
                           <% out.print(session.getAttribute("tipo-medicamento") != null ? "checked" : ""); %> > <filtro class="vacina-medicamento-filtro">Medicamento</filtro>
                    
                    <br><br>
                    
                    <table border="1" class="table-racas">
                        <tr>
                          <th class="table-raca-title">Tipo</th>
                          <th class="table-raca-title">Espécie</th>
                          <th class="table-raca-title">Nome</th>
                          <th class="table-raca-title">Periodicidade</th>
                          <th class="table-raca-title"></th>
                          <th class="table-raca-title"></th>
                        </tr>
                        <input type="hidden" name="excluido" value=""/>
                        <input type="hidden" name="atualizado" value=""/>
                          
                        
                        <%
                                if (session.getAttribute("medicamento") instanceof VacinasMedicamentos[]) {
                                    VacinasMedicamentos[] medicamentos = (VacinasMedicamentos[]) session.getAttribute("medicamento");
                                    for (VacinasMedicamentos medicamento : medicamentos) {
                                        int id = medicamento.getIdVacinasMedicamentos();
                            %>
                        <tr>
                          <td><% out.print(medicamento.getTipoVacinaMedicamento().getNomeTipoVacinaMedicamento()); %></td>
                          <td><% out.print(medicamento.getTipoAnimal().getNomeTipoAnimal()); %></td>
                          <td><% out.print(medicamento.getNome()); %></td>
                          <td><% out.print(medicamento.getPeriodicidade() + " " + medicamento.getTempo()); %></td>
                          <td>
                            <center><input name="excluir" id="<% out.print(medicamento.getIdVacinasMedicamentos()); %>" type="image" src="images/excluir.png" class="excluir-button" /></center>
                          </td>
                          <td>
                            <center><input name="atualizar" id="<% out.print(medicamento.getIdVacinasMedicamentos()); %>" type="image" src="images/edit.png" class="excluir-button" /></center>
                          </td>
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

                // Links de exclusão de raças

                var excluir = document.getElementsByName("excluir");
                var i = 0;
                for (i = 0; i < excluir.length; i++) {
                    excluir[i].addEventListener('click', function (e) {
                        var confirma = window.confirm("Deseja confirmar exclusão?");
                        
                        if(confirma){
                            document.getElementsByName("excluido")[0].value = e.target.id;
                            document.getElementById("formVacinasMedicamentos").action = "/AnyMais/vacinasMedicamentos/excluido";
                            document.getElementById("formVacinasMedicamentos").submit();
                        }
                    });
                }
                
                var atualizar = document.getElementsByName("atualizar");
                var i = 0;
                for (i = 0; i < atualizar.length; i++) {
                    atualizar[i].addEventListener('click', function (e) {
                        document.getElementsByName("atualizado")[0].value = e.target.id;
                        document.getElementById("formVacinasMedicamentos").action = "/AnyMais/vacinasMedicamentos/atualizar";
                        document.getElementById("formVacinasMedicamentos").submit();
                    });
                }
                
                var adicionarVacinaMedicamento = document.getElementsByName("adicionar-vacinas-medicamentos")[0];
                adicionarVacinaMedicamento.addEventListener("click", function(){
                    document.getElementById("formVacinasMedicamentos").action = "/AnyMais/vacinasMedicamentos/cadastrar";
                    document.getElementById("formVacinasMedicamentos").submit();
                });
                
                // Links de filtro
                
                var filtros = [];
                filtros.push(document.getElementsByName("procurar-vacinas-medicamentos")[0]);
                filtros.push(document.getElementsByName("tipo-pet-c")[0]);
                filtros.push(document.getElementsByName("tipo-pet-g")[0]);
                filtros.push(document.getElementsByName("tipo-vacina")[0]);
                filtros.push(document.getElementsByName("tipo-medicamento")[0]);
                
                for (i = 0; i < filtros.length; i++) {
                    filtros[i].addEventListener("click", function () {
                        document.getElementById("formVacinasMedicamentos").action = "/AnyMais/vacinasMedicamentos";
                        document.getElementById("formVacinasMedicamentos").submit();
                    });
                }

            }

            load();

        </script>
    </body>
</html>