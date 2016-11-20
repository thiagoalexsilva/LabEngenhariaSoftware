<%-- 
    Document   : enviar-mensagem
    Created on : Nov 20, 2016, 1:33:20 PM
    Author     : Gustavo
--%>

<%@page import="model.entity.Usuario"%>
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
            
            String part = eUsuario ? "usuario/enviado" : ePetshop ? "petshop/enviado" : "";
            link = "/AnyMais/" + part;

        %>
        <form id="formmensagem" action="<%= link %>" method="post">
            <input type="hidden" name="excluido" value=""/>
            <table>
                <tr>
                    <td>Assunto: </td>
                    <td><input type="text" name="assunto"></td>
                </tr>
                <tr>
                    <td>Destinatário: </td>
                    <td><input type="text" name="destinatario"></td>
                </tr>
                <tr>
                    <td>Mensagem: </td>
                    <td><input type="text" name="mensagem"></td>
                </tr>
            </table>
            <input type="button" name="cancelar" value="Cancelar">
            <input type="submit" name="enviar" value="Enviar">
        </form>
    </body>
    <script>
        function load(){
            var cancelar = document.getElementsByName("cancelar")[0];
            
            cancelar.addEventListener("click", function(e){
                    var confirma = window.confirm("Deseja confirmar cancelamento? Os dados preenchidos serão perdidos.");
                    if(confirma){
                        document.getElementById("formmensagem").action = link;
                        document.getElementById("formmensagem").submit();
                    }
                });
        }
        
        load();
    </script>
</html>
