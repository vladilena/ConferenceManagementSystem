package model.util;

import model.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class AccessManager {
    private static final Map<String, List<String>> permittedCommandMap = new HashMap<>();
    private final static Logger LOGGER = LogManager.getLogger(AccessManager.class);


    static {
        init();
    }

    private static void init() {
        List<String> moderatorCommand = new ArrayList<>();
        moderatorCommand.add("main");
        moderatorCommand.add("change_language");
        moderatorCommand.add("redirect_conference");
        moderatorCommand.add("redirect_speakers");
        moderatorCommand.add("redirect_create_conference");
        moderatorCommand.add("redirect_offer_lecture");
        moderatorCommand.add("redirect_change_conference");
        moderatorCommand.add("redirect_change_lecture");
        moderatorCommand.add("logout");
        moderatorCommand.add("offer_lecture");
        moderatorCommand.add("create_conference");
        moderatorCommand.add("change_rating");
        moderatorCommand.add("participate");
        moderatorCommand.add("change_conference");
        moderatorCommand.add("send_invitations");
        moderatorCommand.add("approve");
        moderatorCommand.add("change_lecture");

        List<String> speakerCommand = new ArrayList<>();
        speakerCommand.add("main");
        speakerCommand.add("change_language");
        speakerCommand.add("logout");
        speakerCommand.add("redirect_profile");
        speakerCommand.add("redirect_conference");
        speakerCommand.add("redirect_offer_lecture");
        speakerCommand.add("offer_lecture");
        speakerCommand.add("participate");


        List<String> userCommand = new ArrayList<>();
        userCommand.add("main");
        userCommand.add("change_language");
        userCommand.add("logout");
        userCommand.add("redirect_conference");
        userCommand.add("participate");


        List<String> unregisteredCommand = new ArrayList<>();
        unregisteredCommand.add("main");
        unregisteredCommand.add("change_language");
        unregisteredCommand.add("redirect_registration");
        unregisteredCommand.add("redirect_login");
        unregisteredCommand.add("login");
        unregisteredCommand.add("register");


        permittedCommandMap.put("moderator", moderatorCommand);
        permittedCommandMap.put("speaker", speakerCommand);
        permittedCommandMap.put("user", userCommand);
        permittedCommandMap.put("unregistered", unregisteredCommand);

    }

    public boolean isSecuredPage(String urlPattern) {
        for (Role role : Role.values()) {
            if (urlPattern.contains(role.toString().toLowerCase())) {
                LOGGER.debug("Page is secured");
                return true;
            }
        }
        LOGGER.debug("Page isn't secured");
        return false;
    }

    public boolean checkPermission(String urlPattern, String role) {
        if (urlPattern.contains(role)) {
            String command = urlPattern.replaceFirst(".*/", "");
            return permittedCommandMap.get(role.toLowerCase()).contains(command);
        }
        return false;
    }


}


