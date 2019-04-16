package com.training.vladilena.model.dao;

import com.training.vladilena.model.dao.data.SpeakerBuilder;
import com.training.vladilena.model.dao.impl.MySQLSpeakerDao;
import com.training.vladilena.model.entity.Speaker;
import com.training.vladilena.util.TestConnectionPoolHolder;
import com.training.vladilena.util.TestDataBaseManager;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestSpeakerDao {
    private static SpeakerDao speakerDao;

    @BeforeClass
    public static void setUpClass() {
        TestDataBaseManager.setUpTestDDL();
        TestDataBaseManager.setUpTestDML();
        try {
           speakerDao = new MySQLSpeakerDao(new TestConnectionPoolHolder().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test(expected = UnsupportedOperationException.class)
    public void deleteTest(){
        Speaker speaker = SpeakerBuilder.getBuilder().constructSpeaker(1L).build();
        speakerDao.delete(speaker.getId());
    }

    @Test
    public void createTest(){
        Speaker speaker = SpeakerBuilder.getBuilder().constructSpeaker(3L).build();
        assertTrue(speakerDao.create(speaker));
        assertEquals(speaker, speakerDao.findById(speaker.getId()));
        TestDataBaseManager.setUpTestDML();
    }
    @Test
    public void findByIdTest() {
        Speaker expectedSpeaker = SpeakerBuilder.getBuilder().constructSpeaker(2L).build();
        Speaker actualSpeaker = speakerDao.findById(expectedSpeaker.getId());
        assertEquals(expectedSpeaker, actualSpeaker);
    }

    @Test
    public void findAllTest() throws SQLException {
        List<Speaker> expectedSpeakers = new ArrayList<>();
        expectedSpeakers.add(SpeakerBuilder.getBuilder().constructSpeaker(2L).build());
        List<Speaker> actualSpeakers = speakerDao.findAll();
        assertEquals(expectedSpeakers, actualSpeakers);
    }

    @Test
    public void updateTest() {
        Speaker speaker = SpeakerBuilder.getBuilder().constructSpeaker(2L).build();
        speaker.setBonus(5);
        assertTrue(speakerDao.update(speaker));
        assertEquals(speaker, speakerDao.findById(speaker.getId()));
        TestDataBaseManager.setUpTestDML();
    }

    @Test
    public void changeRatingTest(){
        Speaker speaker = SpeakerBuilder.getBuilder().constructSpeaker(2L).build();
        double beforeChangeRating = speaker.getRating();
        assertTrue(speakerDao.changeRating(7, speaker.getId()));
        double afterChangeRating = speakerDao.findById(speaker.getId()).getRating();
        assertNotEquals(beforeChangeRating, afterChangeRating);
        assertEquals(7, afterChangeRating);
        TestDataBaseManager.setUpTestDML();
    }

    @Test
    public void transferBonusTest(){
        Speaker speaker = SpeakerBuilder.getBuilder().constructSpeaker(2L).build();
        double beforeTransferBonus = speaker.getBonus();
        assertTrue(speakerDao.transferBonus(BigDecimal.valueOf(100), speaker.getId()));
        double afterTransferBonus = speakerDao.findById(speaker.getId()).getBonus();
        assertNotEquals(beforeTransferBonus, afterTransferBonus);
        assertEquals(100, afterTransferBonus);
        TestDataBaseManager.setUpTestDML();
    }
}


