package com.training.vladilena.model.dao.mapper.impl;

import com.training.vladilena.model.dao.mapper.ObjectMapper;
import com.training.vladilena.model.entity.Conference;
import com.training.vladilena.model.entity.Lecture;
import com.training.vladilena.model.entity.Speaker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code LectureMapper} implements {@link ObjectMapper}
 *
 * @author Vladlena Ushakova
 */
public class LectureMapper implements ObjectMapper<Lecture> {
    private static final Logger LOGGER = LogManager.getLogger(LectureMapper.class);
    private static SpeakerMapper speakerMapper = new SpeakerMapper();
    private static ConferenceMapper conferenceMapper = new ConferenceMapper();

    /**
     * {@inheritDoc}
     */
    @Override
    public Lecture parseFromResultSet(ResultSet rs, boolean islazy) throws SQLException {
        Lecture lecture = null;
        if (islazy) {
            LOGGER.debug("Try to parse from lecture without relations");
            lecture = parseWithoutSpeakerAndConference(rs);
        } else {
            LOGGER.debug("Try to parse from lecture with relations");
            lecture = parseWithSpeakerAndConference(rs);
        }
        return lecture;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Lecture makeUnique(Map<Long, Lecture> lectures, Lecture lecture) {
        LOGGER.info("Try to make lecture unique");
        lectures.putIfAbsent(lecture.getId(), lecture);
        return lectures.get(lecture.getId());
    }

    @Override
    public boolean isEmptyInResultSet(ResultSet rs) throws SQLException {
        LOGGER.debug("Try to find out if lecture is empty in result set");
        return rs.getLong("lecture_id") == 0;
    }

    private Lecture parseWithoutSpeakerAndConference(ResultSet rs) throws SQLException {
        Lecture lecture = new Lecture();
        lecture.setId(rs.getLong("lecture_id"));
        lecture.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
        lecture.setTitle(rs.getString("lecture_title"));
        lecture.setTitleEn(rs.getString("lecture_title_en"));
        lecture.setDescription(rs.getString("lecture_description"));
        lecture.setDescriptionEn(rs.getString("lecture_description_en"));
        lecture.setApproved(rs.getBoolean("approved"));
        return lecture;
    }

    private Lecture parseWithSpeakerAndConference(ResultSet rs) throws SQLException {
        Map<Long, Speaker> speakers = new HashMap<>();
        Map<Long, Conference> conferences = new HashMap<>();
        Lecture lecture = parseWithoutSpeakerAndConference(rs);
        if (!speakerMapper.isEmptyInResultSet(rs)) {
            LOGGER.debug("Try to parse from related table(s)");
            Speaker speaker = speakerMapper.parseFromResultSet(rs, true);
            speaker = speakerMapper.makeUnique(speakers, speaker);
            lecture.setMainSpeaker(speaker);
        } else {
            LOGGER.debug("Related table is empty");
        }
        if (!conferenceMapper.isEmptyInResultSet(rs)) {
            LOGGER.debug("Try to parse from related table(s)");
            Conference conference = conferenceMapper.parseFromResultSet(rs, true);
            conference = conferenceMapper.makeUnique(conferences, conference);
            lecture.setMainConference(conference);
        } else {
            LOGGER.debug("Related table is empty");
        }
        return lecture;
    }
}



