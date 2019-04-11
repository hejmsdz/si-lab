package si.lab.rest;

import si.lab.model.Course;
import si.lab.storage.Store;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Collection;

@Path("courses")
public class CoursesCollection {
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Course> get() {
        return Store.getInstance().getCourses();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response create(Course course) {
        Store.getInstance().addCourse(course);
        URI location = UriBuilder.fromResource(CourseResource.class)
                .resolveTemplate("id", course.getId())
                .build();
        return Response.created(location).build();
    }
}
