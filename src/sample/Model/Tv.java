package sample.Model;

/**
 * Created by asd on 2017-11-12.
 */
public class Tv {

    private Integer type;
    private Integer size;

    public Tv() {
    }

    public Tv(Integer type, Integer size) {
        this.type = type;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Tv{" +
                "type=" + type +
                ", size=" + size +
                '}';
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
