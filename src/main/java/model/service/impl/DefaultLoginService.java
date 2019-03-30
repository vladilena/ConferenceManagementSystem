package model.service.impl;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;
import model.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultLoginService implements LoginService {
    private static final Logger LOGGER = LogManager.getLogger(DefaultLoginService.class);

    private static volatile LoginService loginService;
    private static UserDao userDao;

    private DefaultLoginService() {
        userDao = DaoFactory.getInstance().getUserDao();
    }

    public static LoginService getInstance(){
        LoginService localInstance = loginService;
        if(localInstance == null) {
            synchronized (DefaultLoginService.class) {
                localInstance = loginService;
                if(localInstance == null) {
                    loginService = new DefaultLoginService();
                    LOGGER.debug("Create first DefaultLoginService instance");
                }
            }
        }
        LOGGER.debug("Return DefaultLoginService instance");
        return loginService;
    }


    @Override
    public User login(String login, String password) {
        User user = userDao.findByLogin(login);

        if(user != null && user.getPassword().equals(password))
            return user;

        return null;
    }


}


