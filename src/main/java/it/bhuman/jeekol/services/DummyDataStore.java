/*
 * Copyright (C) 2014 B Human Srl.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

package it.bhuman.jeekol.services;

import it.bhuman.jeekol.entities.Course;
import it.bhuman.jeekol.entities.Student;
import it.bhuman.jeekol.entities.Student.Gender;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author uji
 */
@Startup
@Singleton
public class DummyDataStore {
	private Set<Course> courses = null;

	@PostConstruct
	void init() {
		Student s0 = new Student(0, "Andrea", Gender.MALE);
		Student s1 = new Student(1, "Mariano", Gender.MALE);
		Student s2 = new Student(2, "Silvio", Gender.MALE);
		Student s3 = new Student(3, "Stefano", Gender.MALE);
		Student s4 = new Student(4, "Francesca", Gender.FEMALE);
		Student s5 = new Student(5, "Manuela", Gender.FEMALE);
		Student s6 = new Student(6, "Rita", Gender.FEMALE);
		Student s7 = new Student(7, "Paolo", Gender.MALE);
		Student s8 = new Student(8, "Nicoletta", Gender.FEMALE);
		Student s9 = new Student(9, "Bernardo", Gender.MALE);

		Set<Student> ss0 = new HashSet<Student>();

		ss0.add(s0);
		ss0.add(s1);
		ss0.add(s6);

		Set<Student> ss1 = new HashSet<Student>();

		ss1.add(s1);
		ss1.add(s2);
		ss1.add(s7);

		Set<Student> ss2 = new HashSet<Student>();

		ss2.add(s4);
		ss2.add(s5);
		ss2.add(s6);

		Set<Student> ss3 = new HashSet<Student>();

		ss3.add(s2);
		ss3.add(s3);
		ss3.add(s4);
		ss3.add(s5);

		Set<Student> ss4 = new HashSet<Student>();

		ss4.add(s0);
		ss4.add(s1);
		ss4.add(s6);
		ss4.add(s8);
		ss4.add(s9);

		courses = new HashSet<Course>();

		Course c0 = new Course(0, "Analisi 1", 2014);
		c0.setAttendees(ss0);

		Course c1 = new Course(1, "Analisi 2", 2014);
		c1.setAttendees(ss1);

		Course c2 = new Course(2, "Fisica 1", 2014);
		c2.setAttendees(ss2);

		Course c3 = new Course(3, "Fisica 2", 2014);
		c3.setAttendees(ss3);

		Course c4 = new Course(4, "Fondameni di Informatica 1", 2014);
		c4.setAttendees(ss4);

		courses.add(c0);
		courses.add(c1);
		courses.add(c2);
		courses.add(c3);
		courses.add(c4);
	}

	/**
	 * @return the courses
	 */
	public Set<Course> findAllCourses() {
		return courses;
	}

	public Course findCourseById(long id) {
		if (courses == null)
			return null;

		for (Course course : courses) {
			if (course.getId() == id)
				return course;
		}

		return null;
	}

	public Set<Student> findStudensByCourseId(long id) {
		if (courses == null)
			return null;

		for (Course course : courses) {
			if (course.getId() == id) {
				if (course.getAttendees() == null)
					return new HashSet<Student>();

				return course.getAttendees();
			}
		}

		return null;
	}
}
