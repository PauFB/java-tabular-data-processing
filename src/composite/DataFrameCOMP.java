package composite;

public interface DataFrameCOMP {

    int columns();

    int size();

    void accept(Visitor v);

}
