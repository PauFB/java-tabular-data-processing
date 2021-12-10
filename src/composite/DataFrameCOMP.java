package composite;

import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public interface DataFrameCOMP {

    int columns();

    int size();

    List<ArrayList<String>> query(String label, Predicate<String> predicate);

    Double accept(Visitor v, String label);

}
