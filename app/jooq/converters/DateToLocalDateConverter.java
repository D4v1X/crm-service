package jooq.converters;


import org.jooq.Converter;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Basic JOOQ type Converter from java.util.Date to java.time.LocalDate
 */
public class DateToLocalDateConverter implements Converter<Date, LocalDate> {

    @Override
    public LocalDate from(Date t) {
        return t == null ? null : LocalDate.parse(t.toString());
    }

    @Override
    public Date to(LocalDate u) {
        return u == null ? null : Date.valueOf(u.toString());
    }

    @Override
    public Class<Date> fromType() {
        return Date.class;
    }

    @Override
    public Class<LocalDate> toType() {
        return LocalDate.class;
    }
}
