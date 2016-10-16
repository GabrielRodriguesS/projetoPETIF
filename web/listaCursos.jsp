<%-- 
    Document   : listaInscritos
    Created on : 17/06/2016, 10:06:47
    Author     : Gabriel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h4>Lista de Inscritos</h4>
<p>Selecione o curso que você deseja gerar a lista:</p>
<form action="sitePET" method="GET" target="_blank">
    <select name="cursoSelecionado">
        <c:forEach items="${nomeDoscursos}" var="nomeCurso">
            <option value="${nomeCurso}">${nomeCurso}</option>
        </c:forEach>
    </select>
    <br />
    <br />
    <input type="hidden" name="comando" value="criaLista"/> 
    <input type="submit" value="Imprimir" />
</form>
