package factory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface DataFrame {
	
	String at(int id, String label);
	
	String iat(int i, int j);
	
	int columns();
	
	int size();

	ArrayList<String> sort(String label, Comparator<Object> c);

	List<ArrayList<String>> query(String label, Predicate<String> predicate);

}
