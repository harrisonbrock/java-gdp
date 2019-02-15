package com.harrisonbrock.javajdp.log;

import lombok.Data;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class NationLogMessage implements Serializable {
    private final String text;
    private final String formatteDate;

    public NationLogMessage(String text) {
        this.text = text;
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd hh:mm:ss a";
        DateFormat format = new SimpleDateFormat(strDateFormat);
        formatteDate = format.format(date);
    }

}
