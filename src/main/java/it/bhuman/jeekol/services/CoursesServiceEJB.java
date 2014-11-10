package it.bhuman.jeekol.services;

import it.bhuman.jeekol.entities.Course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;

@Startup
@Stateless
@LocalBean
public class CoursesServiceEJB extends AbstractFacade<Course>{
	
	public CoursesServiceEJB() {
		super(Course.class);
	}

	private List<Course> courses;
	
	@PersistenceContext(unitName="bhuman-jee-kol", type=PersistenceContextType.EXTENDED)
	private EntityManager manager;
    
    public List<Course> getCourses() {
    	return courses;
    }
    
    @Override
    protected EntityManager getEntityManager() {
    	return manager;
    }
    
    @PostConstruct
    private void initialize() {
        
    	courses = onlyListCourse(findAll());
    }
    
    @PreDestroy
    private void destroy() {
      System.out.println("CoursesServiceEJB destroyed " + courses);
    }
    
	@PrePassivate
	private void prepareForPassivation() {
		System.out.println("In PrePassivate/PreDestroy");
		try {
			if (getEntityManager().isOpen()) {
				getEntityManager().close();
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	}
	
	@PostActivate
	private void restoreFromPassivation() {
		System.out.println("In PostActivate/PostConstruct");
		try {
			getEntityManager().getTransaction().begin();
		} catch (PersistenceException p) {
			p.printStackTrace();
		}
	}
    
	/**
	 * 
	 * @param list
	 * @return Ritorna una lista di corsi senza la lista degli studenti.
	 */
	private List<Course> onlyListCourse(List<Course> list){
	 	   
		   List<Course> listCourse = new ArrayList<Course>();
		   Course onlyCourse = null;
		   
	 	   for (Iterator<Course> iterator = list.iterator(); iterator.hasNext();) {
	 		    Course course = (Course) iterator.next();
	 		    if (course.getAttendees() != null && !course.getAttendees().isEmpty()) {
	 		    	onlyCourse = new Course();
	 		    	onlyCourse.setId(course.getId());
	 		    	onlyCourse.setName(course.getName());
	 		    	onlyCourse.setMale(course.getMale());
	 		    	onlyCourse.setFemale(course.getFemale());
	 		    	onlyCourse.setYear(course.getYear());
	 		    	listCourse.add(onlyCourse);
	 		    }
	        }
	 	   return listCourse;
	    }
}
