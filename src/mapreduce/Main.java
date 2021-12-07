package mapreduce;

import composite.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        DirectoryCOMP dir1 = new DirectoryCOMP("dir1");
        DirectoryCOMP dir2 = new DirectoryCOMP("dir2");
        FileCOMP f1 = new FileCOMP("DimenLookupAge8277.csv");
        FileCOMP f2 = new FileCOMP("cities.json");
        FileCOMP f3 = new FileCOMP("example.txt");
        dir1.addChild(f1);
        dir2.addChild(f2);
        dir2.addChild(f3);

        List<DataFrameCOMP> list = Arrays.asList(dir1, dir2);
        List<Integer> result  = Map.map(list, new ColumnsAverage());
        for (Integer elem:result)
            System.out.println(elem);
    }
}
