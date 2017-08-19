package in.mywork.messenger.resources;

import in.mywork.messenger.beans.MessageFilterBean;
import in.mywork.messenger.model.Message;
import in.mywork.messenger.service.MessageService;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/messages")
public class MessageResource {

	MessageService messageService = MessageService.getMessageService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessage(@BeanParam MessageFilterBean filterBean) {
		System.out.println("offset: " + filterBean.getOffset() + " limit: "
				+ filterBean.getLimit());
		return messageService.getAllMessages();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postMessage(Message m) {
		System.out.println("post message  " + m.toString());
		Message message = messageService.addMessage(m);
		return Response.status(Response.Status.OK).entity(message).build();

	}

	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message putMessage(Message m,
			@PathParam("messageId") String messageId) {
		System.out.println("post message  " + m.toString());
		m.setId(Long.parseLong(messageId));
		return messageService.updateMessage(m);

	}

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String getTest() {
		return "Test";
	}

	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessageById(@PathParam("messageId") String messageId) {
		System.out.println("message id  " + messageId);
         
		return messageService.getMessage(Long.parseLong(messageId));

	}

	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		System.out.println("CommentResource message id  ");
		return new CommentResource();

	}
}
