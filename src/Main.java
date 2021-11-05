import java.util.ArrayList;
import java.util.List;

// import java.util.function.Predicate;

public class Main {

	public static void main(String[] args) {

		DataFrameFactory factory = new CSVDataFactory();
		DataFrame dataFrame = factory.readFile("DimenLookupAge8277.csv");
		
		DataFrameFactory factory2 = new JSONDataFactory();
		DataFrame dataFrame2 = factory2.readFile("cities.json");

		System.out.println(dataFrame.at(0, "Code"));
		System.out.println(dataFrame.size());
		System.out.println(dataFrame.iat(0, 0));
		System.out.println(dataFrame.iat(1, 0));
		System.out.println(dataFrame.iat(2, 0));
		
		System.out.println();
		dataFrame.sort("Code", new IntAscending());
		System.out.println(dataFrame.iat(0, 0));
		System.out.println(dataFrame.iat(0, 1));
		System.out.println(dataFrame.iat(0, 2));
		
		//List<ArrayList<String>> d2 = (List<ArrayList<String>>) dataFrame.query(x -> x.get(0));
	}

}
