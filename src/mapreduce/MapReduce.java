package mapreduce;

import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import factory.DataFrame;


public class MapReduce {

    public static List<Integer> map(List<DataFrame> list, Function function) {
        return (List<Integer>) list.parallelStream().map(function).collect(Collectors.toList());

        //for (DataFrameCOMP elem : list)
            //result.add(Double.valueOf(function.apply(elem)));
        //return result;
    }

    public static Double reduce(List<Integer> values) {
        Double suma = 0.0;
        for (Integer elem : values) {
            suma += elem;
        }
        return suma / values.size();
    }

    /*
    public LinkedList<ArrayList<String>> reduce(List<LinkedList<ArrayList<String>>> values) {
        return null;
    }
     */

}
