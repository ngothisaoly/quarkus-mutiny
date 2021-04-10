package congnghetuts.com.quarkus.controller;

import congnghetuts.com.quarkus.exception.ErrorValidator;
import congnghetuts.com.quarkus.http.HttpBadRequestContent;
import congnghetuts.com.quarkus.http.HttpResultContent;

import javax.ws.rs.core.Response;
import java.util.List;

public class HttpResponseController {
    protected Response badRequest(List<ErrorValidator> errorValidators) {
        HttpBadRequestContent httpBadRequestContent = new HttpBadRequestContent();
        httpBadRequestContent.setError_code(400);
        httpBadRequestContent.setErrorMsg("Bad Request");
        httpBadRequestContent.setErrors(errorValidators);
        return Response.status(Response.Status.BAD_REQUEST).entity(httpBadRequestContent).build();
    }


    protected Response serverError(List<ErrorValidator> errorValidators) {
        HttpBadRequestContent httpBadRequestContent = new HttpBadRequestContent();
        httpBadRequestContent.setError_code(500);
        httpBadRequestContent.setErrorMsg("Server Error");
        httpBadRequestContent.setErrors(errorValidators);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(httpBadRequestContent).build();
    }

    protected <T> Response accepted(String message, T data) {
        HttpResultContent httpResultContent = new HttpResultContent();
        httpResultContent.setStatus(202);
        httpResultContent.setMessage(message);
        httpResultContent.setData(data);
        return Response.status(Response.Status.ACCEPTED).entity(httpResultContent).build();
    }
}
