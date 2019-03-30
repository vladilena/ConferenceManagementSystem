package model.dao.mapper.impl;

import model.dao.mapper.ObjectMapper;
import model.entity.Conference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ConferenceMapper implements ObjectMapper<Conference> {


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

    @Override
    public Conference makeUnique(Map<Long, Conference> conferences, Conference conference) {
        conferences.putIfAbsent(conference.getId(), conference);
        return conferences.get(conference.getId());
    }
}



