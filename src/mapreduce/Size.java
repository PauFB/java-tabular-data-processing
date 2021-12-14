package mapreduce;

import factory.DataFrame;

import java.util.function.Function;


public class Size implements Function {

    @Override
    public Object apply(Object o) {
        return ((DataFrame)o).size();
    }

    /*
    public int apply(DataFrameCOMP dataFrame) {
        return dataFrame.size();
    }
    */

}
