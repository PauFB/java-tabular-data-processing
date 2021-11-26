import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class TXTData implements DataFrame {
    @Override
    public String at(int id, String label) {
        return null;
    }

    @Override
    public String iat(int i, int j) {
        return null;
    }

    @Override
    public int columns() {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public ArrayList<String> sort(String label, Comparator<Object> c) {
        return null;
    }

    @Override
    public List<ArrayList<String>> query(String label, Predicate<String> predicate) {
        return null;
    }
}
