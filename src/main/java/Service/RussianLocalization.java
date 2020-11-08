package Service;

import Interfaces.Localization;
import Model.DayOfTheWeek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class RussianLocalization implements Localization {
     private static final Map<String,String> categoriesLocal = new HashMap<>();
     static {
        categoriesLocal.put("help","помощь");
        categoriesLocal.put("default","базовая");
        categoriesLocal.put("problem","проблема");
        categoriesLocal.put("transport","транспорт");
        categoriesLocal.put("rating","оценка");
        categoriesLocal.put("work","работа");
    }
    public String localize(DayOfTheWeek dW){
        switch (dW){
            case MONDAY: return "Понедельник";
            case TUESDAY: return "Вторник";
            case WEDNESDAY: return "Среда";
            case THURSDAY: return "Четверг";
            case FRIDAY: return "Пятница";
            case SATURDAY: return "Суббота";
            case SUNDAY: return "Воскресенье";
            default: throw new IllegalArgumentException();
        }
    }

    @Override
    public String localizeCategory(String category) {
        return categoriesLocal.get(category);
    }
    public List<String> localizeCategories(List<String> categories) {
        List<String> list = new ArrayList<>();
        for (String category: categories){
            list.add(localizeCategory(category));
        }
        return list;
    }

    @Override
    public String delocalizeCategory(String categoryLocal) {
        for (Map.Entry<String,String> entry : categoriesLocal.entrySet()){
            if (categoryLocal.equals(entry.getValue()))
                return entry.getKey();
        }
        return categoryLocal;
    }
}
