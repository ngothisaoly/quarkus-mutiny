package congnghetuts.com.quarkus.transform;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.time.Duration;


public class MultiTransform {
    public static void main(String[] args) {
        Multi<String> multi = Multi.createFrom().items("Congnghetuts", "welcome", "2021");

        multi.onItem().transform(i -> i.toUpperCase())
                .subscribe().with(
                    item -> System.out.println(" >> " + item),
                    f -> System.out.println("Fail with error: " + f.getMessage())
        );


        Multi<String> multiTransformFail = Multi.createFrom().items("Congnghetuts", "hello", "welcome", "2021")
                                           .onItem().transform(item -> item.toUpperCase())
                                           .onItem().transform(MultiTransform::validate);

        multiTransformFail.subscribe().with(
                item -> System.out.println(" >> " + item),
                f -> System.out.println("Fail with error: " + f.getMessage()),
                () -> System.out.println("---------Complete------------")
        );



        Multi<String> multiTransformToUniAndMerge = Multi.createFrom().items("transformToUniAndMergeA", "transformToUniAndMergeB");
        Multi<String> Umerged = multiTransformToUniAndMerge.onItem()
                .transformToUniAndMerge(name -> MultiTransform.transformToUniAndMerge(name));

        Umerged.subscribe().with(
                item -> System.out.println(" >> " + item),
                f -> System.out.println("Fail with error: " + f.getMessage()),
                () -> System.out.println("---------Complete------------")
        );


        Multi<String> multiTransformToUniAndConcat = Multi.createFrom().items("transformToUniAndConcatenateA", "transformToUniAndConcatenateB");
        Multi<String> Uconcat = multiTransformToUniAndConcat.onItem()
                .transformToUniAndConcatenate(name -> MultiTransform.transformToUniAndConcatenate(name));
        Uconcat.subscribe().with(
                item -> System.out.println(" >> " + item),
                f -> System.out.println("Fail with error: " + f.getMessage()),
                () -> System.out.println("---------Complete------------")
        );


        Multi<String> multiTransformToMultiAndMerged = Multi.createFrom().items("transformToMultiAndMergeA", "transformToMultiAndMergeB");

        Multi<String> mergedM = multiTransformToMultiAndMerged
                .onItem().transformToMultiAndMerge(item -> MultiTransform.transformToMultiAndMerge(item));

        mergedM.subscribe().with(
                item -> System.out.println(" >> " + item),
                f -> System.out.println("Fail with error: " + f.getMessage()),
                () -> System.out.println("---------Complete------------")
        );

        Multi<String> multiToMultiAndConcat = Multi.createFrom().items("transformToMultiAndConcatenateA", "transformToMultiAndConcatenateB");
        Multi<String> concatM = multiToMultiAndConcat
                .onItem().transformToMultiAndConcatenate(item -> MultiTransform.transformToMultiAndConcatenate(item));
        concatM.subscribe().with(
                item -> System.out.println(" >> " + item),
                f -> System.out.println("Fail with error: " + f.getMessage()),
                () -> System.out.println("---------Complete------------")
        );
    }

    public static Multi<String> transformToMultiAndConcatenate(String name) {
        System.out.println("-----------------TRANSFORM FOR ITEM: " + name);
        Multi<String> multi = Multi.createFrom().items("\n------" + name);
        if (name.equals("transformToMultiAndConcatenateA")) {
            multi =  multi.onItem().call(i ->
                    Uni.createFrom().nullItem().onItem().delayIt().by(Duration.ofMillis(5000))
            );

        }
        if (name.equals("transformToMultiAndConcatenateB")) {
            multi =  multi.onItem().call(i ->
                    Uni.createFrom().nullItem().onItem().delayIt().by(Duration.ofMillis(2000))
            );
        }
        return multi;
    }


    public static Multi<String> transformToMultiAndMerge(String name) {
        System.out.println("-----------------TRANSFORM FOR ITEM: " + name);
        Multi<String> multi = Multi.createFrom().items("\n--------" + name);
        if (name.equals("transformToMultiAndMergeA")) {
            multi =  multi.onItem().call(i ->
                    Uni.createFrom().nullItem().onItem().delayIt().by(Duration.ofMillis(5000))
            );
        }
        if (name.equals("transformToMultiAndMergeB")) {
            multi =  multi.onItem().call(i ->
                    Uni.createFrom().nullItem().onItem().delayIt().by(Duration.ofMillis(2000))
            );
        }
        return multi;
    }

    public static Uni<String> transformToUniAndConcatenate(String name) {
        System.out.println("-----------------TRANSFORM FOR ITEM: " + name);
        Uni uni = Uni.createFrom().item("\n----" + name);
        if (name.equals("transformToUniAndConcatenateA"))
            uni = uni.onItem().delayIt().by(Duration.ofMillis(5000));

        if (name.equals("transformToUniAndConcatenateB"))
            uni = uni.onItem().delayIt().by(Duration.ofMillis(2000));
        return uni;
    }

    public static Uni<String> transformToUniAndMerge(String name) {
        System.out.println("-----------------TRANSFORM FOR ITEM: " + name);
        Uni uni = Uni.createFrom().item("\n--" + name);
        if (name.equals("transformToUniAndMergeA"))
            uni = uni.onItem().delayIt().by(Duration.ofMillis(5000));

        if (name.equals("transformToUniAndMergeB"))
            uni = uni.onItem().delayIt().by(Duration.ofMillis(2000));
        return uni;
    }


    public static String validate (String item) {
        if (item.equals("HELLO"))
            throw new RuntimeException("Having a exception with item:  " + item);
        return item;
    }
}
