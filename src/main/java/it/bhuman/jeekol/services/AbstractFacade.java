package it.bhuman.jeekol.services;

import it.bhuman.jeekol.entities.Course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbstractFacade<T> {

	private Class<T> entityClass;

	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract EntityManager getEntityManager();

	public T findStudensByCourseId(long id) {
		
		return getEntityManager().find(entityClass, id);
		 
	}
	
	public T findCourseById(long id) {
		
		return getEntityManager().find(entityClass, id);
	}
	
	public List<T> findAll() {

		return refreshCollection(count());
	}

	public List<T> count() {
		
		@SuppressWarnings("unchecked")
		CriteriaQuery<T> cq = (CriteriaQuery<T>) getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		
		@SuppressWarnings("unchecked")
		Iterator<Object> iterator = (Iterator<Object>) getEntityManager().createQuery(cq).getResultList().iterator();
		while (iterator.hasNext()){
			
			Course course = (Course) iterator.next();
			
			Query male = getEntityManager().createQuery("SELECT SUM(CASE WHEN s.gender = 'MALE' THEN 1 END) AS MALE FROM Course c JOIN c.attendees s WHERE c.id =:m_id");
			male.setParameter("m_id", course.getId());
			
			Query female = getEntityManager().createQuery("SELECT SUM(CASE WHEN s.gender = 'FEMALE' THEN 1 END) AS FEMALE FROM Course c JOIN c.attendees s WHERE c.id =:g_id");
			female.setParameter("g_id", course.getId());
			
			update(Integer.valueOf(male.getSingleResult().toString()), Integer.valueOf(female.getSingleResult().toString()), course.getId());
		}
		return getEntityManager().createQuery(cq).getResultList();
	}
	
	public void update(int m, int f, long id) {
		
		 Query update = getEntityManager().createQuery("UPDATE Course c SET male = :m, female = :f where c.id=:course_id");
          update.setParameter("course_id", id).setParameter("m", m).setParameter("f", f).executeUpdate();
	}
	
	public List<T> refreshCollection(List<T> entityCollection) {
	    
	        List<T> result = new ArrayList<T>();
	        if (entityCollection != null && !entityCollection.isEmpty()) {
	            getEntityManager().getEntityManagerFactory().getCache().evict(entityCollection.get(0).getClass());
	            T mergedEntity;
	            for (T entity : entityCollection) {
	                mergedEntity = getEntityManager().merge(entity);
	                getEntityManager().refresh(mergedEntity);
	                result.add(mergedEntity);
	            }
	        }
	        return result;
    }

}
