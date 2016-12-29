package Controller.Command;

import Model.DAO.Dao;
import Model.DAO.WebformSubmittedDataJpaController;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Command.Command;

/**
 *
 * @author Gabriel
 */
public class ChamaSalvarLista implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        WebformSubmittedDataJpaController dao = Dao.getDaoWebFormData();
        String nomeCurso = request.getParameter("cursoSelecionado");
        List<String> nomesInscritos = dao.getAllInscritos(nomeCurso);
        request.setAttribute("alunosInscritos", nomesInscritos);
        request.setAttribute("nomeCurso", nomeCurso);
        request.setAttribute("dataMaxima", dateToString());
        return "salvaLista";
    }

    private static String dateToString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date).replaceAll("/", "-");
    }
}
