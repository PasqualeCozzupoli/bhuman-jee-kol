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

import java.util.Set;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author uji
 */
@Path("/training")
@Produces(MediaType.APPLICATION_JSON)
public class CoursesRESTService
{
	
    @EJB
    private DummyDataStore dataStore;
    
	@EJB
	private CoursesServiceEJB coursesServiceEJB;
    
    @GET
    @Path("/courses")
    public Response getCourses()
    {
        return Response.ok().entity(coursesServiceEJB.getCourses()).build();
    }


    @GET
    @Path("/course/{id}")
    public Response getCourse(@PathParam("id") long id)
    {
        Course course = coursesServiceEJB.findCourseById(id);

        if (course == null)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(course).build();
    }

    @GET
    @Path("/course/{id}/attendees")
    public Response getAttendees(@PathParam("id") long id)
    {
		Set<Student> attendees = coursesServiceEJB.findStudensByCourseId(id).getAttendees();

        if (attendees == null)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(attendees).build();
    }
     
}
