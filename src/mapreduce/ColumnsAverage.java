package mapreduce;

import composite.DataFrameCOMP;

public class ColumnsAverage implements IMap {

    public int apply(DataFrameCOMP dataFrame) {
        return dataFrame.columns();
    }

}
