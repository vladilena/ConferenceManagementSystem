package model.dao;

import model.entity.Lecture;

public interface LectureDao extends GenericDao<Lecture> {
    boolean approve (long lectureId);
}
