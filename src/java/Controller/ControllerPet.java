package Controller;

import Controller.Command.ChamaGerarCertificado;
import Controller.Command.ChamaListaCursos;
import Controller.Command.ChamaSalvarLista;
import Controller.Command.ChamaValidarCertificado;
import Controller.Command.GerarCertificado;
import Controller.Command.SalvarLista;
import Controller.Command.GerarListaInscritos;
import Controller.Command.NoCommand;
import Controller.Command.ValidarCertificado;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Command.Command;

/**
 *
 * @author PET BSI NÃO ESQUECER QUE ESSE CONTROLLER É CHAMADO DE sitePET!
 */
public class ControllerPet extends HttpServlet {

    private static final Map<String, Command> commandsMap = new HashMap<>();

    static {
        commandsMap.put("gerarLista", new GerarListaInscritos());//Cria lista de inscritos
        commandsMap.put("gerarCertificado", new GerarCertificado());//Cria lista de inscritos
        commandsMap.put("salvarLista", new ChamaSalvarLista());//Chama a pagina para inserir os dados do curso dado e os inscritos
        commandsMap.put("gerarCertificado", new ChamaGerarCertificado());//Chama a pagina para gerar certificados
        commandsMap.put("salvarInscritos", new SalvarLista());//Salva lista de presentes no curso
        commandsMap.put("chamaValidarCertificado", new ChamaValidarCertificado());//Valida Certificado e da opção de gerar novamente
        commandsMap.put("validarCertificado", new ValidarCertificado());//Valida Certificado e da opção de gerar novamente
        commandsMap.put("chamaListaCursos", new ChamaListaCursos());//Chama a pagina com os cursos existentes
        commandsMap.put("noCommand", new NoCommand());//chamada de comandos errado e pagina de erros
    }

    public static Command get(String commandName) {
        if (commandName == null || !commandsMap.containsKey(commandName)) {
            return commandsMap.get("noCommand");
        }
        return commandsMap.get(commandName);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        Command servlet = ControllerPet.get(request.getParameter("comando"));
        request.setAttribute("pagina", servlet.execute(request, response));
        if (!response.isCommitted()) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ControllerPet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("pagina", "noCommand");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ControllerPet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("pagina", "noCommand");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
