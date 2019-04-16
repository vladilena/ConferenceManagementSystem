package com.training.vladilena.model.validation;
import com.training.vladilena.model.dto.Report;
/**
 * The {@code ReportValidation} is a interface which provide method to validate {@link Report}
 *
 * @author Vladlena ushakova
 */
public interface ReportValidation {
    boolean actualParticipantsAmountValid(int actual, int registered);
}
