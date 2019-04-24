package com.training.vladilena.model.validation.impl;

import com.training.vladilena.model.validation.ReportValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultReportValidation implements ReportValidation {
    private static volatile ReportValidation reportValidation;
    private static final Logger LOGGER = LogManager.getLogger(DefaultReportValidation.class);

    private DefaultReportValidation() {
    }

    /**
     * Always return same {@link DefaultReportValidation} instance
     *
     * @return always return same {@link DefaultReportValidation} instance
     */
    public static ReportValidation getInstance() {
        ReportValidation localInstance = reportValidation;
        if (localInstance == null) {
            synchronized (DefaultUserValidation.class) {
                localInstance = reportValidation;
                if (localInstance == null) {
                    reportValidation = new DefaultReportValidation();
                    LOGGER.debug("Create first DefaultReportValidation instance");
                }
            }
        }
        LOGGER.debug("Return DefaultReportValidation instance");
        return reportValidation;
    }


    @Override
    public boolean actualParticipantsAmountValid(int actual, int registered) {
        return actual >= 0 && actual <= registered;
    }
}


