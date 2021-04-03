package congnghetuts.com.quarkus.multi;

import io.smallrye.mutiny.Multi;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MultiMutiny {
    public static void main(String[] args) {
        /*Multi.createFrom().items("A", "B", "C", "D", "E")
                .subscribe().with(
                        item -> System.out.println("Data------------: " + item),
                        f -> System.out.println("Failure: " + f.getMessage()),
                       () -> System.out.println("-----------Completed----------------")
                );



        Multi.createFrom().items("A", "B", "C", "D", "E")
                .onItem().transform(MultiMutiny::validate)
                .subscribe().with(
                        item -> System.out.println("Data------------: " + item),
                        f -> System.out.println("Failure: " + f.getMessage()),
                        () -> System.out.println("-----------Completed----------------")
                );


        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Multi.createFrom().iterable(list)
                .subscribe().with(
                        item -> System.out.println("Data------------: " + item),
                        f -> System.out.println("Failure: " + f.getMessage()),
                        () -> System.out.println("-----------Completed----------------")
               );



        Multi.createFrom().ticks().every(Duration.ofMillis(1000))
                .subscribe().with(
                        item -> System.out.println("Data------------: " + item),
                        f -> System.out.println("Failure: " + f.getMessage()),
                       () -> System.out.println("-----------Completed----------------")
                );



        Multi<String> failedMulti = Multi.createFrom().failure(new Exception("Have a exeption"));
        failedMulti.subscribe().with(
                item -> System.out.println("Data------------: " + item),
                f -> System.out.println("Failure: " + f.getMessage()),
                () -> System.out.println("-----------Completed----------------")
        );



        Multi<String> multiEmpty = Multi.createFrom().empty();
        multiEmpty.subscribe().with(
                item -> {
                       System.out.println("Data------------: " + item);
                       if (item.isEmpty()) {
                           System.out.println("Data is empty");
                       }
                 },
                f -> System.out.println("Failure: " + f.getMessage()),
                () -> System.out.println("-----------Completed----------------")
        ); */
/*
        Multi<String> multiIncludeNull = Multi.createFrom().items("A", "", "B", null, "D");
        multiIncludeNull.subscribe().with(
                item -> System.out.println("Data------------: " + item),
                f -> System.out.println("Failure: " + f.getMessage()),
                () -> System.out.println("-----------Completed----------------")
        );
*/


        Multi<Integer> multiEmitter = Multi.createFrom().emitter(em -> {
            em.emit(1);
            em.emit(2);
            em.emit(3);
            em.complete();
        });

        multiEmitter.subscribe().with(
                item -> System.out.println("Data------------: " + item),
                f -> System.out.println("Failure: " + f.getMessage()),
                () -> System.out.println("-----------Completed----------------")
        );

    }

    public static String validate(String item) {
        if (item.equals("C")) {
            throw new RuntimeException("C is not accepted");
        }
        return item;
    }

}