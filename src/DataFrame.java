import java.util.Comparator;
import java.util.function.Predicate;

public interface DataFrame {
	
	public Object at(String id, String label);
	
	public Object iat(int i, int j);
	
	public int columns();
	
	public int size();
	
	public void sort(Comparator<Object> c);
	
	public Object query(Predicate<Object> predicate);

}
