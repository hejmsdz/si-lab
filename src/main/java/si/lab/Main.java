package si.lab;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import si.lab.rest.*;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class Main {
    public static void main(String[] args) {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        ResourceConfig config = new ResourceConfig(
                StudentsCollection.class,
                StudentResource.class,
                CoursesCollection.class,
                CourseResource.class,
                StudentGradesCollection.class,
                StudentGradeResource.class,
                DeclarativeLinkingFeature.class
//                ExceptionMapper.class
        );

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
    }
}
