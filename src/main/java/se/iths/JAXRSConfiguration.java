package se.iths;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

// example.com/api/v1/users/1234

//@ApplicationPath("api/v1") best practive, then you need to add api/v1 to the url
@ApplicationPath("")
public class JAXRSConfiguration extends Application {

}
