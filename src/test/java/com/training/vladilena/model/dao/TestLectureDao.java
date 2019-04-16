package com.training.vladilena.model.dao;

import com.training.vladilena.model.dao.data.ConferenceBuilder;
import com.training.vladilena.model.dao.data.LectureBuilder;
import com.training.vladilena.model.dao.data.SpeakerBuilder;
import com.training.vladilena.model.dao.impl.MySQLLectureDao;
import com.training.vladilena.model.entity.Lecture;
import com.training.vladilena.util.TestConnectionPoolHolder;
import com.training.vladilena.util.TestDataBaseManager;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLectureDao {
    private static LectureDao lectureDao;

    @BeforeClass
    public static void setUpClass() {
        TestDataBaseManager.setUpTestDDL();
        TestDataBaseManager.setUpTestDML();
        try {
            lectureDao = new MySQLLectureDao(new TestConnectionPoolHolder().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createTest() {
        Lecture lecture = LectureBuilder.getBuilder().constructLecture(2L, false,
                ConferenceBuilder.getBuilder().constructConference(1L),
                SpeakerBuilder.getBuilder().constructSpeaker(2L)).build();
        assertTrue(lectureDao.create(lecture));
        assertEquals(lecture, lectureDao.findById(lecture.getId()));
        TestDataBaseManager.setUpTestDML();
    }

    @Test
    public void updateTest() {
        Lecture lecture = LectureBuilder.getBuilder().constructLecture(1L, false,
                ConferenceBuilder.getBuilder().constructConference(1L),
                SpeakerBuilder.getBuilder().constructSpeaker(2L)).build();
        lecture.setTitleEn("update");
        assertTrue(lectureDao.update(lecture));
        assertEquals(lecture, lectureDao.findById(lecture.getId()));
        TestDataBaseManager.setUpTestDML();
    }

    @Test
    public void deleteTest() {
        Lecture lecture = LectureBuilder.getBuilder().constructLecture(1L, false,
                ConferenceBuilder.getBuilder().constructConference(1L),
                SpeakerBuilder.getBuilder().constructSpeaker(2L)).build();
        assertTrue(lectureDao.delete(lecture.getId()));
        assertNull(lectureDao.findById(lecture.getId()));
        TestDataBaseManager.setUpTestDML();
    }

    @Test
    public void findAllTest() {
        List<Lecture> expectedLectures = new ArrayList<>();
        expectedLectures.add(LectureBuilder.getBuilder().constructLecture(1L, false,
                ConferenceBuilder.getBuilder().constructConference(1L),
                SpeakerBuilder.getBuilder().constructSpeaker(2L)).build());
        List<Lecture> actualLectures = lectureDao.findAll();

        assertEquals(expectedLectures, actualLectures);
    }

    @Test
    public void findByIdTest() {
        Lecture expectedLecture = LectureBuilder.getBuilder().constructLecture(1L, false,
                ConferenceBuilder.getBuilder().constructConference(1L),
                SpeakerBuilder.getBuilder().constructSpeaker(2L)).build();
        Lecture actualLecture = lectureDao.findById(expectedLecture.getId());
        assertEquals(expectedLecture, actualLecture);
    }

    @Test
    public void approveTest() {
        Lecture lecture = LectureBuilder.getBuilder().constructLecture(1L, false,
                ConferenceBuilder.getBuilder().constructConference(1L),
                SpeakerBuilder.getBuilder().constructSpeaker(2L)).build();
        assertTrue(lectureDao.approve(lecture.getId()));
        assertTrue(lectureDao.findById(lecture.getId()).isApproved());
        TestDataBaseManager.setUpTestDML();
    }
}


