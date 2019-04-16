package com.training.vladilena.model.dao.mapper.impl;

import com.training.vladilena.model.dao.mapper.ObjectMapper;
import com.training.vladilena.model.entity.Conference;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
/**
 * The {@code ConferenceMapper} implements {@link ObjectMapper}
 *
 * @author Vladlena Ushakova
 */
public class ConferenceMapper implements ObjectMapper<Conference> {
    /**
     * {@inheritDoc}
     */
    @Override
    public Conference parseFromResultSet(ResultSet rs) throws SQLException {
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
    /**
     * {@inheritDoc}
     */
    @Override
    public Conference makeUnique(Map<Long, Conference> conferences, Conference conference) {
        conferences.putIfAbsent(conference.getId(), conference);
        return conferences.get(conference.getId());
    }
}



