<%-- 
    Document   : index
    Created on : 17/06/2016, 09:12:28
    Author     : PET BSI
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <title>Projeto PET v1.2.0</title>
    </head>
    <body>
        <h3>Utilidades PET!</h3>

        <a href="sitePET?comando=chamaListaCursos&gerarOuSalvarLista=Gerar">Criar lista de presença</a> <br>
        <a href="sitePET?comando=chamaListaCursos&gerarOuSalvarLista=Salvar">Salvar lista de presença</a> <br>
        <a href="sitePET?comando=chamaValidarCertificado">Validar certificado</a> <br>
        <a href="sitePET?comando=gerarCertificado">Gerar certificado</a>

        <c:choose>
            <c:when test="${pagina != null}">
                <c:import url="${pagina}.jsp"></c:import>
            </c:when>
            <c:when test="${mensagem != null}">
                <br><p><c:out value="${mensagem}"></c:out></p>
            </c:when>
        </c:choose>

    </body>
</html>
