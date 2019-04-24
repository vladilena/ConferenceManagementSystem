package com.training.vladilena.model.service;

import com.training.vladilena.model.dao.SpeakerDao;
import com.training.vladilena.model.entity.Speaker;

import java.util.List;

/**
 * The {@code SpeakerService} service is a specified API for working with the {@link SpeakerDao}
 *
 * @author Vladlena Ushakova
 */
public interface SpeakerService {
    /**
     * Method to create {@link Speaker}
     *
     * @param userId {@code speaker} with this id will be created
     * @return returns {@code true} if {@link Speaker} was created succeed
     * or else {@code false}
     */
    boolean create(long userId);

    /**
     * Method to change {@link Speaker}'s rating
     *
     * @param newRating is a new rating value
     * @param speakerId is a {@link Speaker}'s {@code id} to change rating
     * @return returns {@code true} if {@code rating} was changed succeed
     * or else {@code false}
     */
    boolean changeRating(double newRating, long speakerId);

    /**
     * Method to change {@link Speaker}'s bonus depends on his rating
     *
     * @param speakerId is a {@link Speaker}'s {@code id} to change bonus
     * @return returns {@code true} if {@code rating} was changed succeed
     * or else {@code false}
     */
    boolean transferBonus(long speakerId);

    /**
     * Method to change {@link Speaker}'s bonus to zero
     *
     * @param speakerId is a {@link Speaker}'s {@code id} to change bonus
     * @return returns {@code true} if {@code rating} was changed succeed
     * or else {@code false}
     */
    boolean getBonus(long speakerId);

    /**
     * Method to get all {@link Speaker}s
     *
     * @return return {@link List} of all {@link Speaker}s
     */
    List<Speaker> getAll();

    /**
     * Method return {@link Speaker} which find by {@code id}
     *
     * @param id indicates an {@link Speaker} {@code id} that you want to return
     * @return return {@link Speaker} by {@code id}
     */
    Speaker getById(long id);
}
