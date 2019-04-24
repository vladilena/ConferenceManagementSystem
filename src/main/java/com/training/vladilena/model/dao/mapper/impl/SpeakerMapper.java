package com.training.vladilena.model.dao.mapper.impl;

import com.training.vladilena.model.dao.mapper.ObjectMapper;
import com.training.vladilena.model.entity.Lecture;
import com.training.vladilena.model.entity.Speaker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code SpeakerMapper} implements {@link ObjectMapper}
 *
 * @author Vladlena Ushakova
 */
public class SpeakerMapper implements ObjectMapper<Speaker> {
    private static final Logger LOGGER = LogManager.getLogger(SpeakerMapper.class);

    private static LectureMapper lectureMapper = new LectureMapper();

    /**
     * {@inheritDoc}
     */
    @Override
    public Speaker parseFromResultSet(ResultSet rs, boolean islazy) throws SQLException {
        Speaker speaker = null;
        if (islazy) {
            LOGGER.debug("Try to parse from speaker without relations");
            speaker = parseWithoutLectures(rs);
        } else {
            LOGGER.debug("Try to parse from speaker with relations");
            speaker = parseWithLectures(rs);
        }
        return speaker;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Speaker makeUnique(Map<Long, Speaker> speakers, Speaker speaker) {
        LOGGER.info("Try to make lecture unique");
        speakers.putIfAbsent(speaker.getId(), speaker);
        return speakers.get(speaker.getId());
    }

    @Override
    public boolean isEmptyInResultSet(ResultSet rs) throws SQLException {
        LOGGER.debug("Try to find out if speaker is empty in result set");
        return rs.getLong("user_id") == 0;
    }

    private Speaker parseWithoutLectures(ResultSet rs) throws SQLException {
        Speaker result = new Speaker();
        result.setRating(rs.getDouble("rating"));
        result.setBonus(rs.getDouble("bonus"));
        result.setId(rs.getLong("user_id"));
        result.setLogin(rs.getString("login"));
        result.setEmail(rs.getString("email"));
        result.setPassword(rs.getString("password"));
        result.setLastName(rs.getString("last_name"));
        result.setLastNameEn(rs.getString("last_name_en"));
        result.setFirstName(rs.getString("first_name"));
        result.setFirstNameEn(rs.getString("first_name_en"));
        return result;
    }

    private Speaker parseWithLectures(ResultSet rs) throws SQLException {
        Map<Long, Speaker> speakers = new HashMap<>();
        Map<Long, Lecture> lectures = new HashMap<>();
        Speaker result = null;
        while (rs.next()) {
            result = parseWithoutLectures(rs);
            parseFromResultLectures(rs, lectures);
            result = makeUnique(speakers, result);
            result.setLectures(new ArrayList<>(lectures.values()));
        }
        return result;
    }

    private void parseFromResultLectures(ResultSet rs, Map<Long, Lecture> lectures) throws SQLException {
        if (!lectureMapper.isEmptyInResultSet(rs)) {
            LOGGER.debug("Try to parse from related table");
            Lecture lecture = lectureMapper.parseFromResultSet(rs, true);
            lectureMapper.makeUnique(lectures, lecture);
        } else {
            LOGGER.debug("Related table is empty");
        }
    }
}
