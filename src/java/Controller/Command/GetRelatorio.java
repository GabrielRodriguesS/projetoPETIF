package Controller.Command;

import Model.DAO.Dao;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Gabriel
 */
public class GetRelatorio {

    private static GetRelatorio instance = new GetRelatorio();

    private GetRelatorio() {
    }

    public static GetRelatorio getInstance() {
        return instance;
    }

    public byte[] getRelatorio(String nomeArquivoDoRelatorio, Map<String, Object> parametros) throws JRException, FileNotFoundException, SQLException {
        byte[] relatorio = null;
        String path = getPath(nomeArquivoDoRelatorio);
        Connection connection;
        try {
            connection = Dao.getConnection();
            JasperPrint jp = JasperFillManager.fillReport(path, parametros, connection);
            relatorio = JasperExportManager.exportReportToPdf(jp);
            Dao.closeConnection(connection);
        } catch (JRException | SQLException e) {
            throw e;
        }
        return relatorio;
    }

    private String getPath(String nomeArquivo) {
        String path = this.getClass().getClassLoader().getResource("").getPath() + ".." + File.separator + ".." + File.separator + "relatorios" + File.separator + nomeArquivo;
        if (path.contains("%20")) {
            path = path.replace("%20", " ");
        }
        return path;
    }
}
