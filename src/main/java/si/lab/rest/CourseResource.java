package si.lab.rest;

import si.lab.model.Course;
import si.lab.storage.MemoryStore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("courses/{id}")
public class CourseResource {
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Course get(@PathParam("id") int id) {
        Course course = MemoryStore.getInstance().getCourse(id);
        if (course == null) {
            throw new NotFoundException();
        }
        return course;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Course update(@PathParam("id") int id, Course newCourse) {
        Course course = MemoryStore.getInstance().updateCourse(id, newCourse);
        if (course == null) {
            throw new NotFoundException();
        }
        return course;
    }

    @DELETE
    public void delete(@PathParam("id") int id) {
        if (!MemoryStore.getInstance().deleteCourse(id)) {
            throw new NotFoundException();
        }
    }
}

