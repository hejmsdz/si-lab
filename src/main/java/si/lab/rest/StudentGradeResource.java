package si.lab.rest;

import si.lab.model.Grade;
import si.lab.storage.Store;

import javax.ws.rs.*;

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
}
