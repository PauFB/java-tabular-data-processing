package mapreduce;

import composite.DataFrameCOMP;

import java.util.LinkedList;
import java.util.List;

public class Map {

    public static List<Integer> map(List<DataFrameCOMP> list, IMap function) {
        List<Integer> result = new LinkedList<>();
        for (DataFrameCOMP elem : list)
            result.add(function.apply(elem));
        return result;
    }

}
