package congnghetuts.com.quarkus.firstProgram;

import io.smallrye.mutiny.Uni;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class MutinyFirstProgram {
    public static void main(String[] args) {
       Uni.createFrom().item("Cong nghe tuts xin chao")
                .onItem().transform(item -> item + " mutiny")
                .onItem().transform(String::toUpperCase)
                .subscribe().with(
                item -> System.out.println(">> " + item));



        Uni<String> uni11 = Uni.createFrom().item("Cong nghe tuts xin chao");
        Uni<String> uni12 = uni11.onItem().transform(item -> item + " mutiny");
        Uni<String> uni13 = uni12.onItem().transform(String::toUpperCase);
        uni13.subscribe().with(
                item -> System.out.println(">> " + item));


        Uni<String> uni = Uni.createFrom().item("Cong nghe tuts xin chao");
        uni.onItem().transform(item -> item + " mutiny");
        uni.onItem().transform(String::toUpperCase);
                uni.subscribe().with(
                item -> System.out.println(">> " + item));

        Uni<String> uniFailed = Uni.createFrom().failure(new Exception("Have a exception"));
        uniFailed.subscribe().with(item -> System.out.println(item),
                f -> System.out.println("Fail with error: " + f)
                );

        Uni<Void> uniNull = Uni.createFrom().nullItem();
        uniNull.subscribe().with(item -> System.out.println("Data: " + item),
                f -> System.out.println("Fail with error: " + f)
        );


        Uni<String> uniEmitter = Uni.createFrom().emitter(e->
        {
            String result = "This is result";
            e.complete(result);
        });

        uniEmitter.subscribe().with(item -> System.out.println("Data: " + item),
                f -> System.out.println("Fail with error: " + f)
        );



        System.out.println("\n------------Creating Unis from a CompletionStage: ");
        CompletionStage cf =
                CompletableFuture.runAsync(() ->  performTask("first stage"));

        Uni uniCompletionStage = Uni.createFrom().completionStage(cf);
        CompletableFuture tt = uniCompletionStage.subscribe().asCompletionStage();
        tt.join();
        System.out.println("\n------------After creating Unis from a CompletionStage: ");
    }

    private static String performTask(String stage) {
        System.out.println("stage: " + stage +  ", thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return stage + " test";
    }
}
