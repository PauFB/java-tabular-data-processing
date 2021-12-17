package mapreduce;

import factory.DataFrame;

import java.util.function.Function;
import java.util.function.Predicate;

public class Query implements Function {

    String label;
    Predicate<String> predicate;

    public Query(String label, Predicate<String> predicate) {
        this.label = label;
        this.predicate = predicate;
    }

    @Override
    public Object apply(Object o) {
        return ((DataFrame) o).query(this.label, this.predicate);
    }

}
