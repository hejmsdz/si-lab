package si.lab.rest;

import si.lab.model.Course;
import si.lab.storage.Store;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Collection;

@Path("courses")
public class CoursesCollection {
    @GET
    @Produces("application/json")
    public Collection<Course> get() {
        return Store.getInstance().getCourses();
    }
}
