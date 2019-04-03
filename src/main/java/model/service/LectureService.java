package model.service;

import model.entity.Lecture;
import model.entity.User;

public interface LectureService {
    boolean create(Lecture lecture,  long conferenceId);

    boolean approve(long lectureId);

    Lecture getById(long lectureId);

    boolean update(Lecture lecture);

    boolean delete(long lectureId);
}


