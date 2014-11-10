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

package it.bhuman.jeekol.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

/**
 *
 * @author uji
 * 
 *         TEST: questo deve diventare un @Entity
 *         http://docs.oracle.com/javaee/6/tutorial/doc/bnbqa.html
 * 
 */
@Entity
public class Course implements Serializable{
	
	private long id;
	private String name;
	private int year;
	private int male;
	private int female;
	
	private Set<Student> attendees;

	public Course() {

	}
    
	public Course(String name, int year) {
		this.name = name;
		this.year = year;
	}

	public Course(String name, int year, int male, int female) {
		this.name = name;
		this.year = year;
		this.male = male;
		this.female = female;
	}

	public Course(long id, String name, int year) {
		this.id = id;
		this.name = name;
		this.year = year;
	}

	public Course(String name, int year, Set<Student> attendees) {
		this.name = name;
		this.year = year;
		this.attendees = attendees;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the attendees
	 * 
	 *         questa campo deve mappare una relazione N:M unidirezionale
	 * 
	 */
	@ElementCollection(targetClass = Student.class)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "COURSE_STUDENT", joinColumns = @JoinColumn(name = "COURSE_ID"), inverseJoinColumns = @JoinColumn(name = "STUDENT_ID"))
	public Set<Student> getAttendees() {
		return attendees;
	}

	/**
	 * @param attendees
	 *            the attendees to set
	 */
	public void setAttendees(Set<Student> attendees) {
		this.attendees = attendees;
	}

	/**
	 * @return the name
	 */
	@Column(name = "NAME", unique = false, nullable = false)
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the year
	 */
	@Column(name = "YEAR", unique = false, nullable = false)
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
     
	@Column(name = "MALE", unique = false, nullable = false)
	public int getMale() {
		return male;
	}

	public void setMale(int male) {
		this.male = male;
	}

	@Column(name = "FEMALE", unique = false, nullable = false)
	public int getFemale() {
		return female;
	}

	public void setFemale(int female) {
		this.female = female;
	}

	public void addStudent(Student student) {
		if (attendees == null) {
			attendees = new HashSet<Student>();
		}
		if (!attendees.contains(student)) {
			attendees.add(student);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Course [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", year=");
		builder.append(year);
		builder.append(", male=");
		builder.append(male);
		builder.append(", female=");
		builder.append(female);
		builder.append(", attendees=");
		builder.append(attendees);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attendees == null) ? 0 : attendees.hashCode());
		result = prime * result + female;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + male;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (attendees == null) {
			if (other.attendees != null)
				return false;
		} else if (!attendees.equals(other.attendees))
			return false;
		if (female != other.female)
			return false;
		if (id != other.id)
			return false;
		if (male != other.male)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
}