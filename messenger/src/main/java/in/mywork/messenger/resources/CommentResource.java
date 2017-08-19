package in.mywork.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class CommentResource {

	@GET
	public String test() {
		return "test...";
	}

	@GET
	@Path("/{commentId}")
	public String test1(@PathParam("commentId") String commentId,
			@PathParam("messageId") String messageId) {
		System.out.println("commentId  " + commentId);
		System.out.println("messageId  " + messageId);
		return "test1... ";
	}

}
