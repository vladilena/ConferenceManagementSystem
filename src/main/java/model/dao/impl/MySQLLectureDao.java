package model.dao.impl;

import model.dao.LectureDao;
import model.dao.mapper.impl.ConferenceMapper;
import model.dao.mapper.impl.LectureMapper;
import model.dao.mapper.impl.SpeakerMapper;
import model.entity.Conference;
import model.entity.Lecture;
import model.entity.Speaker;
import model.util.SQLManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLLectureDao implements LectureDao {
    private static final Logger LOGGER = LogManager.getLogger(MySQLLectureDao.class);

    private Connection connection;
    private LectureMapper lectureMapper;
    private SpeakerMapper speakerMapper;
    private ConferenceMapper conferenceMapper;

    MySQLLectureDao(Connection connection) {
        LOGGER.debug("MySQLLectureDao constructor");
        this.connection = connection;
        lectureMapper = new LectureMapper();
        speakerMapper = new SpeakerMapper();
        conferenceMapper = new ConferenceMapper();
    }

    @Override
    public boolean create(Lecture entity) {
        LOGGER.debug("Try to insert into lecture table");
        boolean resultFlag = false;
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("insert.lecture"))) {
            setGeneralParamsToPreparedStatement(stm, entity);
            if (stm.executeUpdate() > 0) {
                resultFlag = true;
            }
            LOGGER.debug("Insert was successful " + entity);
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows:" + e);
        }
        return resultFlag;
    }

    @Override
    public Lecture findById(long id) {
        Lecture result = null;
        Map<Long, Lecture> lectures = new HashMap<>();
        Map<Long, Speaker> speakers = new HashMap<>();
        Map<Long, Conference> conferences = new HashMap<>();
        LOGGER.debug("Try to find lecture by id");
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("find.lecture.by.id"))) {
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lecture lecture = fillRelatedEntities(rs, lectures, speakers, conferences);
                if (!lecture.equals(result)) {
                    result = lecture;
                }
            }
            LOGGER.debug("Select was successful " + result);
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return result;
    }

    @Override
    public List<Lecture> findAll() {
        List<Lecture> resultList = new ArrayList<>();
        Map<Long, Lecture> lectures = new HashMap<>();
        Map<Long, Speaker> speakers = new HashMap<>();
        Map<Long, Conference> conferences = new HashMap<>();
        LOGGER.debug("Try to find all lectures");
        try (Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(SQLManager.getProperty("find.all.lectures"));
            while (rs.next()) {
                Lecture lecture = fillRelatedEntities(rs, lectures, speakers, conferences);
                if (!resultList.contains(lecture)) {
                    resultList.add(lecture);
                }
            }
            LOGGER.debug("Found all lectures successful " + resultList);
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return resultList;
    }

    @Override
    public boolean update(Lecture entity) {
        boolean resultFlag = false;
        LOGGER.debug("Try to update lecture");
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("update.lecture"))) {
            setGeneralParamsToPreparedStatement(stm, entity);
            stm.setLong(8, entity.getMainSpeaker().getId());
            stm.setLong(9, entity.getId());
            if (stm.executeUpdate() > 0) {
                resultFlag = true;
            }
            LOGGER.debug("Successful update");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return resultFlag;
    }

    @Override
    public boolean delete(long id) {
        boolean resultFlag = false;
        LOGGER.debug("Try to delete from lecture");
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("delete.lecture"))) {
            stm.setLong(1, id);
            if (stm.executeUpdate() > 0) {
                resultFlag = true;
            }
            LOGGER.debug("Delete was successful");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return resultFlag;
    }

    @Override
    public void close() {
        LOGGER.debug("Try to close connection");
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Threw a RuntimeException, full stack trace follows: " + e);
            throw new RuntimeException(e);
        }
    }

    private void setGeneralParamsToPreparedStatement(PreparedStatement stm, Lecture entity) throws SQLException {
        LOGGER.debug("Try to set params to prepare statements");
        stm.setTimestamp(1, Timestamp.valueOf(entity.getStartTime()));
        stm.setString(2, entity.getTitle());
        stm.setString(3, entity.getTitleEn());
        stm.setString(4, entity.getDescription());
        stm.setString(5, entity.getDescriptionEn());
        stm.setBoolean(6, entity.isApproved());
        stm.setLong(7, entity.getMainConference().getId());
    }

    private Lecture fillRelatedEntities(ResultSet rs, Map<Long, Lecture> lectures, Map<Long, Speaker> speakers, Map<Long, Conference> conferences) throws SQLException {
        Lecture lecture = lectureMapper.parseFromResultSet(rs);
        Speaker speaker = speakerMapper.parseFromResultSet(rs);
        Conference conference = conferenceMapper.parseFromResultSet(rs);
        speaker = speakerMapper.makeUnique(speakers, speaker);
        lecture = lectureMapper.makeUnique(lectures, lecture);
        conference = conferenceMapper.makeUnique(conferences, conference);
        lecture.setMainSpeaker(speaker);
        lecture.setMainConference(conference);
        conference.getConferenceLectures().add(lecture);
        speaker.getConferences().add(conference);
        return lecture;
    }

    @Override
    public boolean approve(long lectureId) {
        boolean resultFlag = false;
        LOGGER.debug("Try to approve lecture");
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("approve.lecture"))) {
            stm.setLong(1, lectureId);
            if (stm.executeUpdate() > 0) {
                resultFlag = true;
            }
            LOGGER.debug("Successful update");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return resultFlag;
    }

//    public static void main(String[] args) {
//        LectureDao lectureDao = DaoFactory.getInstance().getLectureDao();
//        System.out.println(lectureDao.findAll());
//    }
}


