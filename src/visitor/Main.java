package visitor;

import composite.DataFrameCOMP;
import composite.DirectoryCOMP;
import composite.FileCOMP;

import java.util.Arrays;
import java.util.List;

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

        for (DataFrameCOMP d : list) {
            System.out.println(d.accept(new AverageVisitor(),"SortOrder"));
        }
    }
}