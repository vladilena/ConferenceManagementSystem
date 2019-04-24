package com.training.vladilena.controller.command.impl.redirect;

import com.training.vladilena.controller.command.Command;
import com.training.vladilena.model.dto.Report;
import com.training.vladilena.model.entity.Conference;
import com.training.vladilena.model.service.ConferenceService;
import com.training.vladilena.model.service.impl.DefaultConferenceService;
import com.training.vladilena.util.AttributesManager;
import com.training.vladilena.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The {@code RedirectCreateReportCommand} class implements {@link Command}
 * and is used for redirect to the create report page
 * and fill it with {@link Report} data
 *
 * @author Vladlena Ushakova
 */
public class RedirectCreateReportCommand implements Command {
    private static ConferenceService conferenceService;

    public RedirectCreateReportCommand() {
        conferenceService = DefaultConferenceService.getInstance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long conferenceId = Long.valueOf(request.getSession().getAttribute(AttributesManager.getProperty("conference.id")).toString());
        Conference currentConference = conferenceService.getById(conferenceId);

        Report report = new Report();
        report.setConferenceTitle(currentConference.getTitle());
        report.setConferenceTitleEn(currentConference.getTitleEn());
        report.setDateTime(currentConference.getDateTime());
        report.setProvidedLecturesAmount(currentConference.getConferenceLectures().size());
        report.setRegisteredParticipantsAmount(currentConference.getRegisteredParticipants().size());

        request.setAttribute(AttributesManager.getProperty("report"), report);
        return PathManager.getProperty("path.page.create.report");
    }


}


