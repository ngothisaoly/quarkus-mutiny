package congnghetuts.com.quarkus.firstProgram;

import io.smallrye.mutiny.Uni;

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


    }
}
