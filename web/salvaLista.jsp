<%-- 
    Document   : salvaLista
    Created on : 16/10/2016, 20:49:40
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h4>Salvar Lista de Inscritos do Curso ${nomeCurso}</h4>

<form action="sitePET" method="POST">  
    <p>Nome do Curso</p>
    <input type="text" name="nomeCursoAlterado" value="${nomeCurso}" /> *Altere só se necessário
    
    <p>Data em que o curso ocorreu:</p>
    <input type="date" name="dataCurso" max="${dataMaxima}" required />

    <p>Carga horária estimada (em horas):</p>
    <input type="number" name="cargaHoraria" placeholder="2" min="0" required />

    <p>*Envie apenas os nomes dos inscritos que compareceram ao minicurso <br>
    *Escreva corretamente o nome dos inscritos, pois será assim que eles serão impressos no certificado</p>
    <label><input type="button" name="add" value="Adicionar Participante" /></label> 
    <fieldset id="inputs_adicionais" style="border: none"> 
        <c:forEach items="${alunosInscritos}" var="nomeInscrito">
            <label style="display: block">
                <input type="text" name="nome" placeholder="Nome" value="${nomeInscrito}" />
                <a href="#" class="remove" style="color:red">X</a>
            </label>
        </c:forEach>
    </fieldset>  


    <input type="hidden" name="comando" value="salvarInscritos"/> 
    <input type="submit" value="Enviar" />
</form>

<script type="text/javascript" src="http://code.jquery.com/jquery-1.5.1.min.js"></script>  
<script type="text/javascript">
    $(document).ready(function () {
        n = $("input[name='nome']").length;
        var input = '<label style="display: block"><input type="text" name="nome" id="participante'+ n +'" placeholder="Nome"/> <a href="#" class="remove" style="color:red">X</a></label>';
        $("input[name='add']").click(function (e) {
            $('#inputs_adicionais').append(input);
             $('input[id=participante'+n+']').focus();
        });

        $('#inputs_adicionais').delegate('a', 'click', function (e) {
            e.preventDefault();
            $(this).parent('label').remove();
        });
    });
</script>