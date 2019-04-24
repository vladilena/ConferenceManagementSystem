package com.training.vladilena.model.dao;

import com.training.vladilena.model.dao.data.ConferenceBuilder;
import com.training.vladilena.model.dao.data.LectureBuilder;
import com.training.vladilena.model.dao.data.SpeakerBuilder;
import com.training.vladilena.model.dao.impl.MySQLLectureDao;
import com.training.vladilena.model.entity.Lecture;
import com.training.vladilena.util.TestConnectionPoolHolder;
import com.training.vladilena.util.TestDataBaseManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestLectureDao {
    private static LectureDao lectureDao;

    @BeforeAll
    static void setUpClass() {
        TestDataBaseManager.setUpTestDDL();
        TestDataBaseManager.setUpTestDML();
        try {
            lectureDao = new MySQLLectureDao(new TestConnectionPoolHolder().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createTest() {
        Lecture lecture = LectureBuilder.getBuilder().constructLecture(2L, false,
                ConferenceBuilder.getBuilder().constructConference(1L),
                SpeakerBuilder.getBuilder().constructSpeaker(2L)).build();
        assertTrue(lectureDao.create(lecture));
        assertEquals(lecture, lectureDao.findById(lecture.getId()));
        TestDataBaseManager.setUpTestDML();
    }

    @Test
    void updateTest() {
        Lecture lecture = LectureBuilder.getBuilder().constructLecture(1L, false,
                ConferenceBuilder.getBuilder().constructConference(1L),
                SpeakerBuilder.getBuilder().constructSpeaker(2L)).build();
        lecture.setTitleEn("update");
        assertTrue(lectureDao.update(lecture));
        assertEquals(lecture, lectureDao.findById(lecture.getId()));
        TestDataBaseManager.setUpTestDML();
    }

    @Test
    void deleteTest() {
        Lecture lecture = LectureBuilder.getBuilder().constructLecture(1L, false,
                ConferenceBuilder.getBuilder().constructConference(1L),
                SpeakerBuilder.getBuilder().constructSpeaker(2L)).build();
        assertTrue(lectureDao.delete(lecture.getId()));
        assertNull(lectureDao.findById(lecture.getId()));
        TestDataBaseManager.setUpTestDML();
    }

    @Test
    void findAllTest() {
        List<Lecture> expectedLectures = new ArrayList<>();
        expectedLectures.add(LectureBuilder.getBuilder().constructLecture(1L, false,
                ConferenceBuilder.getBuilder().constructConference(1L),
                SpeakerBuilder.getBuilder().constructSpeaker(2L)).build());
        List<Lecture> actualLectures = lectureDao.findAll();

        assertEquals(expectedLectures, actualLectures);
    }

    @Test
    void findByIdTest() {
        Lecture expectedLecture = LectureBuilder.getBuilder().constructLecture(1L, false,
                ConferenceBuilder.getBuilder().constructConference(1L),
                SpeakerBuilder.getBuilder().constructSpeaker(2L)).build();
        Lecture actualLecture = lectureDao.findById(expectedLecture.getId());
        assertEquals(expectedLecture, actualLecture);
    }

    @Test
    void approveTest() {
        Lecture lecture = LectureBuilder.getBuilder().constructLecture(1L, false,
                ConferenceBuilder.getBuilder().constructConference(1L),
                SpeakerBuilder.getBuilder().constructSpeaker(2L)).build();
        assertTrue(lectureDao.approve(lecture.getId()));
        assertTrue(lectureDao.findById(lecture.getId()).isApproved());
        TestDataBaseManager.setUpTestDML();
    }
}


