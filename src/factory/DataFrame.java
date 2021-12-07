package factory;

import java.util.*;
import java.util.function.Predicate;

public interface DataFrame extends Iterable<ArrayList<String>>{
	
	String at(int id, String label);
	
	String iat(int i, int j);
	
	int columns();
	
	int size();

	ArrayList<String> sort(String label, Comparator<Object> c);

	List<ArrayList<String>> query(String label, Predicate<String> predicate);

	Double max(String label);

	Double min(String label);

	Double average(String label);

	Double sum(String label);

}
