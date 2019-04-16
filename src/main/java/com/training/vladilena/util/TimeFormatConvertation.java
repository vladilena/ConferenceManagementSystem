package com.training.vladilena.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * The {@code TimeFormatConvertation} class is used to convert date and time pattern
 * depends on user's local
 *
 * @author Vladlena Ushakova
 */
public class TimeFormatConvertation extends SimpleTagSupport {
    private LocalDateTime date;
    private String local;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public void doTag() throws IOException {
        if (date != null) {
            formatDate();
        }
    }

    private void formatDate() throws IOException {
        DateTimeFormatter formatter = getDateTimeFormatter();
        getJspContext().getOut().write(formatter.format(date));
    }

    private DateTimeFormatter getDateTimeFormatter() {
        DateTimeFormatter formatter;

        if (local.contains("UA")) {
            formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        } else if (local.contains("US")) {
            formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        } else {
            formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        }
        return formatter;
    }
}


