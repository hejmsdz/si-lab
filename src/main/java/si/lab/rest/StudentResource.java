package si.lab.rest;

import si.lab.model.Student;
import si.lab.storage.MemoryStore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("students/{index}")
public class StudentResource {
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Student get(@PathParam("index") long index) {
        Student student = MemoryStore.getInstance().getStudent(index);
        if (student == null) {
            throw new NotFoundException();
        }
        return student;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Student update(@PathParam("index") long index, Student newStudent) {
        Student student = MemoryStore.getInstance().updateStudent(index, newStudent);
        if (student == null) {
            throw new NotFoundException();
        }
        return student;
    }

    @DELETE
    public void delete(@PathParam("index") long index) {

        if (!MemoryStore.getInstance().deleteStudent(index)) {
            throw new NotFoundException();
        }
    }
}

