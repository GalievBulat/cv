package Interfaces;

import Model.DayOfTheWeek;

public interface Localization {
     String localize(DayOfTheWeek dW);
     String localizeCategory(String category);
     String delocalizeCategory(String category);
}
