package model.service.impl;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.Role;
import model.entity.User;
import model.service.SpeakerService;
import model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class DefaultUserService implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(DefaultUserService.class);
    private static volatile UserService userService;
    private static UserDao userDao;
    private static SpeakerService speakerService;


    private DefaultUserService() {
        userDao = DaoFactory.getInstance().getUserDao();
        speakerService = DefaultSpeakerService.getInstance();
    }

    public static UserService getInstance() {
        UserService localInstance = userService;
        if (localInstance == null) {
            synchronized (DefaultLoginService.class) {
                localInstance = userService;
                if (localInstance == null) {
                    userService = new DefaultUserService();
                      LOGGER.debug("Create first DefaultUserService instance");
                }
            }
        }
        LOGGER.debug("Return DefaultUserService instance");
        return userService;
    }

    public Set<String> getAllUsersLogins() {
        return userDao.getAllLogins();
    }

    public boolean create(User user) {
        boolean resultFlag = false;

        if(user.getRole() == Role.USER){
            resultFlag = userDao.create(user);
        } else if (user.getRole() == Role.SPEAKER){
            resultFlag = userDao.create(user) && speakerService.create(userDao.findIdByLogin(user.getLogin()));
        }
    return resultFlag;
    }

    @Override
    public boolean subscribeOnConference(long userId, long conferenceId) {
        return userDao.subscribeOnConference(userId, conferenceId);
    }
}


