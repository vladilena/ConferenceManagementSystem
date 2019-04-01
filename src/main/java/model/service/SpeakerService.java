package model.service;

import model.entity.Speaker;

import java.util.List;

public interface SpeakerService {
    boolean create(long userId);
    boolean changeRating(int newRating, long speakerId);
    List<Speaker> getAll();
    Speaker getById(long id);
}
