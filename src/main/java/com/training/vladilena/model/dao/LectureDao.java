package com.training.vladilena.model.dao;

import com.training.vladilena.model.entity.Lecture;
/**
 * The {@code LectureDao} implements {@link GenericDao}
 * interface for ORM database entity {@link Lecture}
 *
 * @author Vladlena Ushakova
 */
public interface LectureDao extends GenericDao<Lecture> {
    /**
     * Method to approve {@link Lecture} which find by {@code id}
     *
     * @param lectureId indicates an {@link Lecture} {@code id} that you want to approve
     * @return returns {@code true} if the {@code lecture} was updated
     *         or else {@code false}
     */
    boolean approve(long lectureId);
}
