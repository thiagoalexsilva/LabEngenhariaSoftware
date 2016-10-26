<%-- 
    Document   : editar-cadastro-petshop
    Created on : 25/10/2016, 23:40:29
    Author     : Erica
--%>

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
        
        <div class="container">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <div class="menu menu-petshop">
                    <img src="images/profile-image.png" class="menu-profile-image"/><br>
                    <a href="editar-cadastro-petshop.jsp"><input type="image" src="images/editar-perfil-button.png" class="menu-editar-perfil" /></a><br>
                    <a href="servicos.html"><input type="image" src="images/servicos-button.png" class="menu-servicos" /></a><br>
                    <a href="mensagens-cliente.html"><input type="image" src="images/mensagens-button.png" class="menu-mensagens" /></a><br>
                    <a href="agendamentos-cliente.html"><input type="image" src="images/agendamento-button.png" class="menu-agendamento" /></a><br>
                    <input type="image" src="images/logout-button.png" class="logout-button" />
                </div>
                <a href="home-petshop.jsp"><input type="image" src="images/home-button2.png" class="menu2-home2" /></a>
                <a href="avaliacoes.html"><input type="image" src="images/avaliacoes-button.png" class="menu2-avaliacoes" /></a>
                <div class="principal principal-petshop">
                    <h4 class="title">Editar Perfil</h4>
                    
                    <form class="editar-form" action="">
                    <table>
                        <tr>
                          <td class="editar-cadastro-field">Nome </td>
                          <td colspan="4" class="editar-cadastro-field">
                              <input type="text" class="label-field-editar" name="nome" required>
                          </td>
                          <td>
                              <img class="editar-foto" src="images/profile-image.png"/><br>
                              <u class="selecionar-foto">Selecionar foto...</u>
                          </td>
                        </tr>
                        <tr>
                          <td class="editar-cadastro-field">Endereço </td>
                          <td colspan="5" class="editar-cadastro-field">
                              <input type="text" class="label-field-editar" name="endereco" required>
                          </td>
                        </tr>
                        <tr>
                          <td class="editar-cadastro-field">Bairro </td>
                          <td class="editar-cadastro-field">
                              <input type="text" class="label-field-teste editar-coluna1" name="bairro" required>
                          </td>
                          <td class="editar-cadastro-field">Complemento </td>
                          <td colspan="2" class="editar-cadastro-field">
                              <input type="text" class="label-field editar-coluna2" name="complemento" required>
                          </td>
                        </tr>
                        <tr>
                          <td class="editar-cadastro-field">CEP </td>
                          <td class="editar-cadastro-field">
                              <input type="text" class="label-field editar-coluna1" name="cep" required> 
                          </td>
                          <td class="editar-cadastro-field">Cidade </td>
                          <td class="editar-cadastro-field">
                              <input type="text" class="label-field editar-coluna2" name="cidade" required>
                          </td>
                        </tr>
                        <tr>
                          <td class="editar-cadastro-field">UF </td>
                          <td colspan="5" class="editar-cadastro-field">
                              <select name="uf" required class="editar-cadastro-uf">
                                <option></option>
                                <option value="AC">AC</option>
                                <option value="AL">AL</option>
                                <option value="AP">AP</option>
                                <option value="AM">AM</option>
                                <option value="BA">BA</option>
                                <option value="CE">CE</option>
                                <option value="DF">DF</option>
                                <option value="ES">ES</option>
                                <option value="GO">GO</option>
                                <option value="MA">MA</option>
                                <option value="MT">MT</option>
                                <option value="MS">MS</option>
                                <option value="MG">MG</option>
                                <option value="PA">PA</option>
                                <option value="PB">PB</option>
                                <option value="PR">PR</option>
                                <option value="PE">PE</option>
                                <option value="PI">PI</option>
                                <option value="RJ">RJ</option>
                                <option value="RN">RN</option>
                                <option value="RS">RS</option>
                                <option value="RO">RO</option>
                                <option value="RR">RR</option>
                                <option value="SC">SC</option>
                                <option value="SP">SP</option>
                                <option value="SE">SE</option>
                                <option value="TO">TO</option>
                              </select>
                          </td>
                        </tr>
                        <tr>
                          <td class="editar-cadastro-field">Telefone </td>
                          <td class="editar-cadastro-field">
                              <input type="text" class="label-field editar-coluna1" name="telefone" required>
                          </td>
                          <td class="editar-cadastro-field">Telefone 2 </td>
                          <td colspan="2" class="editar-cadastro-field">
                              <input type="text" class="label-field editar-coluna2" name="celular">
                          </td>
                        </tr>
                        <tr>
                          <td class="editar-cadastro-field">E-mail </td>
                          <td class="editar-cadastro-field">
                              <input type="text" class="label-field editar-coluna1" name="email" required>
                          </td>
                          <td class="editar-cadastro-field">Confirmar E-mail </td>
                          <td colspan="2" class="editar-cadastro-field">
                              <input type="text" class="label-field editar-coluna2" name="confirma-email" required>
                          </td>
                        </tr>
                        <tr>
                          <td class="editar-cadastro-field">Senha </td>
                          <td class="editar-cadastro-field">
                              <input type="password" class="label-field editar-coluna1" name="senha" required>
                          </td>
                          <td class="editar-cadastro-field">Confirmar Senha </td>
                          <td colspan="2" class="editar-cadastro-field">
                              <input type="password" class="label-field editar-coluna2" name="confirma-senha" required>
                          </td>
                        </tr>
                        <tr>
                            <td class="editar-cadastro-field">Mensagem </td>
                            <td colspan="2" class="editar-cadastro-field">
                              <textarea name="mensagem" rows="2" cols="30"></textarea> 
                            </td>
                        </tr>
                        <tr>
                            <td colspan="6" class="editar-cadastro-field">
                                <input type="submit" class="button-deletar" value="Deletar Conta">
                                <input type="submit" class="button-cancelar" value="Cancelar">
                                <input type="submit" class="button-cadastrar" value="Cadastrar">
                            </td>
                        </tr>
                    </table>
                </form>
                </div>
            </div>
            <div class="col-md-2"></div>
        </div>
        <div class="container c-footer">
            <footer></footer>
        </div>
    </body>
</html>
