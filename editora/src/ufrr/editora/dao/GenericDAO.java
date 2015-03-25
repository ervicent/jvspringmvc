package ufrr.editora.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;


public interface GenericDAO<T, ID extends Serializable> {

	public T save(T entity);

	public T update(T entity);

	public void delete(T entity);

	public List<T> getAll();

	public List<T> getAllOrder(String field);

	public T getById(ID id);

	public Query query(String query);

}
