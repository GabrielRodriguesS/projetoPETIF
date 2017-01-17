/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Gabriel
 */
public class Dao {

    public static WebformSubmittedDataJpaController getDaoWebFormData() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetoPETPU");
        return new WebformSubmittedDataJpaController(emf);
    }

    public static CertificadosJpaController getDaoCertificado() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetoPETPU");
        return new CertificadosJpaController(emf);
    }

    public static CertificadosJpaController getCertificadosJpaController() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetoPETPU");
        return new CertificadosJpaController(emf);
    }

    public static Connection getConnection() throws SQLException {
        return (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/petredesinternet?zeroDateTimeBehavior=convertToNull", "root", "");
    }

    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
