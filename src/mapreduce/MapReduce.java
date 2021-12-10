package mapreduce;

import composite.DataFrameCOMP;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public class MapReduce {

    public static List<Double> map(List<DataFrameCOMP> list, IMap function) {
        List<Double> result = new LinkedList<>();
        for (DataFrameCOMP elem : list)
            result.add(Double.valueOf(function.apply(elem)));
        return result;
    }

    public static Double reduce(Double identity, List<Double> values, BinaryOperator<Double> function) {
        return values.stream().reduce(identity, function);
    }

}
