package model.service;

import model.entity.Conference;

import java.util.List;

public interface ConferenceService {
     List <Conference> getOngoingConferences(long currentTIme);
     List<Conference> getLastConferences(long currentTIme);
    Conference getById(long id);
    boolean create (Conference conference);
}
