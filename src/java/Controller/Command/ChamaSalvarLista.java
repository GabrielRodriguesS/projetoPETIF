package Controller.Command;

import Model.DAO.WebformSubmittedDataJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetoPETPU");
        WebformSubmittedDataJpaController dao = new WebformSubmittedDataJpaController(emf);
        request.setAttribute("alunosInscritos", dao.getAllInscritos(request.getParameter("cursoSelecionado")));
        return "salvaLista";
    }

}
