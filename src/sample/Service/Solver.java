package sample.Service;


import sample.Controller;
import sample.Model.Result;

import javax.naming.ldap.Control;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by asd on 2017-11-12.
 */
public class Solver<T> {
    final int parties;
    final int divider;
    final LinkedList<String> linkedList;
    final CyclicBarrier barrier;
    final Result result;
    final ArrayList<List<T>> all;
    final T t;
    final int conditionMin;
    final int conditionMax;
    final String conditionWord;

    public Solver(int divider, int parties, ArrayList<List<T>> all, T t, int conditionMin, int conditionMax, String conditionWord) {
        this.divider = divider;
        this.parties = parties;
        this.conditionMin = conditionMin;
        this.conditionMax = conditionMax;
        this.conditionWord = conditionWord;
        linkedList = null;
        this.t = t;
        this.all = all;
        result = new Result<T>(new ArrayList<List<T>>(), 0);
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

        barrier = new CyclicBarrier(parties,
                new Runnable() {
                    public void run() {
                        System.out.println("\n\n===============================================");
                        System.out.println("!!! Barrier, Test Vaule: " + result.getSum());
                        System.out.print("Waiting threads: " + barrier.getNumberWaiting() + "\nRequired parties: " + barrier.getParties());
                        System.out.println("\n" + result.getWynik());

                        Date dateobj = new Date();
                        Starter.callback.updateView2("#######################################################\nTime: "+df.format(dateobj)+"\nWaiting threads: " + barrier.getNumberWaiting() + ", Required parties: " + barrier.getParties());
                        Starter.callback.updateView2( "Barrier, Filtered elements: " + result.getSum());
                        // Show all
                        //Starter.callback.updateView2(result.getWynik().toString()+"\n");

                        // Show each part with size
                        int siz = 0;
                        ArrayList<List> lis = result.getWynik();
                        for (List<T> tv : lis) {
                            Starter.callback.updateView2("PART SIZE: " + tv.size() + ", " + tv.toString()); // ********* CALLBACK
                            siz += tv.size();
                        }

                        result.reset();
                    }
                });


        recursiveOption(all, parties);
//        normalOption(parties);// divider==parties required


    }

    public void normalOption(int p) {
        System.out.println("SIZE: " + all.size());
        for (int i = 0; i < p; ++i) {
            Starter.callback.updateView("Starting Thread: "+i);
            new Thread(new Worker<T>(all.get(i), barrier, result, t, conditionMin, conditionMax, conditionWord)).start();
        }
    }


    public void recursiveOption(ArrayList<List<T>> arrayList, int max_divider) {

        String star ="";

        if (arrayList.size() == max_divider) {
            for (int i = 0; i < max_divider; ++i) {
                star= star.concat("*");
                Starter.callback.updateView("Starting Thread: "+i+" "+star);
                new Thread(new Worker<T>(arrayList.get(i), barrier, result, t, conditionMin, conditionMax, conditionWord)).start();
            }
        } else {
            ArrayList<List<T>> help = Distribution.dataDistribution(2, arrayList);
            recursiveOption(help, max_divider);
        }
    }


}
