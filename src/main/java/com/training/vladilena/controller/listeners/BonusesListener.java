package com.training.vladilena.controller.listeners;

import com.training.vladilena.model.service.regular_services.DefaultTransferSpeakerBonuses;
import com.training.vladilena.util.BusinessLogicManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 * The {@code BonusesListener} class implements {@link ServletContextListener}
 * and is used to execute {@link DefaultTransferSpeakerBonuses} periodically
 *
 * @author Vladlena Ushakova
 */
public class BonusesListener implements ServletContextListener {
    private ScheduledExecutorService scheduler;

    /**
     *  The method planned to execute {@link DefaultTransferSpeakerBonuses} with
     *  initial delay and period
     * @param sce is a ServletContextEvent event
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        Runnable command = new DefaultTransferSpeakerBonuses();
        long initialDelay = Long.valueOf(BusinessLogicManager.getProperty("initial.delay.bonuses"));
        TimeUnit unit = TimeUnit.DAYS;
        long period = Long.valueOf(BusinessLogicManager.getProperty("period.bonuses"));

        scheduler.scheduleAtFixedRate(command, initialDelay, period, unit);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}


