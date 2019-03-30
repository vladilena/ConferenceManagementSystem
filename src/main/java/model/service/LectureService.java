package model.service;

import model.entity.Lecture;
import model.entity.User;

public interface LectureService {
    boolean create (Lecture lecture, User user, long conferenceId);
    boolean approve (long lectureId);
}


