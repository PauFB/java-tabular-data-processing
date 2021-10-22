import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface DataFrame {
	
	public String at(int id, String label);
	
	public String iat(int i, int j);
	
	public int columns();
	
	public int size();
	
	public ArrayList<String> sort(String label, Comparator<Object> c);
	
	public <T> List<ArrayList<String>> query(Predicate<ArrayList<String>> predicate);

}
