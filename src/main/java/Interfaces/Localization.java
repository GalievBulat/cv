package Interfaces;

import Model.DayOfTheWeek;

import java.util.List;

public interface Localization {
     String localize(DayOfTheWeek dW);
     String localizeCategory(String category);
     String delocalizeCategory(String category);
}
