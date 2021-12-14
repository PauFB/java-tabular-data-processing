package visitor;

import composite.*;
import factory.DataFrame;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        FileCOMP f1 = new FileCOMP("DimenLookupAge8277.csv");
        FileCOMP f2 = new FileCOMP("cities.json");
        FileCOMP f3 = new FileCOMP("example.txt");

        DirectoryCOMP dir1 = new DirectoryCOMP("dir1");
        DirectoryCOMP dir2 = new DirectoryCOMP("dir2");
        List<DataFrame> list = Arrays.asList(dir1, f1, f3);
        List<DataFrame> list2 = Arrays.asList(dir2, f2);

        System.out.println("Maxim dir1 SortOrder");
        for (DataFrame d : list) {
            System.out.println(d.accept(new MaximumVisitor(),"SortOrder"));
        }
        System.out.println("Maxim dir2 LatD");
        for (DataFrame d : list2) {
            System.out.println(d.accept(new MaximumVisitor(),"LatD"));
        }

        System.out.println("\nMinim dir1 SortOrder");
        for (DataFrame d : list) {
            System.out.println(d.accept(new MinimumVisitor(),"SortOrder"));
        }
        System.out.println("Minim dir2 LatD");
        for (DataFrame d : list2) {
            System.out.println(d.accept(new MinimumVisitor(),"LatD"));
        }

        System.out.println("\nSuma dir1 SortOrder");
        for (DataFrame d : list) {
            System.out.println(d.accept(new SumVisitor(),"SortOrder"));
        }
        System.out.println("Suma dir2 LatD");
        for (DataFrame d : list2) {
            System.out.println(d.accept(new SumVisitor(),"LatD"));
        }

        System.out.println("\nMitjana dir1 SortOrder");
        for (DataFrame d : list) {
            System.out.println(d.accept(new AverageVisitor(),"SortOrder"));
        }
        System.out.println("Mitjana dir2 LatD");
        for (DataFrame d : list2) {
            System.out.println(d.accept(new AverageVisitor(),"LatD"));
        }
    }
}