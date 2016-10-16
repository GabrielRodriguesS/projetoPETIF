/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.DAO.exceptions.NonexistentEntityException;
import Model.WebformSubmissions;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author PET BSI
 */
public class WebformSubmissionsJpaController implements Serializable {

    public WebformSubmissionsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(WebformSubmissions webformSubmissions) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(webformSubmissions);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(WebformSubmissions webformSubmissions) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            webformSubmissions = em.merge(webformSubmissions);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = webformSubmissions.getSid();
                if (findWebformSubmissions(id) == null) {
                    throw new NonexistentEntityException("The webformSubmissions with id " + id + " no longer exists.");
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
            WebformSubmissions webformSubmissions;
            try {
                webformSubmissions = em.getReference(WebformSubmissions.class, id);
                webformSubmissions.getSid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The webformSubmissions with id " + id + " no longer exists.", enfe);
            }
            em.remove(webformSubmissions);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<WebformSubmissions> findWebformSubmissionsEntities() {
        return findWebformSubmissionsEntities(true, -1, -1);
    }

    public List<WebformSubmissions> findWebformSubmissionsEntities(int maxResults, int firstResult) {
        return findWebformSubmissionsEntities(false, maxResults, firstResult);
    }

    private List<WebformSubmissions> findWebformSubmissionsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(WebformSubmissions.class));
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

    public WebformSubmissions findWebformSubmissions(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(WebformSubmissions.class, id);
        } finally {
            em.close();
        }
    }

    public int getWebformSubmissionsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<WebformSubmissions> rt = cq.from(WebformSubmissions.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
