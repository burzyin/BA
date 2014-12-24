package com.exadel.belarusattractions.dto.map;

/**
 * Enum MapType.
 * Supported types:
 * Google map
 * Open street map.
 * IllegalArgumentException will be thrown
 * in case of unsupported mapType.
 * <p/>
 * Developer: Yan Khonskiy
 * Created: 3:52 AM, 11/26/12
 */
public enum MapType {

    GOOGLE_MAP("googleMap"),
    OPEN_STREET_MAP("openStreetMap");

    private final String typeCode;

    private MapType(String typeCode) {
        this.typeCode = typeCode;
    }

    public static MapType parse(String type) {
        if (type == null) {
            return null;
        }

        for (MapType value : MapType.values()) {
            if (value.typeCode.equals(type)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unsupported type " + type);
    }

    /**
     *
     * @return
     */
    public String getTypeCode() {
        return this.typeCode;
    }
}