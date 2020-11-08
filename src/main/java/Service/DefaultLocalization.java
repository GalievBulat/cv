package Service;

import Interfaces.Localization;
import Model.DayOfTheWeek;

public class DefaultLocalization implements Localization {

    @Override
    public String localize(DayOfTheWeek dW) {
        return dW.name();
    }

    @Override
    public String localizeCategory(String category) {
        return category;
    }

    @Override
    public String delocalizeCategory(String category) {
        return category;
    }
}
