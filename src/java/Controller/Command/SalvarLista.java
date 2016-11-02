package Controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Command.Command;

/**
 *
 * @author Gabriel
 */
public class SalvarLista implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //implementar um codigo para pegar o campos em um list e salvar na tabela certificados do bd
        try {
            String[] nomesInscritos = request.getParameterValues("nome");
            
        } catch (Exception e) {
            throw e;
        }
        return null;  //so pra nao encher o saco
    }

}
