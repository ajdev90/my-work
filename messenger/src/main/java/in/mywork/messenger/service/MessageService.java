package in.mywork.messenger.service;

import in.mywork.messenger.exception.DataNotFoundException;
import in.mywork.messenger.model.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageService {

	private List<Message> messageList;

	private static MessageService messageService;

	public static MessageService getMessageService() {
		if (messageService == null) {
			messageService = new MessageService();
		}
		return messageService;
	}

	private MessageService() {

		System.out.println("MessageService constructor called ");

		messageList = new ArrayList<Message>();
		Message m1 = new Message(10, "message 1", new Date(), "Alpha");
		Message m2 = new Message(11, "message 2", new Date(), "Beta");
		Message m3 = new Message(12, "message 3", new Date(), "Gamma");
		Message m4 = new Message(13, "message 4", new Date(), "Delta");
		Message m5 = new Message(14, "message 5", new Date(), "Theta");

		messageList.add(m1);
		messageList.add(m2);
		messageList.add(m3);
		messageList.add(m4);
		messageList.add(m5);

	}

	public List<Message> getAllMessages() {
		System.out.println("messageList length " + messageList.size());
		return messageList;
	}

	public Message getMessage(long id) {
		List<Message> messages = getAllMessages();
		Message message = null;
		for (Message m : messages) {
			if (m.getId() == id) {
				message = m;
				break;
			}
		}
		if (message == null) {
			throw new DataNotFoundException("requested resource not found");
		}
		return message;

	}

	public Message addMessage(Message m) {
		System.out.println("messageList length " + messageList.size());
		messageList.add(m);
		System.out.println("messageList length " + messageList.size());
		return m;
	}

	public Message updateMessage(Message m) {

		int index = -1;
		List<Message> messages = getAllMessages();

		for (int i = 0; i < messages.size(); i++) {
			if (m.getId() == messages.get(i).getId()) {
				index = i;
			}
		}
		if (index != -1) {
			messages.add(index, m);
			return m;
		} else {
			return null;
		}

	}

	public void deleteMessage(Message m) {
		int index = -1;
		List<Message> messages = getAllMessages();
		for (int i = 0; i < messages.size(); i++) {
			if (m.getId() == messages.get(i).getId()) {
				index = i;
			}
		}
		if (index != -1) {
			messages.remove(index);
		}

	}

}
