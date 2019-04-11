package si.lab.rest;

import si.lab.model.Grade;
import si.lab.storage.Store;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("students/{index}/grades/{id}")
public class StudentGradeResource {
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Grade get(@PathParam("index") long index, @PathParam("id") int id) {
        Grade grade = Store.getInstance().getStudentGrade(index, id);
        if (grade == null) {
            throw new NotFoundException();
        }
        return grade;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Grade update(@PathParam("index") long index, @PathParam("id") int id, Grade newGrade) {
        Grade grade = Store.getInstance().updateStudentGrade(index, id, newGrade);
        if (grade == null) {
            throw new NotFoundException();
        }
        return grade;
    }

    @DELETE
    public void delete(@PathParam("index") long index, @PathParam("id") int id) {
        if (!Store.getInstance().deleteStudentGrade(index, id)) {
            throw new NotFoundException();
        }
    }
}
