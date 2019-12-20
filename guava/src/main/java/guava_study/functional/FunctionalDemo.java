package guava_study.functional;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Preconditions;

import javax.annotation.Nullable;
import java.io.IOException;
import java.net.ServerSocket;

public class FunctionalDemo {

    public static void main(String[] args) throws IOException {

        Function<String, Integer> function = new Function<String, Integer>() {

            @Nullable
            @Override
            public Integer apply(@Nullable String input) {
                Preconditions.checkNotNull(input, "the input stream should not bu null.");
                return input.length();
            }
        };

        System.out.println(function.apply("hello"));

        System.out.println(Functions.toStringFunction().apply(new ServerSocket(8888)));
    }


}
