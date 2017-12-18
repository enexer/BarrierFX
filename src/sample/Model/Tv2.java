package sample.Model;

/**
 * Created by asd on 2017-11-12.
 */
public class Tv2 {
    private Integer type1;
    private Integer type2;
    private Integer type3;
    private Integer type4;

    public Tv2() {
    }

    public Tv2(Integer type1, Integer type2, Integer type3, Integer type4) {
        this.type1 = type1;
        this.type2 = type2;
        this.type3 = type3;
        this.type4 = type4;
    }

    @Override
    public String toString() {
        return "Tv2{" +
                "type1=" + type1 +
                ", type2=" + type2 +
                ", type3=" + type3 +
                ", type4=" + type4 +
                '}';
    }

    public Integer getType1() {
        return type1;
    }

    public void setType1(Integer type1) {
        this.type1 = type1;
    }

    public Integer getType2() {
        return type2;
    }

    public void setType2(Integer type2) {
        this.type2 = type2;
    }

    public Integer getType3() {
        return type3;
    }

    public void setType3(Integer type3) {
        this.type3 = type3;
    }

    public Integer getType4() {
        return type4;
    }

    public void setType4(Integer type4) {
        this.type4 = type4;
    }
}
