package congnghetuts.com.quarkus.http;

import congnghetuts.com.quarkus.exception.ErrorValidator;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
public class HttpBadRequestContent {
    int error_code;

    String errorMsg;

    List<ErrorValidator> errors;
}
