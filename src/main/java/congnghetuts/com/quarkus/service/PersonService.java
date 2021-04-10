package congnghetuts.com.quarkus.service;

import congnghetuts.com.quarkus.exception.ErrorValidator;
import congnghetuts.com.quarkus.exception.InvalidValueException;
import congnghetuts.com.quarkus.exception.ServerRuntimException;
import congnghetuts.com.quarkus.model.Person;
import io.smallrye.mutiny.Uni;


import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PersonService {

    @Transactional
    public Uni<Person> createPerson(Person person) {

        PersonService personService = new PersonService();

        return Uni.createFrom().item(person)
                .onItem().invoke(this::validate)
                .onItem().call(i -> personService.ngSave(i));
    }

    public Uni<List<Person>> getAll() {
        return Uni.createFrom().item(Person.listAll());
    }

    private Person validate(Person person) {
        if(Person.findByEmail(person.email) != null) {

            throw new InvalidValueException().
                    withError(new ErrorValidator("The email has already existed in DB"));
        }
        return person;
    }


    private Person save(Person person) {
        Person personSave = new Person();
        personSave.id = person.id;
        personSave.name = person.name;
        personSave.email = person.email;
        personSave.age = person.age;
        try {
            personSave.persist();
            return person;
        } catch (Exception ex) {
            throw new ServerRuntimException().withError(new ErrorValidator("Create the person failure"));
        }
    }

    private Uni<Person> ngSave(Person person) {
        return Uni.createFrom().item(save(person));

    }
}