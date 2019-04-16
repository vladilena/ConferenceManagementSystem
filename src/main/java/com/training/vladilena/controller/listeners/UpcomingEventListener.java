package com.training.vladilena.controller.listeners;

import com.training.vladilena.model.service.regular_services.DefaultSendInvitationsService;
import com.training.vladilena.util.BusinessLogicManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 * The {@code UpcomingEventListener} class implements {@link ServletContextListener}
 * and is used to execute {@link DefaultSendInvitationsService} periodically
 *
 * @author Vladlena Ushakova
 */
public class UpcomingEventListener implements ServletContextListener {
    private ScheduledExecutorService scheduler;

    /**
     *  The method planned to execute {@link DefaultSendInvitationsService} with
     *  initial delay and period
     * @param event is a ServletContextEvent event
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        Runnable command = new DefaultSendInvitationsService();
        long initialDelay = Long.valueOf(BusinessLogicManager.getProperty("initial.delay.mail"));
        TimeUnit unit = TimeUnit.DAYS;
        long period = Long.valueOf(BusinessLogicManager.getProperty("period.mail"));

        scheduler.scheduleAtFixedRate(command, initialDelay, period, unit);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}


