package Model.DAO;

import Controller.ControllerPet;
import Model.DAO.exceptions.NonexistentEntityException;
import Model.DAO.exceptions.PreexistingEntityException;
import Model.WebformSubmittedData;
import Model.WebformSubmittedDataPK;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
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
public class WebformSubmittedDataJpaController implements Serializable {

    public WebformSubmittedDataJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(WebformSubmittedData webformSubmittedData) throws PreexistingEntityException, Exception {
        if (webformSubmittedData.getWebformSubmittedDataPK() == null) {
            webformSubmittedData.setWebformSubmittedDataPK(new WebformSubmittedDataPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(webformSubmittedData);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findWebformSubmittedData(webformSubmittedData.getWebformSubmittedDataPK()) != null) {
                throw new PreexistingEntityException("WebformSubmittedData " + webformSubmittedData + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(WebformSubmittedData webformSubmittedData) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            webformSubmittedData = em.merge(webformSubmittedData);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                WebformSubmittedDataPK id = webformSubmittedData.getWebformSubmittedDataPK();
                if (findWebformSubmittedData(id) == null) {
                    throw new NonexistentEntityException("The webformSubmittedData with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(WebformSubmittedDataPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            WebformSubmittedData webformSubmittedData;
            try {
                webformSubmittedData = em.getReference(WebformSubmittedData.class, id);
                webformSubmittedData.getWebformSubmittedDataPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The webformSubmittedData with id " + id + " no longer exists.", enfe);
            }
            em.remove(webformSubmittedData);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<WebformSubmittedData> findWebformSubmittedDataEntities() {
        return findWebformSubmittedDataEntities(true, -1, -1);
    }

    public List<WebformSubmittedData> findWebformSubmittedDataEntities(int maxResults, int firstResult) {
        return findWebformSubmittedDataEntities(false, maxResults, firstResult);
    }

    private List<WebformSubmittedData> findWebformSubmittedDataEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(WebformSubmittedData.class));
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

    public WebformSubmittedData findWebformSubmittedData(WebformSubmittedDataPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(WebformSubmittedData.class, id);
        } finally {
            em.close();
        }
    }

    public int getWebformSubmittedDataCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<WebformSubmittedData> rt = cq.from(WebformSubmittedData.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //esse sql retorna os alunos inscritos usando como parametro o nomeCurso
    public List<String> getAllInscritos(String nomeCurso) {
        String sqlBuscaNomes = "SELECT data FROM `webform_submitted_data` WHERE sid in \n"
                + "(select sid FROM `webform_submitted_data` WHERE cid=20 and data like"
                + " \"" + nomeCurso + "\") and cid = 1";
        EntityManager em = null;
        List<String> pessoasInscritas = new ArrayList();
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            try {
                Query q = em.createNativeQuery(sqlBuscaNomes);
                pessoasInscritas = q.getResultList();
            } catch (EntityNotFoundException enfe) {
                Logger.getLogger(ControllerPet.class.getName()).log(Level.SEVERE, null, enfe);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return pessoasInscritas;
    }

    public Integer countAllInscritos(String nomeCurso) {
        return this.getAllInscritos(nomeCurso).size();
    }

    //cid 20 é o campo do bd que tem os nomes dos cursos;
    public List<String> getAllCursos() {
        EntityManager em = null;
        List<String> cursosExistentes = new ArrayList();
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            try {
                Query q = em.createNativeQuery("SELECT DISTINCT data FROM webform_submitted_data WHERE cid = 20");
                cursosExistentes = q.getResultList();
            } catch (Exception ex) {
                Logger.getLogger(ControllerPet.class.getName()).log(Level.SEVERE, null, ex);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return cursosExistentes;
    }

    public List getAllSid(String nomeCurso) {
        String sqlBuscaSid = "SELECT sid FROM `webform_submitted_data` WHERE sid in \n"
                + "(select sid FROM `webform_submitted_data` WHERE cid=20 and data like"
                + " \"" + nomeCurso + "\") and cid = 1";
        EntityManager em = null;
        List<String> sidPessoasInscritas = new ArrayList();
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            try {
                
                Query q = em.createNativeQuery(sqlBuscaSid);
                sidPessoasInscritas = q.getResultList();
            } catch (EntityNotFoundException enfe) {
                Logger.getLogger(ControllerPet.class.getName()).log(Level.SEVERE, null, enfe);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return sidPessoasInscritas;
    }

    public TreeMap getAllSidAndInscritos(String nomeCurso, List<String> inscritos) {
        List sid = this.getAllSid(nomeCurso);
        TreeMap sidAndInscritos = new TreeMap();
        int posicao = sid.size();
        for (int i = posicao; i > 0; i--) {
            posicao--;
            sidAndInscritos.put(inscritos.get(posicao), sid.get(posicao));
        }
        return sidAndInscritos;
    }
}
