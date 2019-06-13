package util;

public enum Feature {
    WOODS, RAINFOREST, MARSH, FLOODPLAINS, OASIS, MOUNTAINS, REEF, ICE, CATARACT,
    VOLCANO, VOLCANIC_SOIL, GEOTHERMAL_FISSURE, NONE;

    public static String[] stringArray() {
        return new String[]{"None", "Woods", "Rainforest", "Marsh", "Floodplains", "Oasis", "Mountains",
                "Reef", "Ice", "Cataract", "Volcano", "Volcanic Soil", "Geothermal Fissure"};
    }

    public static Feature stringToEnum(String str) {
        switch (str) {
            case "None":
                return NONE;
            case "Woods":
                return WOODS;
            case "Rainforest":
                return RAINFOREST;
            case "Marsh":
                return MARSH;
            case "Floodplains":
                return FLOODPLAINS;
            case "Oasis":
                return OASIS;
            case "Mountains":
                return MOUNTAINS;
            case "Reef":
                return REEF;
            case "Ice":
                return ICE;
            case "Cataract":
                return CATARACT;
            case "Volcano":
                return VOLCANO;
            case "Volcanic Soil":
                return VOLCANIC_SOIL;
            case "Geothermal Fissure":
                return GEOTHERMAL_FISSURE;
        }
        throw new IllegalArgumentException("String " + str + " not found in enum.");
    }
}
