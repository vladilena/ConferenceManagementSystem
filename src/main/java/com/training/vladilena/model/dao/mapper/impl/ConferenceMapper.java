package com.training.vladilena.model.dao.mapper.impl;

import com.training.vladilena.model.dao.mapper.ObjectMapper;
import com.training.vladilena.model.entity.Conference;
import com.training.vladilena.model.entity.Lecture;
import com.training.vladilena.model.entity.Speaker;
import com.training.vladilena.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code ConferenceMapper} implements {@link ObjectMapper}
 *
 * @author Vladlena Ushakova
 */
public class ConferenceMapper implements ObjectMapper<Conference> {
    private static final Logger LOGGER = LogManager.getLogger(ConferenceMapper.class);
    private static LectureMapper lectureMapper = new LectureMapper();
    private static UserMapper userMapper = new UserMapper();
    private static SpeakerMapper speakerMapper = new SpeakerMapper();

    /**
     * {@inheritDoc}
     */
    @Override
    public Conference parseFromResultSet(ResultSet rs, boolean isLazy) throws SQLException {
        Conference conference = null;
        if (isLazy) {
            LOGGER.debug("Try to parse from conference without relations");
            conference = parseConferenceWithoutLectures(rs);
        } else {
            LOGGER.debug("Try to parse from conference with relations");
            conference = parseConferenceWithLectures(rs);
        }
        return conference;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Conference makeUnique(Map<Long, Conference> conferences, Conference conference) {
        LOGGER.info("Try to make conference unique");
        conferences.putIfAbsent(conference.getId(), conference);
        return conferences.get(conference.getId());
    }

    @Override
    public boolean isEmptyInResultSet(ResultSet rs) throws SQLException {
        LOGGER.debug("Try to find out if conference is empty in result set");
        return rs.getLong("conference_id") == 0;
    }

    private Conference parseConferenceWithoutLectures(ResultSet rs) throws SQLException {
        Conference result = new Conference();
        result.setId(rs.getLong("conference_id"));
        result.setTitle(rs.getString("title"));
        result.setTitleEn(rs.getString("title_en"));
        result.setDescription(rs.getString("description"));
        result.setDescriptionEn(rs.getString("description_en"));
        result.setDateTime(rs.getTimestamp("date_time").toLocalDateTime());
        result.setPlace(rs.getString("place"));
        result.setPlaceEn(rs.getString("place_en"));
        result.setLecturesCapacity(rs.getInt("lectures_capacity"));
        result.setPlaceCapacity(rs.getInt("place_capacity"));
        return result;
    }

    private Conference parseConferenceWithLectures(ResultSet rs) throws SQLException {
        Map<Long, Conference> conferences = new HashMap<>();
        Map<Long, Lecture> lectures = new HashMap<>();
        Map<Long, User> users = new HashMap<>();
        Conference result = null;
        while (rs.next()) {
            result = parseConferenceWithoutLectures(rs);
            parseLectures(rs, lectures);
            parseUsers(rs, users);
            result = makeUnique(conferences, result);
            result.setConferenceLectures(new ArrayList<>(lectures.values()));
            result.setRegisteredParticipants(new ArrayList<>(users.values()));
        }
        return result;
    }

    private void parseLectures(ResultSet rs, Map<Long, Lecture> lectures) throws SQLException {
        if (!lectureMapper.isEmptyInResultSet(rs)) {
            LOGGER.debug("Try to parse from related table(s)");
            Lecture lecture = lectureMapper.parseFromResultSet(rs, true);
            if (!speakerMapper.isEmptyInResultSet(rs)) {
                Speaker speaker = speakerMapper.parseFromResultSet(rs, true);
                lecture.setMainSpeaker(speaker);
            }
            lectureMapper.makeUnique(lectures, lecture);
        } else {
            LOGGER.debug("Related table is empty");
        }
    }

    public List<Conference> parseFromResultSetSubscribed(ResultSet rs) throws SQLException {
        List<Conference> resultList = new ArrayList<>();
        Map<Long, Conference> conferences = new HashMap<>();
        Map<Long, User> users = new HashMap<>();
        Conference result = null;
        while (rs.next()) {
            result = parseConferenceWithoutLectures(rs);
            User user = parseUsers(rs, users);
            result = makeUnique(conferences, result);
            result.getRegisteredParticipants().add(user);
            if (!resultList.contains(result)) {
                resultList.add(result);
            }
        }
        return resultList;
    }

    private User parseUsers(ResultSet rs, Map<Long, User> users) throws SQLException {
        User user = null;
        if (!userMapper.isEmptyInResultSet(rs)) {
            LOGGER.debug("Try to parse from related table(s)");
            user = userMapper.parseFromResultSet(rs, true);
            user = userMapper.makeUnique(users, user);
        } else {
            LOGGER.debug("Related table is empty");
        }
        return user;
    }
}
