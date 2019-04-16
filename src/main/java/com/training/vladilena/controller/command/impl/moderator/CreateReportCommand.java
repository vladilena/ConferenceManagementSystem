package com.training.vladilena.controller.command.impl.moderator;

import com.training.vladilena.controller.command.Command;
import com.training.vladilena.model.dto.Report;
import com.training.vladilena.model.entity.Conference;
import com.training.vladilena.model.service.ReportService;
import com.training.vladilena.model.service.impl.DefaultReportService;
import com.training.vladilena.model.validation.ReportValidation;
import com.training.vladilena.model.validation.impl.DefaultReportValidation;
import com.training.vladilena.util.AttributesManager;
import com.training.vladilena.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
/**
 * The {@code CreateReportCommand} class implements {@link Command}
 * and is used for creating the new {@link Report} by Moderator
 * about past {@link Conference} and saving it in file
 *
 * @author Vladlena Ushakova
 */
public class CreateReportCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CreateReportCommand.class);
    private static ReportValidation validation;
    private static ReportService reportService;

    public CreateReportCommand() {
        validation = DefaultReportValidation.getInstance();
        reportService = DefaultReportService.getInstance();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Report report = getReportFromRequest(request);
        if (validation.actualParticipantsAmountValid(report.getActualParticipantsAmount(), report.getRegisteredParticipantsAmount())) {
            LOGGER.debug("Report is valid");
            tryToCreateReport(report, request);
        } else {
            LOGGER.debug("Report isn't valid");
            request.setAttribute(AttributesManager.getProperty("invalid.participants"), true);
            request.setAttribute(AttributesManager.getProperty("report"), report);
        }
        return PathManager.getProperty("path.page.create.report");
    }

    private Report getReportFromRequest(HttpServletRequest request) {
        Report report = new Report();
        report.setConferenceTitle(request.getParameter(AttributesManager.getProperty("title.ukr")));
        report.setConferenceTitleEn(request.getParameter(AttributesManager.getProperty("title.en")));
        report.setDateTime(LocalDateTime.parse(request.getParameter(AttributesManager.getProperty("date.time"))));
        report.setProvidedLecturesAmount(Integer.valueOf(request.getParameter(AttributesManager.getProperty("provided.lectures"))));
        report.setRegisteredParticipantsAmount(Integer.valueOf(request.getParameter(AttributesManager.getProperty("registered.participants"))));
        report.setActualParticipantsAmount(Integer.valueOf(request.getParameter(AttributesManager.getProperty("actual.participants"))));
        return report;
    }

    private void tryToCreateReport(Report report, HttpServletRequest request) {
        if (reportService.create(report)) {
            LOGGER.debug("Report was created");
            request.setAttribute(AttributesManager.getProperty("report.created"), true);
        }else {
            LOGGER.debug("Report wasn't created");
            request.setAttribute(AttributesManager.getProperty("report.not.create"), true);
            request.setAttribute(AttributesManager.getProperty("report"), report);
        }
    }
}


