package Controller.Command;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
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

        response.getOutputStream().write(this.getRelatorio(request.getParameter("cursoSelecionado")));
        response.flushBuffer();
        return null;
    }

    private byte[] getRelatorio(Object nomeCurso) throws JRException, FileNotFoundException, SQLException {
        String path = this.getClass().getClassLoader().getResource("").getPath() + ".." + File.separator + ".." + File.separator + "relatorios" + File.separator + "listaInscritos.jasper";
        Map<String, Object> parametros = new HashMap<>();
        byte[] relatorio = null;

        if (path.contains("%20")) {
            path = path.replace("%20", " ");
        }
        try {
            parametros.put("nomeCurso", nomeCurso);
            JasperPrint jp = JasperFillManager.fillReport(path, parametros, this.getConexao());
            relatorio = JasperExportManager.exportReportToPdf(jp);
        } catch (JRException | SQLException e) {
            throw e;
        }
        return relatorio;
    }

    private Connection getConexao() throws SQLException {
        Connection conexao = null;
        try {
            conexao = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/petredesinternet?zeroDateTimeBehavior=convertToNull", "root", "");
        } catch (SQLException e) {
            throw e;
        }
        return conexao;
    }
}
