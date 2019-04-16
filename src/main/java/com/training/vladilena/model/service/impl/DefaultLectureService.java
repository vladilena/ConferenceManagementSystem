package com.training.vladilena.model.service.impl;

import com.training.vladilena.model.dao.ConferenceDao;
import com.training.vladilena.model.dao.DaoFactory;
import com.training.vladilena.model.dao.LectureDao;
import com.training.vladilena.model.entity.Conference;
import com.training.vladilena.model.entity.Lecture;
import com.training.vladilena.model.service.LectureService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * {@inheritDoc}
 */
public class DefaultLectureService implements LectureService {
    private static final Logger LOGGER = LogManager.getLogger(DefaultLectureService.class);

    private static volatile LectureService lectureService;
    private static LectureDao lectureDao;
    private static ConferenceDao conferenceDao;

    private DefaultLectureService() {
        lectureDao = DaoFactory.getInstance().getLectureDao();
        conferenceDao = DaoFactory.getInstance().getConferenceDao();
    }
    /**
     * Always return same {@link DefaultLectureService} instance
     *
     * @return always return same {@link DefaultLectureService} instance
     */
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


