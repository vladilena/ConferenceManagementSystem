package model.dao.impl;

import model.dao.ConferenceDao;
import model.dao.mapper.impl.ConferenceMapper;
import model.dao.mapper.impl.LectureMapper;
import model.dao.mapper.impl.SpeakerMapper;
import model.dao.mapper.impl.UserMapper;
import model.entity.Conference;
import model.entity.Lecture;
import model.entity.Speaker;
import model.entity.User;
import model.util.SQLManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLConferenceDao implements ConferenceDao {
    private static final Logger LOGGER = LogManager.getLogger(MySQLConferenceDao.class);

    private Connection connection;
    private ConferenceMapper conferenceMapper;
    private LectureMapper lectureMapper;
    private UserMapper userMapper;
    private SpeakerMapper speakerMapper;

    MySQLConferenceDao(Connection connection) {
        LOGGER.debug("MySQLConferenceDao constructor");
        this.connection = connection;
        conferenceMapper = new ConferenceMapper();
        lectureMapper = new LectureMapper();
        userMapper = new UserMapper();
        speakerMapper = new SpeakerMapper();
    }

    @Override
    public boolean create(Conference entity) {
        boolean resultFlag = false;
        LOGGER.debug("Try to insert into conference table");
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("insert.conference"))) {
            setGeneralParamsToPreparedStatement(stm, entity);
            if (stm.executeUpdate()>0){
                resultFlag = true;
            }
            LOGGER.debug("Insert was successful "+entity);
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows:" + e);
        }
    return resultFlag;
    }

    @Override
    public Conference findById(long id) {
        Conference result = null;
        Map<Long, Conference> conferences = new HashMap<>();
        Map<Long, User> users = new HashMap<>();
        Map<Long, Lecture> lectures = new HashMap<>();
        Map<Long, Speaker> speakers = new HashMap<>();
        LOGGER.debug("Try to find conference by id");
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("find.conference.by.id"))) {
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Conference conference = fillRelatedEntities(rs, conferences, users, lectures, speakers);

                if (!conference.equals(result)) {
                    result = conference;
                }
            }
            LOGGER.debug("Select was successful "+ result);
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: "+e);
        }
        return result;
    }

    @Override
    public List<Conference> findAll() {
        List<Conference> resultList = new ArrayList<>();
        Map<Long, Conference> conferences = new HashMap<>();
        Map<Long, User> users = new HashMap<>();
        Map<Long, Lecture> lectures = new HashMap<>();
        Map<Long, Speaker> speakers = new HashMap<>();
        LOGGER.debug("Try to find all conferences");
        try (Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(SQLManager.getProperty("find.all.conferences"));
            while (rs.next()) {
                Conference conference = fillRelatedEntities(rs, conferences, users, lectures, speakers);
                if (!resultList.contains(conference)) {
                    resultList.add(conference);
                }
            }
            LOGGER.debug("Found all conferences successful "+ resultList);
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: "+e);
        }
        return resultList;
    }


    @Override
    public boolean update(Conference entity) {
        boolean resultFlag = false;
        LOGGER.debug("Try to update conference");
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("update.conference"))) {
            setGeneralParamsToPreparedStatement(stm, entity);
            stm.setLong(10, entity.getId());
            if(stm.executeUpdate()>0){
                resultFlag = true;
            }
            LOGGER.debug("Successful update");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: "+e);
        }
    return  resultFlag;}

    @Override
    public boolean delete(long id) {
        boolean resultFlag = false;
        LOGGER.debug("Try to delete from conference");
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("delete.conference"))) {
            stm.setLong(1, id);
            if(stm.executeUpdate()>0){
                resultFlag = true;
            }
            LOGGER.debug("Delete was successful");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: "+e);
        }
    return resultFlag;
    }

    @Override
    public void close() {
        LOGGER.debug("Try to close connection");
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: "+e);
            throw new RuntimeException(e);
        }
    }

    private void setGeneralParamsToPreparedStatement(PreparedStatement stm, Conference entity) throws SQLException {
        LOGGER.debug("Try to set params to prepare statements");
        stm.setString(1, entity.getTitle());
        stm.setString(2, entity.getTitleEn());
        stm.setString(3, entity.getDescription());
        stm.setString(4, entity.getDescriptionEn());
        stm.setTimestamp(5, Timestamp.valueOf(entity.getDateTime()));
        stm.setString(6, entity.getPlace());
        stm.setString(7, entity.getPlaceEn());
        stm.setInt(8, entity.getLecturesCapacity());
        stm.setInt(9, entity.getPlaceCapacity());
    }

    private Conference fillRelatedEntities(ResultSet rs, Map<Long, Conference> conferences, Map<Long, User> users, Map<Long, Lecture> lectures, Map<Long, Speaker> speakers) throws SQLException {
        Conference conference = conferenceMapper.parseFromResultSet(rs);
        conference = conferenceMapper.makeUnique(conferences, conference);
        User user;
        Lecture lecture;
        Speaker speaker;

        if (rs.getLong("user_id")!=0){
            LOGGER.debug("Try to parse related users, if not null");
            user =
                    userMapper.parseFromResultSet(rs);
            user = userMapper.makeUnique(users, user);
            if (!conference.getRegisteredParticipants().contains(user)) {
                conference.getRegisteredParticipants().add(user);
            }
        }
        if (rs.getLong("lecture_id")!=0){
            LOGGER.debug("Try to parse related lectures, if not null");

            lecture = lectureMapper.parseFromResultSet(rs);
            lecture = lectureMapper.makeUnique(lectures, lecture);
            if (!conference.getConferenceLectures().contains(lecture)) {
                conference.getConferenceLectures().add(lecture);
                lecture.setMainConference(conference);
            }
        if (rs.getLong("speaker_id")!=0){
            LOGGER.debug("Try to parse related speaker, if not null");

            speaker = speakerMapper.parseFromResultSet(rs);
            speaker = speakerMapper.makeUnique(speakers, speaker);
            lecture.setMainSpeaker(speaker);
        }
        }
        return conference;
    }

//    public static void main(String[] args) {
//        ConferenceDao conferenceDao = DaoFactory.getInstance().getConferenceDao();
//        System.out.println(conferenceDao.findAll());
//        //System.out.println(conferenceDao.findById(2));
//    }
}


