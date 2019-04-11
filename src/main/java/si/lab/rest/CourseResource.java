package si.lab.rest;

import si.lab.model.Course;
import si.lab.storage.Store;

import javax.ws.rs.*;

@Path("courses/{id}")
public class CourseResource {
    @GET
    @Produces("application/json")
    public Course get(@PathParam("id") int id) {
        Course course = Store.getInstance().getCourse(id);
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

