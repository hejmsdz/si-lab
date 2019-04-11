package si.lab.rest;

import si.lab.model.Student;
import si.lab.storage.Store;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Collection;

@Path("students")
public class StudentsCollection {
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Student> get() {
        return Store.getInstance().getStudents();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Student student) {
        Store.getInstance().addStudent(student);
        URI location = UriBuilder.fromResource(StudentResource.class)
                .resolveTemplate("index", student.getIndex())
                .build();
        return Response.created(location).build();
    }
}
