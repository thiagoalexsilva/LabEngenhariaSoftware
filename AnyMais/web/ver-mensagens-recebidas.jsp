<%-- 
    Document   : ver-mensagens-recebidas
    Created on : Nov 20, 2016, 11:09:01 AM
    Author     : Gustavo
--%>

<%@page import="model.entity.Usuario"%>
<%@page import="model.entity.Mensagem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Any+</title>
    </head>
    <body>
        <%
            boolean eUsuario = false;
            boolean ePetshop = false;
            
            String link = "";
            
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            if(usuario != null){
                eUsuario = true;
            }
            else{
                usuario = (Usuario) session.getAttribute("petshop");
                if(usuario != null){
                    ePetshop = true;
                }
            }
            
            String part = eUsuario ? "usuario" : ePetshop ? "petshop" : "";
            link = "/AnyMais/" + part;
            
            Mensagem[] mensagens = (Mensagem[]) session.getAttribute("mensagens");
        %>
        <form id="formmensagens" action="<%= link %>" method="post">
            <input type="hidden" name="excluido" value=""/>
            <table>
                <tr>
                    <th>Assunto</th>
                    <th>Remetente</th>
                    <th>Data</th>
                    <th></th>
                </tr>
                <%
                    if(mensagens != null){
                        for(Mensagem m : mensagens){
                %>
                            <tr>
                                <td><a href="<%= link + "/" + m.getIdMensagem() %>"><%= m.getAssunto() %></a></td>
                                <td><%= m.getRemetente().getPessoa().getNome()%> <<%= m.getRemetente().getConta().getEmail() %>></td>
                                <td><%= m.getData() %></td>
                                <td><input type="image" name="excluir" id="<%= m.getIdMensagem() %>" src="/AnyMais/images/excluir-button.png"/></td>
                            </tr>
                <%
                        }
                    }
                    else{
                %>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                <%        
                    }
                %>
            </table>
        </form>
        <script>
            function load(){
                
                var i=0;
                var a_tags = document.getElementsByTagName("a");
                var form = document.getElementById("formmensagens");
                
                for(i=0; i<a_tags.length; i++ ){
                    a_tags[i].addEventListener("click", function(e){
                        form.action = e.currentTarget.href;
                        form.submit();
                    });
                }
                
                for(i=0; i<excluir.length; i++){
                    excluir[i].addEventListener("click", function(e){
                        document.getElementsByName("excluido")[0].value = e.target.id;
                        form.action = "/AnyMais/usuario/excluir";
                        form.submit();
                    });
                }
            }
            
            load();
        </script>
    </body>
</html>
