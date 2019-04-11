package si.lab.rest;

import si.lab.model.Student;
import si.lab.storage.Store;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Student update(@PathParam("index") long index, Student newStudent) {
        return Store.getInstance().updateStudent(index, newStudent);
    }

    @DELETE
    public void delete(@PathParam("index") long index) {
        Store.getInstance().deleteStudent(index);
    }
}

