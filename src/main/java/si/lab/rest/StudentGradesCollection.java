package si.lab.rest;

import si.lab.model.Grade;
import si.lab.storage.Store;

import javax.ws.rs.*;
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
}
