package Controller.Command;

import Model.Certificados;
import Model.DAO.CertificadosJpaController;
import Model.DAO.Dao;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Command.Command;
import util.Security.Criptografar;

/**
 *
 * @author Gabriel
 */
public class SalvarLista implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String nomesInscritos[] = request.getParameterValues("nome");
            CertificadosJpaController dao = Dao.getDaoCertificado();
            Certificados certificado;
            for (Object nomeInscrito : nomesInscritos) {
                certificado = getParametros(request);
                certificado.setNome(encodeString(nomeInscrito.toString()));
                certificado.setNumeroCertificado(getNumeroCertificado(dao.getCertificadosCount()));
                saveCertificado(certificado, dao);
            }
            request.setAttribute("mensagem", "Lista salva com sucesso!");
        } catch (Exception e) {
            request.setAttribute("mensagem", "Ocorreram problemas ao salvar a lista. :/");
            throw e;
        }
        return null;
    }

    private static void saveCertificado(Certificados certificado, CertificadosJpaController dao) throws Exception {
        dao.create(certificado);
    }

    private static Certificados getParametros(HttpServletRequest request) throws ParseException, UnsupportedEncodingException {
        Certificados certificadoRetorno = new Certificados();
        String nomeCurso = request.getParameter("nomeCursoAlterado");
        nomeCurso = encodeString(nomeCurso);
        String dataCurso = request.getParameter("dataCurso");
        String cargaHoraria = request.getParameter("cargaHoraria");

        certificadoRetorno.setCurso(nomeCurso);
        certificadoRetorno.setCargaHoraria(getInteger(cargaHoraria));
        certificadoRetorno.setData(getDate(dataCurso));
        return certificadoRetorno;
    }

    private static String encodeString(String string) throws UnsupportedEncodingException {
        byte stringByte[] = string.getBytes();
        return new String(stringByte, "UTF-8");
    }

    private static String getNumeroCertificado(Integer numeroCertificado) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return Criptografar.criptografar(numeroCertificado.toString());
    }

    private static Date getDate(String dataString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(dataString);
    }

    private static Integer getInteger(String string) {
        return Integer.parseInt(string);
    }
}
