package com.training.vladilena.model.dao;

import com.training.vladilena.model.dao.impl.MySQLDaoFactory;

/**
 * The {@code DaoFactory} class is an Abstract class to get instances of DAO
 *
 * @author Vladlena Ushakova
 */
public abstract class DaoFactory {

    private static volatile DaoFactory daoFactory;

    public abstract ConferenceDao getConferenceDao();

    public abstract LectureDao getLectureDao();

    public abstract RoleDao getRoleDao();

    public abstract UserDao getUserDao();

    public abstract SpeakerDao getSpeakerDao();

    /**
     * Always return same {@link MySQLDaoFactory} instance
     *
     * @return always return same {@link MySQLDaoFactory} instance
     */
    public static DaoFactory getInstance() {
        DaoFactory localInstance = daoFactory;
        if (localInstance == null) {
            synchronized (DaoFactory.class) {
                localInstance = daoFactory;
                if (localInstance == null) {
                    daoFactory = new MySQLDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}


