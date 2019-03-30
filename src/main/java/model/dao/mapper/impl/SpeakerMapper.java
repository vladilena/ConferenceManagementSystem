package model.dao.mapper.impl;

import model.dao.mapper.ObjectMapper;
import model.entity.Speaker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class SpeakerMapper implements ObjectMapper<Speaker> {


    @Override
    public Speaker parseFromResultSet(ResultSet rs) throws SQLException {
            Speaker result = new Speaker();
            result.setId(rs.getLong("user_id"));
            result.setLogin(rs.getString("login"));
            result.setPassword(rs.getString("password"));
            result.setEmail(rs.getString("email"));
            result.setFirstName(rs.getString("first_name"));
            result.setFirstNameEn(rs.getString("first_name_en"));
            result.setLastName(rs.getString("last_name"));
            result.setLastNameEn(rs.getString("last_name_en"));
            result.setRating(rs.getDouble("rating"));
            result.setBonus(rs.getDouble("bonus"));
            return result;
    }

    @Override
    public Speaker makeUnique(Map<Long, Speaker> speakers, Speaker speaker) {
            speakers.putIfAbsent(speaker.getId(), speaker);
            return speakers.get(speaker.getId());
    }
}


