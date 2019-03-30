package model.dao.mapper.impl;

import model.dao.mapper.ObjectMapper;
import model.entity.Lecture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class LectureMapper implements ObjectMapper<Lecture> {


    @Override
    public Lecture parseFromResultSet(ResultSet rs) throws SQLException {
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

    @Override
    public Lecture makeUnique(Map<Long, Lecture> lectures, Lecture lecture) {
        lectures.putIfAbsent(lecture.getId(), lecture);


        return lectures.get(lecture.getId());
    }
}


