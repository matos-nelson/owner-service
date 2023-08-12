package org.rent.circle.owner.api.enums;

public enum Suffix {
    JR("Jr"),
    SR("Sr"),
    II("II"),
    III("III"),
    IV("IV"),
    V("V");

    public final String value;

    Suffix(String value) {
        this.value = value;
    }

    public static Suffix getValue(String value) {
        for (Suffix e : values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        return null;
    }
}
