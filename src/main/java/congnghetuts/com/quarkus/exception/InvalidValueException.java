package congnghetuts.com.quarkus.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class InvalidValueException extends RuntimeException{
    private List<ErrorValidator> errors = new ArrayList<>();

    public InvalidValueException withError(ErrorValidator error) {
        this.errors.add(error);
        return this;
    }

    public void addError(ErrorValidator error) {
        this.errors.add(error);
    }
}
