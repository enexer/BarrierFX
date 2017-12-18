package sample.Service;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.MatchResult;

/**
 * Created by asd on 2017-11-12.
 */
public class Distribution {
    public static <T> ArrayList<List<T>> dataDistribution(int d, LinkedList<T> linkedList) {

        LinkedList<T> lista_new;
        lista_new = linkedList;


        int divider = d;
        int size = lista_new.size();
        int w = size / divider;
        int w2 = w;
        int w1 = 0;

//        Set<List<Tv>> set = new HashSet<List<Tv>>();
        ArrayList<List<T>> lol = new ArrayList<List<T>>();

//        System.out.println("w " + w + ", size;" + size);
        for (int i = 1; i <= divider; i++) {
            List<T> lst;
            if (i == divider) {
                lst = lista_new.subList(w1, size);
            } else {
                lst = lista_new.subList(w1, w2);
            }

            lol.add(lst);

//            System.out.println(" w1:" + w1 + " w2 " + w2);
            w1 = w2;
            w2 += w;
        }

        Starter.callback.updateView("\n\nSET PARTS: " + lol.size()); // ********* CALLBACK
        System.out.println("SET SIZE:" + lol.size());
        int siz = 0;
        for (List<T> tv : lol) {
            Starter.callback.updateView("PART SIZE: " + tv.size() + ", " + tv.toString()); // ********* CALLBACK
            System.out.println("SIZE: " + tv.size() + ", " + tv.toString());
            siz += tv.size();
        }
//
        Starter.callback.updateView("SET SIZE: " + siz + "\n-------------------------------------------"); // ********* CALLBACK
        System.out.println("SET SIZE: " + siz + "\n-------------------------------------------");
//        System.out.println(lista_new.getFirst().getSize());

        return lol;

    }

    public static <T> ArrayList<List<T>> dataDistribution(int d, ArrayList<List<T>> c) {

        int divider = d;


        ArrayList<List<T>> copy = c;
        ArrayList<List<T>> newList = new ArrayList<List<T>>();

        for (int i = 0; i < copy.size(); i++) {
            List<T> l = copy.get(i);

            int size = l.size();
            int w = size / divider;
            int w2 = w;
            int w1 = 0;

            if (l.size() < 3) {
                newList.add(l);
            } else {
                for (int j = 1; j <= divider; j++) {
                    List<T> lst;
                    if (j == divider) {
                        lst = l.subList(w1, size);
                    } else {
                        lst = l.subList(w1, w2);
                    }

                    newList.add(lst);

//                    System.out.println(" w1:" + w1 + " w2 " + w2);
                    w1 = w2;
                    w2 += w;
                }
            }
        }

        Starter.callback.updateView("SET PARTS: " + newList.size()); // ********* CALLBACK
        System.out.println("SET SIZE:" + newList.size());
        int siz = 0;
        for (List<T> tv : newList) {
            Starter.callback.updateView("PART SIZE: " + tv.size() + ", " + tv.toString()); // ********* CALLBACK
            System.out.println("SIZE: " + tv.size() + ", " + tv.toString());
            siz += tv.size();
        }
//
        Starter.callback.updateView("-------------------------------------------"); // ********* CALLBACK
//        Starter.callback.updateView("SET SIZE: " + siz + "\n-------------------------------------------"); // ********* CALLBACK
        System.out.println("SET SIZE: " + siz + "\n-------------------------------------------");
//        System.out.println(lista_new.getFirst().getSize());
        return newList;
    }

