/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

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
}
