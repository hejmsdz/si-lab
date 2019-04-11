package si.lab.rest;

import si.lab.model.Course;
import si.lab.storage.Store;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("courses/{id}")
public class CourseResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Course get(@PathParam("id") int id) {
        Course course = Store.getInstance().getCourse(id);
        if (course == null) {
            throw new NotFoundException();
        }
        return course;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Course update(@PathParam("id") int id, Course newCourse) {
        Course course = Store.getInstance().updateCourse(id, newCourse);
        if (course == null) {
            throw new NotFoundException();
        }
        return course;
    }

    @DELETE
    public void delete(@PathParam("id") int id) {
        Store.getInstance().deleteCourse(id);
    }
}

