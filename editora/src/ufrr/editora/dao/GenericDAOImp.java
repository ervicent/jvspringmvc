package ufrr.editora.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;


public class GenericDAOImp<T, ID extends Serializable> implements GenericDAO<T, ID> {

	private EntityManager entityManager;
	private final Class<T> persistentClass;
	private Logger log;

	public void setLog(Logger log) {
		this.log = log;
	}

	public Logger getLog() {
		return log;
	}

	public GenericDAOImp(Class<T> classe) {
		setLog(Logger.getLogger(this.getClass()));
		this.persistentClass = classe;
		this.entityManager = new JPAUtil().getEntityManager();
	}
	
	public Class<T> getPersintentClass() {
		return this.persistentClass;
	}

	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	protected EntityManager getEntityManager() {
		if (this.entityManager == null)
			throw new IllegalStateException("Erro:");
		return entityManager;
	}

	public T save(T entity) {
		getEntityManager().clear();
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(entity);
		getEntityManager().getTransaction().commit();
		return entity;
	}

	public T update(T entity) {
		try {
			getEntityManager().getTransaction().begin();
			getEntityManager().merge(entity);
			getEntityManager().getTransaction().commit();
			getEntityManager().close();
		} catch (Exception e) {
			getEntityManager().getTransaction().rollback();
		}
		return entity;
	}

	public void delete(T entity) {
		getEntityManager().getTransaction().begin();
		entity = getEntityManager().merge(entity);
		getEntityManager().remove(entity);
		getEntityManager().getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		String querySelect = "SELECT obj FROM " + persistentClass.getSimpleName() + " obj";
		Query query = getEntityManager().createQuery(querySelect);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAllByCodigo(String field, Integer codigo) {
		String querySelect = "SELECT obj FROM " + persistentClass.getSimpleName() + " obj WHERE upper(" + field + ") = ('" + codigo + "')";
		Query query = getEntityManager().createQuery(querySelect);
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<T> getAllByName(String field, String name) {
		String querySelect = "SELECT obj FROM " + persistentClass.getSimpleName() + " obj WHERE upper(" + field + ") like upper('%" + name + "%')";
		Query query = getEntityManager().createQuery(querySelect);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAllOrder(String field) {
		String querySelect = "SELECT obj FROM " + persistentClass.getSimpleName() + " obj ORDER BY " + field;
		Query query = getEntityManager().createQuery(querySelect);
		return query.getResultList();
	}

	public T getById(ID id) {
		return (T) getEntityManager().find(persistentClass, id);
	}

	public Query query(String query) {
		return getEntityManager().createQuery(query);
	}
}
