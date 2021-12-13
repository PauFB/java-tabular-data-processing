package mapreduce;

import composite.DataFrameCOMP;
import factory.DataFrame;

import java.util.function.Function;

public class SizeAverage implements Function {

    @Override
    public Object apply(Object o) {
        return ((DataFrameCOMP) o).size();
    }

    /*
    public int apply(DataFrameCOMP dataFrame) {
        return dataFrame.size();
    }
    */

}
