package si.lab.rest;

import si.lab.model.Grade;
import si.lab.storage.Store;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("students/{index}/grades/{id}")
public class StudentGradeResource {
    @GET
    @Produces("application/json")
    public Grade get(@PathParam("index") long index, @PathParam("id") int id) {
        Grade grade = Store.getInstance().getStudentGrade(index, id);
        if (grade == null) {
            throw new NotFoundException();
        }
        return grade;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Grade update(@PathParam("index") long index, @PathParam("id") int id, Grade newGrade) {
        return Store.getInstance().updateStudentGrade(index, id, newGrade);
    }

    @DELETE
    public void delete(@PathParam("index") long index, @PathParam("id") int id) {
        Store.getInstance().deleteStudentGrade(index, id);
    }
}
