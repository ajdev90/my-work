package in.mywork.messenger.exception;

import in.mywork.messenger.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider	
public class DataNotFoundExceptionMapper implements
		ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exception) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setDocumentation("http://www.google.com");
		errorMessage.setErrorCode(404);
		errorMessage.setErrorMessage(exception.getMessage());
		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
	}

}
