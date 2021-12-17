package mapreduce;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import factory.DataFrame;

import javax.xml.crypto.Data;

public class MapReduce {

    /*
    public static List<Integer> map(List<DataFrame> list, Function function) {
        return (List<Integer>) list.parallelStream().map(function).collect(Collectors.toList());
    }

    public static List<DataFrame> map(List<DataFrame> list, Function function) {
        return (List<DataFrame>) list.parallelStream().map(function).collect(Collectors.toList();
    }
    */

    public static<T> List<T> map(List<DataFrame> list, Function function) {
        return (List<T>) list.parallelStream().map(function).collect(Collectors.toList());
    }

    public static<T> T reduce(List<T> list) {
        T objecte = list.get(0);
        if (objecte instanceof Integer) {
            Integer suma = 0;
            for (T elem : list) {
                suma += (Integer) elem;
            }
            return (T) Double.valueOf(suma / list.size());
        }
        if (objecte instanceof DataFrame) {
            DataFrame result = null;
            Boolean firstHasBeenAdded = false;
            for (T child : list) {
                if (!firstHasBeenAdded) {
                    if ((DataFrame) child != null){
                        result = (DataFrame) child /*.clone()*/;
                        firstHasBeenAdded = true;
                    }
                } else if ((DataFrame) child != null) {
                    for (int i = 0; i < result.getContent().size(); i++) {
                        result.getContent().get(i).addAll(((DataFrame)child).getContent().get(i));
                    }
                }
            }
            return (T) result;
        }
        return null;
    }

    /*
    public LinkedList<ArrayList<String>> reduce(List<LinkedList<ArrayList<String>>> values) {
        return null;
    }
     */

}
