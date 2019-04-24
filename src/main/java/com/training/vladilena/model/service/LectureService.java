package com.training.vladilena.model.service;

import com.training.vladilena.model.dao.LectureDao;
import com.training.vladilena.model.entity.Lecture;

/**
 * The {@code LectureService} service is a specified API for working with the {@link LectureDao}
 *
 * @author Vladlena Ushakova
 */
public interface LectureService {
    /**
     * Method to create {@link Lecture}
     *
     * @param lecture      this {@code lecture} will be created
     * @param conferenceId {@code lecture} with this {@code conferenceId} will be created
     * @return returns {@code true} if {@link Lecture} was created succeed
     * or else {@code false}
     */
    boolean create(Lecture lecture, long conferenceId);

    /**
     * Method to approve {@link Lecture} which find by {@code id}
     *
     * @param lectureId indicates an {@link Lecture} {@code id} that you want to approve
     * @return returns {@code true} if the {@code lecture} was updated
     * or else {@code false}
     */
    boolean approve(long lectureId);

    /**
     * Method to return {@link Lecture} which find by {@code id}
     *
     * @param lectureId indicates an {@link Lecture} {@code id} that you want to return
     * @return return {@link Lecture} by {@code id}
     */
    Lecture getById(long lectureId);

    /**
     * Method to update {@link Lecture}
     *
     * @param lecture the {@code lecture} which will be updated
     * @return returns {@code true} if the {@code lecture} was updated
     * or else {@code false}
     */
    boolean update(Lecture lecture);

    /**
     * Method delete {@link Lecture}
     *
     * @param lectureId the {@code lecture} will be deleted by this {@code id}
     * @return returns {@code true} if the {@code lecture} was deleted
     * or else {@code false}
     */
    boolean delete(long lectureId);
}


