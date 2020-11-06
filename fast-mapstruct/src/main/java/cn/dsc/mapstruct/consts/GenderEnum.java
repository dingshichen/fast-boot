package cn.dsc.mapstruct.consts;

/**
 * @author dingshichen
 */
public enum GenderEnum {

    WOMAN(0, "女"),
    MAN(1, "男");

    private final int code;

    private final String desc;

    GenderEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
