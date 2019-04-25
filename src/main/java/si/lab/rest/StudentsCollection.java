package si.lab.rest;

import si.lab.model.Student;
import si.lab.storage.MongoStore;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Collection;

@Path("students")
public class StudentsCollection {
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Student> get(
            @QueryParam("firstName") String firstName,
            @QueryParam("lastName") String lastName
    ) {
        return MongoStore.getInstance().getStudents(firstName, lastName);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response create(Student student) {
        MongoStore.getInstance().addStudent(student);
        URI location = UriBuilder.fromResource(StudentResource.class)
                .resolveTemplate("index", student.getIndex())
                .build();
        return Response.created(location).build();
    }
}
