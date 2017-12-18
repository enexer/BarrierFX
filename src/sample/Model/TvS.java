package sample.Model;

/**
 * Created by asd on 2017-11-12.
 */
public class TvS {
    private String type1;
    private Integer type2;

    public TvS() {
    }

    public TvS(String type1, Integer type2) {
        this.type1 = type1;
        this.type2 = type2;
    }

    @Override
    public String toString() {
        return "TvS{" +
                "type1='" + type1 + '\'' +
                ", type2=" + type2 +
                '}';
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public Integer getType2() {
        return type2;
    }

    public void setType2(Integer type2) {
        this.type2 = type2;
    }
}
