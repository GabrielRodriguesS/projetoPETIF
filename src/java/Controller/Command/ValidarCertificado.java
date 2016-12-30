/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Command;

import Model.DAO.Dao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Command.Command;

/**
 *
 * @author Gabriel
 */
public class ValidarCertificado implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String numeroCertificado = request.getParameter("numeroCertificado").toUpperCase();
        if (Dao.getCertificadosJpaController().findByNumeroCertificado(numeroCertificado)) {
            request.setAttribute("mensagem", "Encontramos seu certificado :D");
        } else {
            request.setAttribute("mensagem", "Seu certificado deu ruim :/");
        }
        return null;
    }

}
