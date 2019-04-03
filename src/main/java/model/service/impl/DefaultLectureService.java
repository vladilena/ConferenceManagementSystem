package model.service.impl;

import model.dao.ConferenceDao;
import model.dao.DaoFactory;
import model.dao.LectureDao;
import model.entity.*;
import model.service.LectureService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultLectureService implements LectureService {
    private static final Logger LOGGER = LogManager.getLogger(DefaultLectureService.class);

    private static volatile LectureService lectureService;
    private static LectureDao lectureDao;
    private static ConferenceDao conferenceDao;

    private DefaultLectureService() {
        lectureDao = DaoFactory.getInstance().getLectureDao();
        conferenceDao = DaoFactory.getInstance().getConferenceDao();
    }

    public static LectureService getInstance() {
        LectureService localInstance = lectureService;
        if (localInstance == null) {
            synchronized (DefaultConferenceService.class) {
                localInstance = lectureService;
                if (localInstance == null) {
                    lectureService = new DefaultLectureService();
                      LOGGER.debug("Create DefaultLectureService instance");
                }
            }
        }
         LOGGER.debug("Return DefaultLectureService instance");
        return lectureService;
    }

    @Override
    public boolean create(Lecture lecture, long conferenceId) {
        Conference conference = conferenceDao.findById(conferenceId);
        lecture.setStartTime(conference.getDateTime());
        lecture.setMainConference(conference);
        return lectureDao.create(lecture);
    }

    @Override
    public boolean approve(long lectureId) {
        return lectureDao.approve(lectureId);
    }

    @Override
    public Lecture getById(long lectureId) {
        return lectureDao.findById(lectureId);
    }

    @Override
    public boolean update(Lecture lecture) {
        return lectureDao.update(lecture);
    }

    @Override
    public boolean delete(long lectureId) {
        return lectureDao.delete(lectureId);
    }
}


