package com.training.vladilena.model.dao.impl;

import com.training.vladilena.model.dao.SpeakerDao;
import com.training.vladilena.model.dao.mapper.impl.SpeakerMapper;
import com.training.vladilena.model.entity.Speaker;
import com.training.vladilena.util.SQLManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code MySQLSpeakerDao} class implements {@link SpeakerDao}
 * and specified for MySQL DB
 *
 * @author Vladlena Ushakova
 */
public class MySQLSpeakerDao implements SpeakerDao {
    private static final Logger LOGGER = LogManager.getLogger(MySQLSpeakerDao.class);

    private Connection connection;
    private SpeakerMapper speakerMapper;

    public MySQLSpeakerDao(Connection connection) {
        LOGGER.debug("MySQLSpeakerDao constructor");
        this.connection = connection;
        speakerMapper = new SpeakerMapper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean create(Speaker entity) {
        LOGGER.debug("Try to insert into speaker table");
        boolean resultFlag = false;
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("insert.speaker"))) {
            stm.setLong(1, entity.getId());
            stm.setDouble(2, entity.getRating());
            stm.setDouble(3, entity.getBonus());
            if (stm.executeUpdate() > 0) {
                resultFlag = true;
            }
            LOGGER.debug("Insert was successful");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows:" + e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Speaker findById(long id) {
        Speaker result = null;
        LOGGER.debug("Try to find speaker by id " + id);
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("find.speaker.by.id"))) {
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            result = speakerMapper.parseFromResultSet(rs, false);
            LOGGER.debug("Select was successful");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Speaker> findAll() {
        List<Speaker> resultList = new ArrayList<>();
        Map<Long, Speaker> speakers = new HashMap<>();
        LOGGER.debug("Try to find all speakers");
        try (Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(SQLManager.getProperty("find.all.speakers"));
            while (rs.next()) {
                Speaker speaker = speakerMapper.parseFromResultSet(rs, true);
                speaker = speakerMapper.makeUnique(speakers, speaker);
                resultList.add(speaker);
            }
            LOGGER.debug("Found all speakers successful");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return resultList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(Speaker entity) {
        boolean resultFlag = false;
        LOGGER.debug("Try to update speaker");
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("update.speaker"))) {
            stm.setDouble(1, entity.getRating());
            stm.setDouble(2, entity.getBonus());
            stm.setLong(3, entity.getId());
            if (stm.executeUpdate() > 0) {
                resultFlag = true;
            }
            LOGGER.debug("Successful update");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(long id) {
        //On cascade delete from user
        LOGGER.error("Throw new UnsupportedOperationException");
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        LOGGER.debug("Try to close connection");
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Threw a RuntimeException, full stack trace follows " + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean changeRating(double newRating, long speakerId) {
        boolean resultFlag = false;
        LOGGER.debug("Try to change rating for speaker with id: " + speakerId);
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("update.speaker.change.rating"))) {
            stm.setDouble(1, newRating);
            stm.setLong(2, speakerId);
            if (stm.executeUpdate() > 0) {
                resultFlag = true;
            }
            LOGGER.debug("Changing was successful");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean transferBonus(BigDecimal bonus, long speakerId) {
        boolean resultFlag = false;
        LOGGER.debug("Try to change bonus for speaker with id: " + speakerId);
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("update.speaker.transfer.bonus"))) {
            stm.setBigDecimal(1, bonus);
            stm.setLong(2, speakerId);
            if (stm.executeUpdate() > 0) {
                resultFlag = true;
            }
            LOGGER.debug("Changing was successful");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return resultFlag;
    }
}


