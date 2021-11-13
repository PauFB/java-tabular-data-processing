import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		DataFrameFactory factory = new CSVDataFactory();
		DataFrame dataFrame = factory.readFile("DimenLookupAge8277.csv");
		
		DataFrameFactory factory2 = new JSONDataFactory();
		DataFrame dataFrame2 = factory2.readFile("cities.json");

		System.out.println("At (0, Code): " + dataFrame.at(0, "Code"));
		System.out.println("Size: " + dataFrame.size());
		System.out.println("Iat (0, 0): " + dataFrame.iat(0, 0));
		
		List<ArrayList<String>> d2 = dataFrame.query("Code", x -> Integer.parseInt(x) > 113);
		System.out.println("Code > 113: " + d2);

		dataFrame.sort("Code", new IntAscending());
		System.out.println("Primera columna despres de sort Code");
		System.out.println(dataFrame.iat(0, 0));
		System.out.println(dataFrame.iat(1, 0));
		System.out.println(dataFrame.iat(2, 0));

	}

}
