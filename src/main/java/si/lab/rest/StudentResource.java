package si.lab.rest;

import si.lab.model.Student;
import si.lab.storage.Store;

import javax.ws.rs.*;

@Path("students/{index}")
public class StudentResource {
    @GET
    @Produces("application/json")
    public Student get(@PathParam("index") long index) {
        Student student = Store.getInstance().getStudent(index);
        if (student == null) {
            throw new NotFoundException();
        }
        return student;
    }
}

