<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="model.entity.Usuario"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
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
        <%
            boolean eUsuario = false;
            boolean ePetshop = false;
            boolean eAdmin = false;
            boolean cadastro = false;
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            Usuario u = (Usuario) session.getAttribute("usuario");
            if(u == null){
                u = (Usuario) session.getAttribute("petshop");
                if(u == null){
                    u = (Usuario) session.getAttribute("admin");
                    if(u != null){
                        eAdmin = true;
                    }
                }
                else{
                    ePetshop = true;
                }
            }
            else{
                eUsuario = true;
            }
        %>
        
        <div class="container c-header">
            <header>
                <img src="/AnyMais/images/logo.png" class="img-responsive logo-header"/>
            </header>
        </div>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            <div>
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
            <div class="col-md-8 body-cadastro">
                <h3>Cadastro</h3>
                <form id="formAtualizarUsuario" action="/AnyMais/<%= eUsuario ? "usuario" : "petshop"%>/atualizado" method="POST">
                    <table>
                        <tr>
                          <th colspan="2" class="tipo-usuario">
                              <input type="radio" name="tipo" value="1" <%= eUsuario ? "checked" : "" %> required disabled> Pessoa F�sica<br>
                          </th>
                          <th class="tipo-usuario">
                              <input type="radio" name="tipo" value="2" <%= ePetshop ? "checked" : "" %> required disabled> Pessoa Jur�dica<br>
                          </th>
                          <th colspan="3"></th>
                        </tr>
                        <tr>
                          <td class="cadastrar-field">Nome </td>
                          <td colspan="5" class="cadastrar-field">
                              <input type="text" class="label-field alfa" name="nome" required value="<%= u.getPessoa().getNome() %>">
                          </td>
                        </tr>
                        <tr>
                          <td id="sexoCel1" class="cadastrar-field <%=eUsuario ? "" : "hide"%>"> Sexo </td>
                          <td id="sexoCel2" class="cadastrar-field coluna1 <%=eUsuario ? "" : "hide"%>">
                              <select name="sexo" required class="cadastro-seleciona-sexo">
                                <option value="F" <%= u.getPessoa().getSexo().toUpperCase().equals("F") ? "selected" : "" %>>Feminino</option>
                                <option value="M" <%= u.getPessoa().getSexo().toUpperCase().equals("M") ? "selected" : "" %>>Masculino</option>
                                <option value="O" <%= !u.getPessoa().getSexo().toUpperCase().equals("F") && !u.getPessoa().getSexo().toUpperCase().equals("M")? "selected" : "" %>>Outro</option>
                              </select>
                          </td>
                          <td id="dataNascimentoCel1" class="cadastrar-field <%=eUsuario ? "" : "hide"%>">Data Nascimento </td>
                          <td id="dataNascimentoCel2" class="cadastrar-field <%=eUsuario ? "" : "hide"%>">
                              <input type="date" name="dataNascimento" class="coluna2 data" value="<%= u.getPessoa().getDataNascimento() != null ? sdf.format(u.getPessoa().getDataNascimento()) : ""%>">
                          </td>
                          <td id="cpfCnpjCel1" class="cadastrar-field"><span id="cpfCnpjTitulo"><%=eUsuario ? "CPF" : ePetshop ? "CNPJ" : "CPF / CNPJ"%></span></td>
                          <td id="cpfCnpjCel2" class="cadastrar-field">
                              <input type="text" class="label-field" name="cpfCnpj" required value="<%= u.getPessoa().getCpfCnpj()%>" disabled>
                          </td>
                        </tr>
                        <tr>
                          <td class="cadastrar-field">Endere�o </td>
                          <td colspan="5" class="cadastrar-field">
                              <input type="text" class="label-field" name="endereco" required value="<%= u.getPessoa().getEndereco()%>">
                          </td>
                        </tr>
                        <tr>
                          <td class="cadastrar-field">Bairro </td>
                          <td class="cadastrar-field">
                              <input type="text" class="label-field coluna1" name="bairro" required value="<%= u.getPessoa().getBairro()%>">
                          </td>
                          <td class="cadastrar-field">Complemento </td>
                          <td colspan="2" class="cadastrar-field">
                              <input type="text" class="label-field coluna2" name="complemento" value="<%= u.getPessoa().getComplemento()%>">
                          </td>
                        </tr>
                        <tr>
                          <td class="cadastrar-field">CEP </td>
                          <td class="cadastrar-field">
                              <input type="text" class="label-field coluna1 cep" name="cep" required value="<%= u.getPessoa().getCep()%>"> 
                          </td>
                          <td class="cadastrar-field">Cidade </td>
                          <td class="cadastrar-field">
                              <input type="text" class="label-field coluna2" name="cidade" required value="<%= u.getPessoa().getCidade()%>">
                          </td>
                          <td class="cadastrar-field">UF</td>
                          <td class="cadastrar-field">
                              <select name="uf" required class="cadastro-uf" required>
                                <option></option>
                                <option value="AC" <%= u.getPessoa().getUf().toUpperCase().equals("AC") ? "selected" : "" %>>AC</option>
                                <option value="AL" <%= u.getPessoa().getUf().toUpperCase().equals("AL") ? "selected" : "" %>>AL</option>
                                <option value="AP" <%= u.getPessoa().getUf().toUpperCase().equals("AP") ? "selected" : "" %>>AP</option>
                                <option value="AM" <%= u.getPessoa().getUf().toUpperCase().equals("AM") ? "selected" : "" %>>AM</option>
                                <option value="BA" <%= u.getPessoa().getUf().toUpperCase().equals("BA") ? "selected" : "" %>>BA</option>
                                <option value="CE" <%= u.getPessoa().getUf().toUpperCase().equals("CE") ? "selected" : "" %>>CE</option>
                                <option value="DF" <%= u.getPessoa().getUf().toUpperCase().equals("DF") ? "selected" : "" %>>DF</option>
                                <option value="ES" <%= u.getPessoa().getUf().toUpperCase().equals("ES") ? "selected" : "" %>>ES</option>
                                <option value="GO" <%= u.getPessoa().getUf().toUpperCase().equals("GO") ? "selected" : "" %>>GO</option>
                                <option value="MA" <%= u.getPessoa().getUf().toUpperCase().equals("MA") ? "selected" : "" %>>MA</option>
                                <option value="MT" <%= u.getPessoa().getUf().toUpperCase().equals("MT") ? "selected" : "" %>>MT</option>
                                <option value="MS" <%= u.getPessoa().getUf().toUpperCase().equals("MS") ? "selected" : "" %>>MS</option>
                                <option value="MG" <%= u.getPessoa().getUf().toUpperCase().equals("MG") ? "selected" : "" %>>MG</option>
                                <option value="PA" <%= u.getPessoa().getUf().toUpperCase().equals("PA") ? "selected" : "" %>>PA</option>
                                <option value="PB" <%= u.getPessoa().getUf().toUpperCase().equals("PB") ? "selected" : "" %>>PB</option>
                                <option value="PR" <%= u.getPessoa().getUf().toUpperCase().equals("PR") ? "selected" : "" %>>PR</option>
                                <option value="PE" <%= u.getPessoa().getUf().toUpperCase().equals("PE") ? "selected" : "" %>>PE</option>
                                <option value="PI" <%= u.getPessoa().getUf().toUpperCase().equals("PI") ? "selected" : "" %>>PI</option>
                                <option value="RJ" <%= u.getPessoa().getUf().toUpperCase().equals("RJ") ? "selected" : "" %>>RJ</option>
                                <option value="RN" <%= u.getPessoa().getUf().toUpperCase().equals("RN") ? "selected" : "" %>>RN</option>
                                <option value="RS" <%= u.getPessoa().getUf().toUpperCase().equals("RS") ? "selected" : "" %>>RS</option>
                                <option value="RO" <%= u.getPessoa().getUf().toUpperCase().equals("RO") ? "selected" : "" %>>RO</option>
                                <option value="RR" <%= u.getPessoa().getUf().toUpperCase().equals("RR") ? "selected" : "" %>>RR</option>
                                <option value="SC" <%= u.getPessoa().getUf().toUpperCase().equals("SC") ? "selected" : "" %>>SC</option>
                                <option value="SP" <%= u.getPessoa().getUf().toUpperCase().equals("SP") ? "selected" : "" %>>SP</option>
                                <option value="SE" <%= u.getPessoa().getUf().toUpperCase().equals("SE") ? "selected" : "" %>>SE</option>
                                <option value="TO" <%= u.getPessoa().getUf().toUpperCase().equals("TO") ? "selected" : "" %>>TO</option>
                              </select>
                          </td>
                        </tr>
                        <tr>
                          <td class="cadastrar-field">Telefone </td>
                          <td class="cadastrar-field">
                              <input type="text" class="label-field coluna1 telefone" name="telefone" required value="<%= u.getPessoa().getTelefone()%>">
                          </td>
                          <td class="cadastrar-field">Telefone Celular </td>
                          <td colspan="2" class="cadastrar-field">
                              <input type="text" class="label-field coluna2 celular" name="telefone2" value="<%= u.getPessoa().getTelefone2()%>">
                          </td>
                        </tr>
                        <tr>
                          <td class="cadastrar-field">E-mail </td>
                          <td class="cadastrar-field">
                              <input type="text" class="label-field coluna1 email" name="email" required value="<%= u.getConta().getEmail()%>" disabled>
                          </td>
                         <!-- <td class="cadastrar-field">Confirmar E-mail </td>
                          <td colspan="2" class="cadastrar-field">
                              <input type="text" class="label-field coluna2 email" name="confirmaEmail" required>
                          </td>-->
                         <td></td>
                         <td></td>
                        </tr>
                        <tr>
                            <td class="cadastrar-field"><a href="/AnyMais/atualizarlogin">Alterar login</a> </td>
                            <td></td>
                            <td></td>
                            <td></td>
                          <!--<td class="cadastrar-field">
                              <input type="password" class="label-field coluna1" name="senha" required>
                          </td>
                          <td class="cadastrar-field">Confirmar Senha </td>
                          <td colspan="2" class="cadastrar-field">
                              <input type="password" class="label-field coluna2" name="confirmaSenha" required>
                          </td>-->
                        </tr>
                        <tr>
                            <td colspan="6" class="cadastrar-field">
                                <input type="button" name="cancelar" class="button-cancelar" value="Cancelar">
                                <input type="submit" name="cadastrar" class="button-cadastrar" value="Atualizar">
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="col-md-2"></div>
        </div>
        
        <div class="container c-footer">
            <footer></footer>
        </div>
        
        <script>
            function mascaraCpf(e){
                mascaraNumero(e, 11);
            }
    
            function mascaraCnpj(e){
                mascaraNumero(e, 14);
            }
    
            function load(){
                var cadastrar = document.getElementsByName("cadastrar")[0];
                var cancelar = document.getElementsByName("cancelar")[0];
                var tipo1 = document.getElementsByName("tipo")[0];
                var tipo2 = document.getElementsByName("tipo")[1];
                var cpfCnpjTitulo = document.getElementById("cpfCnpjTitulo");
                var cpfCnpjCel1 = document.getElementById("cpfCnpjCel1");
                var cpfCnpjCel2 = document.getElementById("cpfCnpjCel2");
                var sexoCel1 = document.getElementById("sexoCel1");
                var sexoCel2 = document.getElementById("sexoCel2");
                var dataNascimentoCel1 = document.getElementById("dataNascimentoCel1");
                var dataNascimentoCel2 = document.getElementById("dataNascimentoCel2");
                var sexo = document.getElementsByName("sexo")[0];
                var dataNascimento = document.getElementsByName("dataNascimento")[0];
                var form = document.getElementById("formAtualizarUsuario");
                
                tipo1.addEventListener("click", function(e){
                    var cpfCnpj = document.getElementsByName("cpfCnpj")[0];
                
                    cpfCnpjCel1.classList.remove("hide");
                    cpfCnpjCel2.classList.remove("hide");
                    
                    sexoCel1.classList.remove("hide");
                    sexoCel2.classList.remove("hide");
                    
                    dataNascimentoCel1.classList.remove("hide");
                    dataNascimentoCel2.classList.remove("hide");
                    dataNascimento.required = true;
                    sexo.required = true;
                    
                    cpfCnpj.classList.remove("cnpj");
                    cpfCnpj.classList.add("cpf");
                    cpfCnpjTitulo.innerHTML = "CPF";
                                        
                    cpfCnpj.removeEventListener('keypress', mascaraCnpj);
                    cpfCnpj.addEventListener('keypress', mascaraCpf);
                    
                });
                
                tipo2.addEventListener("click", function(e){
                    var cpfCnpj = document.getElementsByName("cpfCnpj")[0];
                
                    cpfCnpjCel1.classList.remove("hide");
                    cpfCnpjCel2.classList.remove("hide");
                    
                    sexoCel1.classList.add("hide");
                    sexoCel2.classList.add("hide");
                    
                    dataNascimentoCel1.classList.add("hide");
                    dataNascimentoCel2.classList.add("hide");
                    dataNascimento.required = false;
                    sexo.required = false;
        
                    cpfCnpj.classList.remove("cpf");
                    cpfCnpj.classList.add("cnpj");
                    cpfCnpjTitulo.innerHTML = "CNPJ";

                    cpfCnpj.removeEventListener('keypress', mascaraCpf);
                    cpfCnpj.addEventListener('keypress', mascaraCnpj);
                    
                });
                
                                
                
                form.addEventListener("submit", function(e){
                    if(cadastrar.value === "Cadastrar"){
                        document.getElementById("formAtualizarUsuario").submit();
                    }
                    else if(cadastrar.value === "Atualizar"){
                        var confirma = window.confirm("Deseja confirmar atualiza��o de dados?");
                        if(confirma){
                            document.getElementById("formAtualizarUsuario").submit();
                        }
                        else{
                            e.preventDefault();
                        }
                    }
                });
                /*
                cadastrar.addEventListener("submit", function(e){
                    if(e.target.value === "Cadastrar"){
                        document.getElementById("formCadastrarUsuario").submit();
                    }
                    
                });*/
                
                cancelar.addEventListener("click", function(e){
                    var confirma = window.confirm("Deseja confirmar cancelamento? Os dados preenchidos ser�o perdidos.");
                    if(confirma){
                        document.getElementById("formCadastrarUsuario").action = "/AnyMais/usuario/cadastrar-usuario.jsp";
                        document.getElementById("formCadastrarUsuario").submit();
                    }
                });

            }
            
            load();
        </script>
        <script src="/AnyMais/scripts/validacao.js?<%=Calendar.getInstance().getTime()%>"></script>
    </body>
</html>
