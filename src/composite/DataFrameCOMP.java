package composite;

import factory.DataFrame;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.Predicate;

public interface DataFrameCOMP {

    int columns();

    int size();

    DataFrameCOMP query(String label, Predicate<String> predicate);

    Double accept(Visitor v, String label);

    LinkedList<ArrayList<String>> getContent();

}
