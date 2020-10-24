package Model;

public enum Station {
    PSH("Пушкина"), SHR("Сахарова"),PBD("Победы"),
    SLV("Славы Водолазов"),SHT("Шахтерская"),BSH("Башкирская"),KRC("Керченская");
    private final String name;
    private Station(String nm) {
        name = nm;
    }
    public static Station getInstance(String st) {
        for (Station station: Station.values()){
            if (st.toLowerCase().equals(station.name.toLowerCase())){
                return station;
            }
        }
        throw new IllegalArgumentException();
    }

    public String getName() {
        return name;
    }
}
