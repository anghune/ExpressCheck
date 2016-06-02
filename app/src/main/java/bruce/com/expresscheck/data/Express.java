package bruce.com.expresscheck.data;

import android.support.annotation.Nullable;

/**
 * Created by WangChunhe on 2016/5/23.
 */
public class Express {
    //快递公司名
    @Nullable
    private String type;

    //快递单号
    private String number;

    public Express(String type, String number) {
        this.type = type;
        this.number = number;
    }

    /**
     * 该构造函数表示自动查询快速公司
     * @param number
     */
    public Express(String number) {
        this("auto", number);
    }

    public void setType(@Nullable String type) {
        this.type = type;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Nullable
    public String getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Express express = (Express) o;

        return com.google.common.base.Objects.equal(type, express.type)
                &&com.google.common.base.Objects.equal(number, express.number);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(type, number);
    }

    @Override
    public String toString() {
        return "typr: " + type + " number: "+ number;
    }
}
