package mapreduce;

import factory.DataFrame;

import java.util.function.Function;

public class Columns implements Function {

    @Override
    public Object apply(Object o) {
        return ((DataFrame)o).columns();
    }
}
