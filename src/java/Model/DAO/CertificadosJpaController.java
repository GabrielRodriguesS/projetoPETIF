/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Controller.ControllerPet;
import Model.Certificados;
import Model.DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Gabriel
 */
public class CertificadosJpaController implements Serializable {

    public CertificadosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Certificados certificados) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(certificados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Certificados certificados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            certificados = em.merge(certificados);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = certificados.getId();
                if (findCertificados(id) == null) {
                    throw new NonexistentEntityException("The certificados with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Certificados certificados;
            try {
                certificados = em.getReference(Certificados.class, id);
                certificados.getId();
            } catch (EntityNotFoundException e) {
                throw new NonexistentEntityException("The certificados with id " + id + " no longer exists.", e);
            }
            em.remove(certificados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Certificados> findCertificadosEntities() {
        return findCertificadosEntities(true, -1, -1);
    }

    public List<Certificados> findCertificadosEntities(int maxResults, int firstResult) {
        return findCertificadosEntities(false, maxResults, firstResult);
    }

    private List<Certificados> findCertificadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Certificados.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Certificados findCertificados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Certificados.class, id);
        } finally {
            em.close();
        }
    }

    public int getCertificadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Certificados> rt = cq.from(Certificados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public boolean findByNumeroCertificado(String numeroCertificado) {
        String sqlBuscaNumCertificado = "SELECT * FROM `certificados` WHERE numero_certificado like \"" + numeroCertificado + "\"";
        EntityManager em = null;
        int certificado = 0;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            try {
                Query q = em.createNativeQuery(sqlBuscaNumCertificado);
                certificado = q.getFirstResult();
            } catch (EntityNotFoundException e) {
                Logger.getLogger(ControllerPet.class.getName()).log(Level.SEVERE, null, e);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return certificado != 0;
    }

    public List<Certificados> getCertificadosNomeData() {
        //tem que criar um certificado para cada curso diferente no mysql
        return null;
    }

    public List<Certificados> getAllCertificadosByDataAndNome(Date data, String nome) {
        //pensar em metodo para retornar tds os certificados com uma data e um nome igual
        return null;
    }
}
