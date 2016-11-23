package bruce.com.expresscheck.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by WangChunhe on 2016/10/23.
 */

public class ExpressCompany implements Parcelable {

    private String name; //快递名称
    private String type; //快递代号
    private String letter;  //快递首字母
    private String tel;  //电话
    private String number;  //测试单号

    public ExpressCompany(String name, String type) {
        this.name = name;
        this.type = type;
    }

    protected ExpressCompany(Parcel in) {
        name = in.readString();
        type = in.readString();
        letter = in.readString();
        tel = in.readString();
        number = in.readString();
    }

    public static final Creator<ExpressCompany> CREATOR = new Creator<ExpressCompany>() {
        @Override
        public ExpressCompany createFromParcel(Parcel in) {
            return new ExpressCompany(in);
        }

        @Override
        public ExpressCompany[] newArray(int size) {
            return new ExpressCompany[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpressCompany expressCompany = (ExpressCompany) o;
        return com.google.common.base.Objects.equal(name, expressCompany.name)
        &&com.google.common.base.Objects.equal(type, expressCompany.type);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(name,type);
    }

    @Override
    public String toString() {

        return "Name = " + name + " Type = " + type + " Letter = " + letter + " Tel = " + tel + " Number = " + number ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(letter);
        dest.writeString(tel);
        dest.writeString(number);
    }
//
//    public static final Parcelable.Creator<ExpressCompany> CREATOR = new Parcelable.Creator<ExpressCompany>() {
//        @Override
//        public ExpressCompany createFromParcel(Parcel source) {
//            return new ExpressCompany(source);
//        }
//
//        @Override
//        public ExpressCompany[] newArray(int size) {
//            return new ExpressCompany[size];
//        }
//    };

//    private ExpressCompany(Parcel in){
//        name = in.readString();
//        type = in.readString();
//        tel = in.readString();
//        letter = in.readString();
//        number = in.readString();
//    }
}