    public static <T> LinkedList<T> func_file(T t, String file_name, int max_objects) {

        String separator = ",";
        LinkedList<T> lista = new LinkedList<T>();
        int max = max_objects;
        File the_file = new File(file_name);

        // Reflection
        /////////////////////////////////////////////////
        Constructor[] constructors; //= new Constructor[0];
        Class<?> cName = null;
        try {
            cName = Class.forName(t.getClass().getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        constructors = cName.getConstructors();
        // Constructor param types
        Class<?>[] constructorParam = constructors[1].getParameterTypes();
        System.out.println(constructorParam);
        // Constructor param 1
        Class w1 = constructorParam[0];
        // Cons param 2
        Class w2 = constructorParam[1];
        System.out.println(w1 + "_" + w2);
        // Constructor param length
        int con_size = constructors[1].getParameterCount();
        ////////////////////////////////////////////////////


//        ArrayList<String> words =  new ArrayList<String>();
//        words.add("");
//        words.add("");
//        words.add("");

        String[] words = {"Samsung", "Sony", "LG", "Toshiba", "Dell", "Acer", "Asus", "MSI", "Sharp", "Panasonic", "Nec", "Philips", "HP"};
        String[] words2 = {"LCD", "LED", "TFT", "ISP", "TN", "OLED", "AMOLED"};
        String[] words3 = {"60Hz", "75Hz", "80Hz", "120Hz", "144Hz", "240Hz"};


        if (the_file.exists()) {
            System.err.println("file already exists");
            //System.exit(0);
        } else {
            try {
                PrintWriter the_output = new PrintWriter(the_file);
                Random random = new Random();
                for (int i = 0; i < max; i++) {
                    ArrayList<String> arrayList = new ArrayList<String>();
                    for (int j = 0; j < con_size; j++) {
                        if (constructorParam[j].equals(String.class)) {
                            arrayList.add(words[random.nextInt(words.length - 1)]
                                    + "_" + words2[random.nextInt(words2.length - 1)]
                                    + "_" + words3[random.nextInt(words3.length - 1)]);
                        } else {
                            int minR = 20;
                            int maxR = 60;
                            arrayList.add(String.valueOf(random.nextInt(maxR - minR + 1) + minR));
                        }
                    }
                    System.out.println(arrayList.toString());
                    the_output.println(arrayList.toString()
                            .replace("[", "")
                            .replace("]", "")
                            .replace(" ", "")
                            .replace(",", separator));
//                    the_output.println(random.nextInt(100) + separator + random.nextInt(50));
                }
                System.out.println("finished writing");
                the_output.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        try {
            Scanner s = new Scanner(the_file);
            System.out.println("reading data" + "\n__________");

            Random random = new Random();

            while (s.hasNext()) {
                Scanner s2 = new Scanner(s.nextLine());

                String qwe = "(\\w+)";
                String pattern = "";
                pattern = new StringBuilder(pattern).append(qwe).toString();

                for (int i = 1; i < con_size; i++) {
                    pattern = new StringBuilder(pattern).append(separator).append(qwe).toString();
                }
//                String pat = "(\\w+)" + separator + "(\\w+)"+ separator + "(\\w+)"+ separator + "(\\w+)";
                System.out.println("PATT:  " + pattern);
                s2.findInLine(pattern);
                MatchResult result = s2.match();

                ArrayList<Object> list = new ArrayList<Object>();

                for (int x = 0; x < con_size; x++) {
                    Class q = constructorParam[x];
                    String rand = result.group(x + 1);
                    Object o1 = rand;
                    if (q.equals(Double.class)) {
//                        System.out.println("Double");
                        o1 = Double.valueOf(rand);
                    } else if (q.equals(Integer.class)) {
//                        System.out.println("Integer");
                        o1 = Integer.valueOf(rand);
                    } else if (q.equals(String.class)) {
//                        System.out.println("String");
                        o1 = String.valueOf(rand);
                    } else if (q.equals(Float.class)) {
//                        System.out.println("Float");
                        o1 = Float.valueOf(rand);
                    }
                    list.add(o1);
                }
//                lista.add((T) cName.getDeclaredConstructor(constructorParam)
//                        .newInstance(Integer.valueOf(result.group(1)), Integer.valueOf(result.group(2))));
                lista.add((T) cName.getDeclaredConstructor(constructorParam).newInstance(list.toArray()));
//                lista.add(new Tv(Integer.valueOf(result.group(1)), Integer.valueOf(result.group(2))));
                for (int i = 1; i <= result.groupCount(); i++) {
                    System.out.print(result.group(i) + ":");
//                    System.out.println(list.toString());
                }
                System.out.println("");
            }
            System.out.println("finished reading" + "\n__________");
            s.close();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return lista;
    }
}
