package mapreduce;

import composite.*;
import java.util.*;
import factory.DataFrame;


public class Main {
    public static void main(String[] args) {

        FileCOMP f1 = new FileCOMP("DimenLookupAge8277.csv");
        FileCOMP f2 = new FileCOMP("cities.json");
        FileCOMP f3 = new FileCOMP("example.txt");

        DirectoryCOMP dir1 = new DirectoryCOMP("dir1");
        DirectoryCOMP dir2 = new DirectoryCOMP("dir2");
        List<DataFrame> list = Arrays.asList(dir1, dir2, f1, f2, f3);
        List<Integer> mides  = MapReduce.map(list, new Size());
        System.out.println("Llista de mides:");
        for (Integer elem : mides)
            System.out.println(elem);
        System.out.println("Mitjana mides: " + MapReduce.reduce(mides));

        List<Integer> columnes  = MapReduce.map(list, new Columns());
        System.out.println("\nLlista de columnes:");
        for (Integer elem : columnes)
            System.out.println(elem);
        System.out.println("Mitjana columnes: " + MapReduce.reduce(columnes));
    }
}
