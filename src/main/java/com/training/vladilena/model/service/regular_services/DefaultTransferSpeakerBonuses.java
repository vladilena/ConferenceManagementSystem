package com.training.vladilena.model.service.regular_services;

import com.training.vladilena.model.entity.Speaker;
import com.training.vladilena.model.service.SpeakerService;
import com.training.vladilena.model.service.impl.DefaultSpeakerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import  com.training.vladilena.model.dao.SpeakerDao;

import java.util.List;
/**
 * The {@code DefaultTransferSpeakerBonuses} service is a class to transfer bonuses using {@link SpeakerDao}
 *
 * @author Vladlena Ushakova
 */
public class DefaultTransferSpeakerBonuses implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(DefaultTransferSpeakerBonuses.class);
    private static SpeakerService speakerService;

    public DefaultTransferSpeakerBonuses() {
        speakerService = DefaultSpeakerService.getInstance();
    }

    @Override
    public void run() {
        List<Speaker> speakers = speakerService.getAll();
        LOGGER.debug("Try to transfer bonus for each speaker");
        speakers.forEach(speaker -> speakerService.transferBonus(speaker.getId()));
    }
}


