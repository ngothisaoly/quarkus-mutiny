package congnghetuts.com.quarkus.http;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
public class HttpResultContent {
    private int status;
    private String message;
    private Object data;
}