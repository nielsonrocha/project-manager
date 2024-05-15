package com.github.nielsonrocha.portfolio.model.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateMapper {

  public static LocalDate stringToDate(String dateString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return LocalDate.parse(dateString, formatter);
  }

  public static String dateToString(LocalDate date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return date.format(formatter);
  }
}
