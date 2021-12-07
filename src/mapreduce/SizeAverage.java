package mapreduce;

import composite.DataFrameCOMP;

public class SizeAverage implements IMap {

    public int apply(DataFrameCOMP dataFrame) {
        return dataFrame.size();
    }

}
