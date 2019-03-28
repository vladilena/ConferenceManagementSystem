package model.dao.impl;

import model.dao.SpeakerDao;
import model.dao.mapper.ConferenceMapper;
import model.dao.mapper.LectureMapper;
import model.dao.mapper.SpeakerMapper;
import model.entity.Conference;
import model.entity.Lecture;
import model.entity.Speaker;
import model.util.SQLManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLSpeakerDao implements SpeakerDao {
    private Connection connection;
    private SpeakerMapper speakerMapper;
    private LectureMapper lectureMapper;
    private ConferenceMapper conferenceMapper;

    MySQLSpeakerDao(Connection connection) {
        this.connection = connection;
        speakerMapper = new SpeakerMapper();
        lectureMapper = new LectureMapper();
        conferenceMapper = new ConferenceMapper();
    }


    @Override
    public boolean create(Speaker entity) {
        boolean resultFlag = false;
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("insert.speaker"))) {
            stm.setLong(1, entity.getId());
            stm.setDouble(2, entity.getRating());
            stm.setDouble(3, entity.getBonus());
            if(stm.executeUpdate()>0){
                resultFlag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return resultFlag;
    }

    @Override
    public Speaker findById(long id) {
        Speaker result = null;
        Map<Long, Speaker> speakers = new HashMap<>();
        Map<Long, Lecture> lectures = new HashMap<>();
        Map<Long, Conference> conferences = new HashMap<>();

        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("find.speaker.by.id"))) {
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Speaker speaker = fillRelatedEntities(rs, speakers, lectures ,conferences);
                if (!speaker.equals(result)) {
                    result = speaker;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Speaker> findAll() {
        List<Speaker> resultList = new ArrayList<>();
        Map<Long, Speaker> speakers = new HashMap<>();

        try (Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(SQLManager.getProperty("find.all.speakers"));
            while (rs.next()) {
                Speaker speaker = speakerMapper.parseFromResultSet(rs);
                speaker = speakerMapper.makeUnique(speakers, speaker);
                if (!resultList.contains(speaker)) {
                    resultList.add(speaker);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public boolean update(Speaker entity) {
        boolean resultFlag = false;
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("update.speaker"))) {
            stm.setDouble(1, entity.getRating());
            stm.setDouble(2, entity.getBonus());
            stm.setLong(3, entity.getId());
            if(stm.executeUpdate()>0){
                resultFlag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return resultFlag;
    }

    @Override
    public boolean delete(long id) {
        //On cascade delete from user
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
private Speaker fillRelatedEntities(ResultSet rs,  Map<Long, Speaker> speakers, Map<Long, Lecture> lectures, Map<Long, Conference> conferences) throws SQLException {
    Speaker speaker = speakerMapper.parseFromResultSet(rs);
    Lecture lecture = lectureMapper.parseFromResultSet(rs);
    Conference conference = conferenceMapper.parseFromResultSet(rs);
    speaker = speakerMapper.makeUnique(speakers, speaker);
    lecture = lectureMapper.makeUnique(lectures, lecture);
    conference = conferenceMapper.makeUnique(conferences, conference);
    lecture.setMainConference(conference);
    lecture.setMainSpeaker(speaker);
    speaker.getLectures().add(lecture);

  return speaker;
}

    @Override
    public boolean changeRating(int newRating, long speakerId) {
        boolean resultFlag = false;
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("update.speaker.change.rating"))) {
            stm.setDouble(1, newRating);
            stm.setLong(2, speakerId);
            if(stm.executeUpdate()>0){
                resultFlag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return resultFlag;
    }
//    public static void main(String[] args) {
//        SpeakerDao speakerDao = DaoFactory.getInstance().getSpeakerDao();
//        System.out.println(speakerDao.findAll());
//        System.out.println(speakerDao.findById(2));
//
//    }
}


