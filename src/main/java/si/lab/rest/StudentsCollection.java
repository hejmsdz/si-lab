package si.lab.rest;

import si.lab.model.Student;
import si.lab.storage.Store;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("students")
public class StudentsCollection {
    @GET
    @Produces("application/json")
    public Collection<Student> get() {
        return Store.getInstance().getStudents();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Student student) {
        Store.getInstance().addStudent(student);
    }
}
