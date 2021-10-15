import java.util.ArrayList;
import java.util.List;

// import java.util.function.Predicate;

public class Main {

	public static void main(String[] args) {

		DataFrameFactory factory = new CSVDataFactory();
		DataFrame dataFrame = factory.readFile("DimenLookupAge8277.csv");

		// Predicate<Object> pred = DataFrame.getcontent().get(3) -> (DataFrame.getcontent().get(3) > 99);
		System.out.println(dataFrame.iat(0, 0));
		System.out.println(dataFrame.size());
		
		dataFrame.sort(new IntComparator());
		System.out.println(dataFrame.iat(0, 0));
		System.out.println(dataFrame.iat(1, 0));
		System.out.println(dataFrame.iat(2, 0));
		System.out.println(dataFrame.iat(3, 0));
		
		List<ArrayList<Object>> d2 = (List<ArrayList<Object>>) dataFrame.query(new AgePredicate<Object>());
	}

}
