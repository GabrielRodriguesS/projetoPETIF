package Controller.Command;

import Model.DAO.WebformSubmittedDataJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Command.Command;

/**
 *
 * @author PET BSI
 */
public class ChamaListaCursos implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetoPETPU");
        WebformSubmittedDataJpaController dao = new WebformSubmittedDataJpaController(emf);
        request.setAttribute("nomeDoscursos", dao.getAllCursos());
        request.setAttribute("gerarOuSalvarLista", request.getParameter("gerarOuSalvarLista"));
        return "listaCursos";
    }

}
