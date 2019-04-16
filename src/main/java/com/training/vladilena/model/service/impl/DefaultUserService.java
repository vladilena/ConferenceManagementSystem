package com.training.vladilena.model.service.impl;

import com.training.vladilena.model.dao.DaoFactory;
import com.training.vladilena.model.dao.UserDao;
import com.training.vladilena.model.entity.Role;
import com.training.vladilena.model.entity.User;
import com.training.vladilena.model.service.SpeakerService;
import com.training.vladilena.model.service.UserService;
import com.training.vladilena.model.transaction.TransactionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Set;
/**
 * {@inheritDoc}
 */
public class DefaultUserService implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(DefaultUserService.class);
    private static volatile UserService userService;
    private static UserDao userDao;
    private static SpeakerService speakerService;

    private DefaultUserService() {
        userDao = DaoFactory.getInstance().getUserDao();
        speakerService = DefaultSpeakerService.getInstance();
    }
    /**
     * Always return same {@link DefaultUserService} instance
     *
     * @return always return same {@link DefaultUserService} instance
     */
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

    public boolean create(User user) throws SQLException {
        boolean resultFlag = false;

        if (user.getRole() == Role.USER) {
            resultFlag = userDao.create(user);

        } else if (user.getRole() == Role.SPEAKER) {
            TransactionManager tm = new TransactionManager();
            tm.begin();
            if (userDao.create(user) && speakerService.create(userDao.findIdByLogin(user.getLogin()))) {
                resultFlag = true;
                tm.commit();
            } else {
                tm.rollback();
            }
            tm.close();
        }
        return resultFlag;
    }

    @Override
    public boolean subscribeOnConference(long userId, long conferenceId) {
        return userDao.subscribeOnConference(userId, conferenceId);
    }
}


