package Controller.Command;

import Model.Certificados;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import util.Command.Command;
import util.Zip.Zip;

/**
 *
 * @author Gabriel
 */
public class GerarCertificado implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArrayList<Certificados> todosInscritos = (ArrayList<Certificados>) request.getServletContext().getAttribute("todosInscritos");
        Map<String, Object> parametros = new HashMap<>();
        Map<String, byte[]> filesCertificados = new HashMap<>();
        //parametros.put("data_certificado", "10 de dezembro de 2016");
        parametros.put("data_certificado", GerarCertificado.getDataPorExtenso(todosInscritos.get(0).getData()));
        parametros.put("id_certificado", null);

        for (Certificados inscrito : todosInscritos) {
            parametros.replace("id_certificado", (Object) inscrito.getId());
            filesCertificados.put(inscrito.getNome(), getCertificado(parametros));
        }

        response.setHeader("Content-Disposition", "attachment;filename=certificados.zip");
        response.setContentType("application/zip");
        response.getOutputStream().write(Zip.getZip(filesCertificados));
        response.flushBuffer();
        return null;
    }

    private static String getDataPorExtenso(Date data) throws ParseException {//2017-01-03
        DateFormat dateFormatExtenso = new SimpleDateFormat("MMMMM", new Locale("pt", "BR"));
        Calendar calendario = GerarCertificado.getCalendar(data);
        return calendario.get(Calendar.DAY_OF_MONTH) + " de " + dateFormatExtenso.format(data) + " de " + calendario.get(Calendar.YEAR);
    }

    private static Calendar getCalendar(Date data) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(data);
        return calendario;
    }

    private static byte[] getCertificado(Map<String, Object> parametros) throws JRException, FileNotFoundException, SQLException, IOException {
        return GetRelatorio.getInstance().getRelatorio("certificadoCurso.jasper", parametros);
    }

}
