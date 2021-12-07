package composite;

import visitor.Visitor;

public interface DataFrameCOMP {

    int columns();

    int size();

    Double accept(Visitor v, String label);

}
