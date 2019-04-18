package si.lab.rest;

import si.lab.model.Grade;
import si.lab.storage.MongoStore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Collection;

@Path("students/{index}/grades")
public class StudentGradesCollection {
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Grade> get(@PathParam("index") long index) {
        Collection<Grade> grades = MongoStore.getInstance().getStudentGrades(index);
        if (grades == null) {
            throw new NotFoundException();
        }
        return grades;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response create(@PathParam("index") long index, Grade grade) {
        Grade insertedGrade = MongoStore.getInstance().addStudentGrade(index, grade);
        if (insertedGrade == null) {
            throw new NotFoundException();
        }
        URI location = UriBuilder.fromResource(StudentGradeResource.class)
                .resolveTemplate("index", index)
                .resolveTemplate("id", grade.getId())
                .build();
        return Response.created(location).build();
    }
}
