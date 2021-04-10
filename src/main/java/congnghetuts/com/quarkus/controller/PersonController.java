package congnghetuts.com.quarkus.controller;

import congnghetuts.com.quarkus.exception.InvalidValueException;
import congnghetuts.com.quarkus.exception.ServerRuntimException;
import congnghetuts.com.quarkus.model.Person;
import congnghetuts.com.quarkus.service.PersonService;
import io.smallrye.mutiny.Uni;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/person")
public class PersonController extends HttpResponseController{

    @Inject
    PersonService service;

    @POST
    public Uni<Response> createPerson(Person person) {
        return service.createPerson(person)
                .onItem().transform(item ->
                        this.handleResponse(item, "The person has been created in DB successfully"))
                .onFailure(InvalidValueException.class).recoverWithItem(e -> {
                    InvalidValueException ex = (InvalidValueException) e;
                    return badRequest(ex.getErrors());
                })
                .onFailure(ServerRuntimException.class).recoverWithItem(e -> {
                    ServerRuntimException ex = (ServerRuntimException) e;
                    return serverError(ex.getErrors());
                })
                ;
    }


    @GET
    public Uni<List<Person>> getAll() {
        return service.getAll();
    }


    private Response handleResponse(Person person, String message) {
        return accepted(message, person);
    }

}
