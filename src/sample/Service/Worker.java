package sample.Service;



import sample.Model.Result;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by asd on 2017-11-12.
 */
public class Worker<T> implements Runnable {
    List<T> row;
    T t;
    final CyclicBarrier barrier;
    final Result result;
    final int conditionMin;
    final int conditionMax;
    final String conditionWord;


    Worker(List<T> row, CyclicBarrier barrier, Result result, T t, int conditionMin, int conditionMax, String conditionWord) {
        this.row = row;
        this.barrier = barrier;
        this.result = result;
        this.t = t;
        this.conditionMin = conditionMin;
        this.conditionMax = conditionMax;
        this.conditionWord = conditionWord;
    }

    public void run() {
//            while (true) {

        System.out.println(Thread.currentThread().getName() +
                "  Waiting at barrier. With part size: "+row.size());


        Starter.callback.updateView(Thread.currentThread().getName() +
                "  Waiting at barrier. With part size: "+row.size());


        Random random = new Random();
        int r= 1000 + random.nextInt(2500 - 1000 + 1);
        try {
            TimeUnit.MILLISECONDS.sleep(r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LinkedList<T> newList = new LinkedList<T>();

        Class<?> cName = null;


        try {
            cName = Class.forName(t.getClass().getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (T tv : row) {
            try {

                ArrayList<Object> arrayList = new ArrayList<Object>();
                for (Method method : findGettersSetters(cName)) {
//                    System.out.println(method);
                    Object valueFromGetter = method.invoke(tv);
//                    System.out.println("VAL: "+valueFromGetter);
                    arrayList.add(valueFromGetter);
                }
//                System.out.println("ARR sizel"+arrayList.toString());
//                Object size = arrayList.get(0);//findGettersSetters(cName).get(0).invoke(tv);
//                Object type = arrayList.get(1);//findGettersSetters(cName).get(1).invoke(tv);

                boolean bol = true;
                for (Object obj : arrayList) {
//                    System.out.println("WWWW"+obj.toString());
                    if (obj.getClass().equals(String.class)) {
//                      System.out.println(obj+"STRRR");
                        // String handle
                        String s = String.valueOf(obj);
                        if (s.contains(conditionWord)) {
                            // ok
                        } else {
                            bol = false;
                        }
                    } else {
                        // Other types handle
                        int s = (int) obj;
                        if (s >= conditionMin && s <= conditionMax) {
                            // OK
                        } else {
                            bol = false;
                        }
                    }
                }
                if (bol) {
                    newList.add(tv);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        // prevent adding empty list []
//        if (newList.size() != 0) {
//            result.setWynik(newList);
//
//        }
        result.setWynik(newList);
        result.setSum(newList.size());




        double pom=(double) 1/barrier.getParties();
        double actualProgress=pom*result.getWynik().size();


        Starter.callback.updateView(Thread.currentThread().getName()+"  Merges filtered part: "+result.getWynik().size()+", part size: "+newList.size()+", merged parts elements: "+result.getSum(),actualProgress);

        try {
            TimeUnit.MILLISECONDS.sleep(500);
            barrier.await();
        } catch (InterruptedException ex) {
            return;
        } catch (BrokenBarrierException ex) {
            return;
        }
//            }
    }

    static ArrayList<Method> findGettersSetters(Class<?> c) {
        ArrayList<Method> list = new ArrayList<Method>();
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods)
//            if (isGetter(method) || isSetter(method))
//                list.add(method);
            if (isGetter(method))
                list.add(method);
        return list;
    }

    public static boolean isGetter(Method method) {
        if (Modifier.isPublic(method.getModifiers()) &&
                method.getParameterTypes().length == 0) {
            if (method.getName().matches("^get[A-Z].*") &&
                    !method.getReturnType().equals(void.class))
                return true;
            if (method.getName().matches("^is[A-Z].*") &&
                    method.getReturnType().equals(boolean.class))
                return true;
        }
        return false;
    }

    public static boolean isSetter(Method method) {
        return Modifier.isPublic(method.getModifiers()) &&
                method.getReturnType().equals(void.class) &&
                method.getParameterTypes().length == 1 &&
                method.getName().matches("^set[A-Z].*");
    }
}
