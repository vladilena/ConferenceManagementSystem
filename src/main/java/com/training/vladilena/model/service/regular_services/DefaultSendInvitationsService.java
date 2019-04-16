package com.training.vladilena.model.service.regular_services;

import com.training.vladilena.model.entity.Conference;
import com.training.vladilena.model.entity.Lecture;
import com.training.vladilena.model.entity.User;
import com.training.vladilena.model.service.ConferenceService;
import com.training.vladilena.model.service.impl.DefaultConferenceService;
import com.training.vladilena.util.BusinessLogicManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
/**
 * The {@code DefaultSendInvitationsService} service is a class to send letters to subscribed users
 *
 * @author Vladlena Ushakova
 */
public class DefaultSendInvitationsService implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(DefaultSendInvitationsService.class);
    private static ConferenceService conferenceService;
    private Transport transport;

    public DefaultSendInvitationsService() {
        conferenceService = DefaultConferenceService.getInstance();

    }

    @Override
    public void run() {

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("pool", true);

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(BusinessLogicManager.getProperty("username"), BusinessLogicManager.getProperty("password"));
                    }
                });

        transport = null;
        try {
            transport = session.getTransport("smtp");
            transport.connect();

            List<Conference> ongoing = conferenceService.getOngoingInOneWeekOrLess(System.currentTimeMillis());
            ongoing.forEach(conference -> sendInvitations(session, conference, transport));

            transport.close();
        } catch (MessagingException e) {
            LOGGER.error("'Problem with sending: " + e);
        }
        LOGGER.debug("Transport is connected");
    }

    private void sendInvitations(Session session, Conference conference, Transport transport) {
        LOGGER.debug("Transport is connected");

        List<Message> messages = null;
        try {
            messages = buildMessages(session, conference);
            for (Message message : messages) {
                Transport.send(message);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    private List<Message> buildMessages(Session session, Conference conference) throws MessagingException {
        List<Message> messages = new ArrayList<>();
        List<User> subscribedUsers = conference.getRegisteredParticipants();
        for (User user : subscribedUsers) {
            messages.add(createMessage(session, user, conference));
        }
        return messages;
    }

    private Message createMessage(Session session, User user, Conference conference) throws MessagingException {
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(BusinessLogicManager.getProperty("admin.email")));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
        message.setSubject(BusinessLogicManager.getProperty("subject"));

        StringBuilder emailText = new StringBuilder()
                .append(BusinessLogicManager.getProperty("text.hello.en"))
                .append(BusinessLogicManager.getProperty("text.line"))
                .append(conference.getTitleEn())
                .append(BusinessLogicManager.getProperty("text.line"))
                .append(conference.getDescriptionEn())
                .append(BusinessLogicManager.getProperty("text.line"))
                .append(conference.getDateTime())
                .append(BusinessLogicManager.getProperty("text.line"))
                .append(conference.getPlaceEn())
                .append(BusinessLogicManager.getProperty("text.line"))
                .append(BusinessLogicManager.getProperty("text.bye.en"))
                .append(BusinessLogicManager.getProperty("text.line"))
                .append(BusinessLogicManager.getProperty("text.delimeter"))
                .append(BusinessLogicManager.getProperty("text.line"))
                .append(BusinessLogicManager.getProperty("text.hello.ukr"))
                .append(BusinessLogicManager.getProperty("text.line"))
                .append(conference.getTitle())
                .append(BusinessLogicManager.getProperty("text.line"))
                .append(conference.getDescription())
                .append(BusinessLogicManager.getProperty("text.line"))
                .append(conference.getDateTime())
                .append(BusinessLogicManager.getProperty("text.line"))
                .append(conference.getPlace())
                .append(BusinessLogicManager.getProperty("text.line"))
                .append(BusinessLogicManager.getProperty("text.bye.ukr"));

        LOGGER.debug("Starting to put lectures in email");
        List<Lecture> lectures = conference.getConferenceLectures();
        lectures.forEach(lecture -> emailText.append(lecture.getTitle())
                .append(lecture.getTitleEn())
                .append(lecture.getTitleEn())
                .append(lecture.getDescription())
                .append(lecture.getDescriptionEn())
                .append(lecture.getStartTime())
        );

        message.setText(emailText.toString());
        return message;
    }
}


