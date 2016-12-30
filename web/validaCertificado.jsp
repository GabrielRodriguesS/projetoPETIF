<%-- 
    Document   : validaCertificado
    Created on : 29/12/2016, 23:41:35
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h4>Insira o numero do seu certificado</h4>
<form action="sitePET" method="POST">
    Numero do seu certificado:
    <input type="text" name="numeroCertificado" max="32" required />
    <input type="hidden" name="comando" value="validarCertificado"/> 
    <input type="submit" value="Enviar" />
</form>
<img 
