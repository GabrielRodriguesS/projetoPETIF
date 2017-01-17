package util.Zip;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author Gabriel
 */
public class Zip {

    public static byte[] getZip(Map<String, byte[]> files) throws IOException {
        ByteArrayOutputStream zipRetorno = new ByteArrayOutputStream();
        try (ZipOutputStream zip = new ZipOutputStream(zipRetorno)) {
            ZipEntry zipEntry;
            for (Map.Entry<String, byte[]> entrySet : files.entrySet()) {
                String nomeCertificado = entrySet.getKey();
                byte[] bytesCertificado = entrySet.getValue();
                zipEntry = new ZipEntry(nomeCertificado);
                zip.putNextEntry(zipEntry);
                zip.write(bytesCertificado);
            }
        }
        return zipRetorno.toByteArray();
    }
}
