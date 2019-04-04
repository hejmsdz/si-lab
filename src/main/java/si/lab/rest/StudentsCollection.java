package si.lab.rest;

import si.lab.model.Student;
import si.lab.storage.Store;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("students")
public class StudentsCollection {
    @GET
    @Produces("application/json")
    public List<Student> get() {
        return Store.getInstance().getStudents();
    }
}
