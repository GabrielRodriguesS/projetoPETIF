package Controller.Command;

import Model.DAO.Dao;
import Model.DAO.WebformSubmittedDataJpaController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Command.Command;

/**
 *
 * @author PET BSI
 */
public class ChamaListaCursos implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        WebformSubmittedDataJpaController dao = Dao.getDaoWebFormData();
        request.setAttribute("nomeDoscursos", dao.getAllCursos());
        request.setAttribute("gerarOuSalvarLista", request.getParameter("gerarOuSalvarLista"));
        return "listaCursos";
    }

}
