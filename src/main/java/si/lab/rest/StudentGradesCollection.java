package si.lab.rest;

import si.lab.model.Grade;
import si.lab.model.Student;
import si.lab.storage.Store;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Collection;

@Path("students/{index}/grades")
public class StudentGradesCollection {
    @GET
    @Produces("application/json")
    public Collection<Grade> get(@PathParam("index") long index) {
        Collection<Grade> grades = Store.getInstance().getStudentGrades(index);
        if (grades == null) {
            throw new NotFoundException();
        }
        return grades;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("index") long index, Grade grade) {
        Store.getInstance().addStudentGrade(index, grade);
        URI location = UriBuilder.fromResource(StudentGradeResource.class)
                .resolveTemplate("index", index)
                .resolveTemplate("id", grade.getId())
                .build();
        return Response.created(location).build();
    }
}
