package congnghetuts.com.quarkus.transform;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

public class UniTransform {
    public static void main(String[] args) {
        Uni<String> uni = Uni.createFrom().item("Congnghetuts");
        uni
                .onItem().transform(item -> item.toUpperCase())
                .onItem().transform(item -> item + ", WELCOME!")
                .subscribe().with(
                    item -> System.out.println("Data: " + item),
                    f -> System.out.println("Fail with error: " + f.getMessage())
                );


        Uni<String> uniTransformFail = Uni.createFrom().item("hello")
                .onItem().transform(i -> i.toUpperCase())
                .onItem().transform(UniTransform::validate);

        uniTransformFail.subscribe().with(
                        item -> System.out.println("Data: " + item),
                        f -> System.out.println("Fail with error: " + f.getMessage())
                );


        Uni<String> uniTransformToUni = Uni.createFrom().item("Congnghetuts");

        uniTransformToUni
                .onItem().transformToUni(name -> UniTransform.remoteRestAPI(name))
                .subscribe().with(
                item -> System.out.println("Data: " + item),
                f -> System.out.println("Fail with error: " + f.getMessage())
        );


        Uni<String> uniTransformToMulti = Uni.createFrom().item("congnghetuts.com");

        Multi<String> multi = uniTransformToMulti.onItem()
                .transformToMulti(item -> Multi.createFrom().items(item + " hello 1", item + " hello 2", item + " hello 3"));

        multi.subscribe().with(item -> System.out.println("Data: " + item),
                f -> System.out.println("Fail with error: " + f.getMessage()),
                () -> System.out.println("--------------Complete--------------")
                );
    }

    public static Uni<String> remoteRestAPI(String name) {
        return Uni.createFrom().item("Welcome! " + name);
    }

    public static String validate (String item) {
        if (item.equals("HELLO"))
            throw new RuntimeException("Having a exception");
        return item;

    }
}
