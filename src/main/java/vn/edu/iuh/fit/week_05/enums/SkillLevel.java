package vn.edu.iuh.fit.week_05.enums;

public enum SkillLevel {
    BEGINNER(0), INTERMEDIATE(1), ADVANCED(2);
    private final int code;

    SkillLevel(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
