<%-- 
    Document   : listaInscritos
    Created on : 17/06/2016, 10:06:47
    Author     : Gabriel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<h4>${gerarOuSalvarLista} Lista de Inscritos</h4>
<p>Selecione o curso que você deseja ${fn:toLowerCase(gerarOuSalvarLista)} a lista:</p>
<c:choose>
    <c:when test="${gerarOuSalvarLista eq 'Gerar'}">
        <form action="sitePET" method="GET" target="_blank">
    </c:when>
    <c:otherwise>
        <form action="sitePET" method="GET" >
    </c:otherwise>
</c:choose>

        <select name="cursoSelecionado">
            <c:forEach items="${nomeDoscursos}" var="nomeCurso">
                <option value="${nomeCurso}">${nomeCurso}</option>
            </c:forEach>
        </select>
        <br />
        <br />
        <input type="hidden" name="comando" value="${fn:toLowerCase(gerarOuSalvarLista)}Lista"/> 
        <input type="submit" value="${gerarOuSalvarLista}" />
    </form>
    
