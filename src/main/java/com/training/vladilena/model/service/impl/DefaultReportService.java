package com.training.vladilena.model.service.impl;

import com.training.vladilena.model.dto.Report;
import com.training.vladilena.model.service.ReportService;
import com.training.vladilena.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * {@inheritDoc}
 */
public class DefaultReportService implements ReportService {
    private static final Logger LOGGER = LogManager.getLogger(DefaultReportService.class);
    private static int counter = 1;
    private static volatile ReportService reportService;

    private DefaultReportService() {}
    /**
     * Always return same {@link DefaultReportService} instance
     *
     * @return always return same {@link DefaultReportService} instance
     */
    public static ReportService getInstance() {
        ReportService localInstance = reportService;
        if (localInstance == null) {
            synchronized (DefaultReportService.class) {
                localInstance = reportService;
                if (localInstance == null) {
                    reportService = new DefaultReportService();
                    LOGGER.debug("Create first DefaultReportService instance");
                }
            }
        }
        LOGGER.debug("Return DefaultReportService instance");
        return reportService;
    }

    @Override
    public boolean create(Report report) {
        report.setId(counter++);
        Path file = null;
        List<String> lines = new ArrayList<>(Arrays.asList(report.toString().split("\n")));
        try {
            file = Paths.get(PathManager.getProperty("path.to.report"));
            Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
            return true;
        } catch (NoSuchFileException e) {
            LOGGER.error("Threw a NoSuchFileException for: " + file + "stack trace: " + e);
            return false;
        } catch (IOException e) {
            LOGGER.error("Threw a IOException: " + e);
            return false;
        }
    }
}


