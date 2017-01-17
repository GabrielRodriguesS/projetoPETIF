package Controller.Command;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Command.Command;

/**
 *
 * @author PET BSI
 */
public class GerarListaInscritos implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment;filename=lista_" + request.getParameter("cursoSelecionado") + ".pdf");
        response.setContentType("application/pdf");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nomeCurso", (Object) request.getParameter("cursoSelecionado"));
        response.getOutputStream().write(GetRelatorio.getInstance().getRelatorio("listaInscritos.jasper", parametros));
        response.flushBuffer();
        return null;
    }

}
