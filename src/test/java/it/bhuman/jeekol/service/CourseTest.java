package it.bhuman.jeekol.service;

import it.bhuman.jeekol.entities.Student;

import org.junit.Assert;
import org.junit.Test;

public class CourseTest extends AbstractDbUnitJpaTest{

	@Test
	public void testFind() {
		
	 Student student = entityManager.find(Student.class, 1L);
		Assert.assertNotNull(student);
		Assert.assertEquals("Rita", student.getName());
	}
		
		
}
