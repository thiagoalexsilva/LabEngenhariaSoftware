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
            <div class="col-md-8 body-cadastro">
                <h3>Cadastro</h3>
                <form action="/AnyMais/usuario/cadastrado">
                    <table>
                        <tr>
                          <th colspan="2" class="tipo-usuario">
                              <input type="radio" name="tipo-usuario" value="PF" required> Pessoa Física<br>
                          </th>
                          <th class="tipo-usuario">
                              <input type="radio" name="tipo-usuario" value="PJ" required> Pessoa Jurídica<br>
                          </th>
                          <th colspan="3"></th>
                        </tr>
                        <tr>
                          <td class="cadastrar-field">Nome </td>
                          <td colspan="5" class="cadastrar-field">
                              <input type="text" class="label-field" name="nome" required>
                          </td>
                        </tr>
                        <tr>
                          <td class="cadastrar-field"> Sexo </td>
                          <td class="cadastrar-field coluna1">
                              <select name="sexo" required class="cadastro-seleciona-sexo">
                                <option></option>
                                <option value="Feminino">Feminino</option>
                                <option value="Masculino">Masculino</option>
                                <option value="Outro">Outro</option>
                              </select>
                          </td>
                          <td class="cadastrar-field">Data Nascimento </td>
                          <td class="cadastrar-field">
                              <input type="date" name="nascimento" class="coluna2" required>
                          </td>
                          <td class="cadastrar-field">CPF/CNPJ </td>
                          <td class="cadastrar-field">
                              <input type="text" class="label-field" name="cpf-cnpj" required>
                          </td>
                        </tr>
                        <tr>
                          <td class="cadastrar-field">Endereço </td>
                          <td colspan="5" class="cadastrar-field">
                              <input type="text" class="label-field" name="endereco" required>
                          </td>
                        </tr>
                        <tr>
                          <td class="cadastrar-field">Bairro </td>
                          <td class="cadastrar-field">
                              <input type="text" class="label-field coluna1" name="bairro" required>
                          </td>
                          <td class="cadastrar-field">Complemento </td>
                          <td colspan="2" class="cadastrar-field">
                              <input type="text" class="label-field coluna2" name="complemento" required>
                          </td>
                        </tr>
                        <tr>
                          <td class="cadastrar-field">CEP </td>
                          <td class="cadastrar-field">
                              <input type="text" class="label-field coluna1" name="cep" required> 
                          </td>
                          <td class="cadastrar-field">Cidade </td>
                          <td class="cadastrar-field">
                              <input type="text" class="label-field coluna2" name="cidade" required>
                          </td>
                          <td class="cadastrar-field">UF</td>
                          <td class="cadastrar-field">
                              <select name="uf" required class="cadastro-uf">
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
                          <td class="cadastrar-field">Telefone </td>
                          <td class="cadastrar-field">
                              <input type="text" class="label-field coluna1" name="telefone" required>
                          </td>
                          <td class="cadastrar-field">Telefone 2 </td>
                          <td colspan="2" class="cadastrar-field">
                              <input type="text" class="label-field coluna2" name="celular">
                          </td>
                        </tr>
                        <tr>
                          <td class="cadastrar-field">E-mail </td>
                          <td class="cadastrar-field">
                              <input type="text" class="label-field coluna1" name="email" required>
                          </td>
                          <td class="cadastrar-field">Confirmar E-mail </td>
                          <td colspan="2" class="cadastrar-field">
                              <input type="text" class="label-field coluna2" name="confirma-email" required>
                          </td>
                        </tr>
                        <tr>
                          <td class="cadastrar-field">Senha </td>
                          <td class="cadastrar-field">
                              <input type="password" class="label-field coluna1" name="senha" required>
                          </td>
                          <td class="cadastrar-field">Confirmar Senha </td>
                          <td colspan="2" class="cadastrar-field">
                              <input type="password" class="label-field coluna2" name="confirma-senha" required>
                          </td>
                        </tr>
                        <tr>
                            <td colspan="6" class="cadastrar-field">
                                <input type="submit" class="button-cancelar" value="Cancelar">
                                <input type="submit" class="button-cadastrar" value="Cadastrar">
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
    </body>
</html>
