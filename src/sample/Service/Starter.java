package sample.Service;

import sample.CallBackInterface;
import sample.Model.Tv;
import sample.Model.Tv2;
import sample.Model.TvS;

/**
 * Created by asd on 2017-11-12.
 */
public class Starter {

    public static CallBackInterface callback;

    private int min;
    private int max;
    private String word;
    int divider;
    int parties;
    String file_name;

    public Starter(CallBackInterface callback, int min, int max, String word, int divider, int parties, String file_name) {

        this.callback = callback;
        this.min = min;
        this.max = max;
        this.word = word;
        this.divider=divider;
        this.parties=parties;
        this.file_name=file_name;

    }

    public void startS(){
        int file_max = 113;
        TvS tvS = new TvS(); // String, Integer
        Tv2 tv2 = new Tv2(); // 4 Integer
        Tv tv = new Tv(); // 2 Integer

        new Solver<TvS>(divider, parties,
                Distribution.dataDistribution(divider, Distribution.func_file(tvS, file_name, file_max)),
                tvS, min, max, word);
    }


    public static void setCallback(CallBackInterface callback) {
        Starter.callback = callback;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setDivider(int divider) {
        this.divider = divider;
    }

    public void setParties(int parties) {
        this.parties = parties;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

}
