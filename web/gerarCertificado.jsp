<%-- 
    Document   : gerarCertificado
    Created on : 04/01/2017, 00:31:47
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h4>Gerar Certificados</h4>
<form action="sitePET" method="POST"> 
    Curso: <select name="curso">
        <c:forEach items="${certificados}" var="certificado">
            <option readonly value="${certificado}">${certificado.nome} / ${certificado.data} </option>
        </c:forEach>
    </select>
</form>
