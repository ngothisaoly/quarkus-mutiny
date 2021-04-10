package congnghetuts.com.quarkus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@RegisterForReflection
@Entity
public class Person extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer id;

    public String name;

    public String email;

    public Integer age;


    public static Person findByEmail(String email){
        return find("email", email).firstResult();
    }
}