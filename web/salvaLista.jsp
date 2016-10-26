<%-- 
    Document   : salvaLista
    Created on : 16/10/2016, 20:49:40
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h4>Salvar Lista de Inscritos</h4>

<form action="sitePET" method="POST">  

    Todos<input type="checkbox" id="select_all"/>
    <label><input type="button" name="add" value="Adicionar Participante" /></label> 
    <fieldset id="inputs_adicionais" style="border: none"> 
        <label style="display: block"><input type="checkbox" name="check"><input type="text" name="nome" placeholder="Nome"></label>  

    </fieldset>  


    <input type="hidden" name="comando" value="salvarInscritos"/> 
    <input type="submit" value="Enviar" />
</form>

<script type="text/javascript" src="http://code.jquery.com/jquery-1.5.1.min.js"></script>  
<script type="text/javascript">
    $(document).ready(function () {

        var input = '<label style="display: block"><input type="checkbox" name="check" /><input type="text" name="nome" placeholder="Nome"/> <a href="#" class="remove" style="color:red">X</a></label>';
        $("input[name='add']").click(function (e) {
            $('#inputs_adicionais').append(input);
        });

        $('#inputs_adicionais').delegate('a', 'click', function (e) {
            e.preventDefault();
            $(this).parent('label').remove();
        });

        $('#select_all').change(function () {
            var checkboxes = $(this).closest('form').find(':checkbox');
            if ($(this).is(':checked')) {
                checkboxes.prop('checked', true);
            } else {
                checkboxes.prop('checked', false);
            }
        });

    });
</script>