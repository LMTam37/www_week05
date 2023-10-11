package vn.edu.iuh.fit.week_05.enums;

public enum SkillType {
    TECHNICAL(0), SOFT(1), LANGUAGE(2);
    private final int code;

    SkillType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
