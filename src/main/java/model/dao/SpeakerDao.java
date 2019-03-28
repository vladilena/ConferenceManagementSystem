package model.dao;

import model.entity.Speaker;

public interface SpeakerDao extends GenericDao<Speaker> {
    boolean changeRating(int newRating, long speakerid);
}
