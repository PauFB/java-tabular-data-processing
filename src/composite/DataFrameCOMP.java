package composite;

import visitor.Visitor;

public interface DataFrameCOMP {

    int columns();

    int size();

    void accept(Visitor v);

}
