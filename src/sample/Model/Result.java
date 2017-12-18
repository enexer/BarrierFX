package sample.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asd on 2017-11-12.
 */
public class Result<T> {

    private ArrayList<List<T>> wynik;


    private int sum;

    public Result(ArrayList<List<T>> wynik, int sum) {
        this.wynik = wynik;
        this.sum = sum;
    }

    public ArrayList<List<T>> getWynik() {
        return wynik;
    }

    public void reset(){
        wynik.clear();
    }

    public void setWynik(List<T> wn) {
        this.wynik.add(wn);
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum + this.sum;
    }


}
