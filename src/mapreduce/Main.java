package mapreduce;

import composite.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        /*
        DirectoryCOMP dir1 = new DirectoryCOMP("dir1");
        DirectoryCOMP dir2 = new DirectoryCOMP("dir2");
        FileCOMP f1 = new FileCOMP("DimenLookupAge8277.csv");
        FileCOMP f2 = new FileCOMP("cities.json");
        FileCOMP f3 = new FileCOMP("example.txt");
        dir1.addChild(f1);
        dir2.addChild(f2);
        dir2.addChild(f3);
        */

        FileCOMP f1 = new FileCOMP("DimenLookupAge8277.csv");
        FileCOMP f2 = new FileCOMP("cities.json");
        FileCOMP f3 = new FileCOMP("example.txt");

        DirectoryCOMP testdir = new DirectoryCOMP("C:\\Users\\paufe\\Desktop\\Test");
        List<DataFrameCOMP> list = Arrays.asList(testdir, f1, f2, f3);
        List<Integer> result  = MapReduce.map(list, new SizeAverage());
        for (Integer elem : result)
            System.out.println(elem);
        //System.out.println("result reduce: " + MapReduce.reduce(0.0, result, (a, b) -> a + b));
    }
}
