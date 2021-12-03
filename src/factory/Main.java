package factory;

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




		System.out.println("At (0, LatD): " + dataFrame2.at(0, "LatD"));
		System.out.println("Size: " + dataFrame2.size());
		System.out.println("Iat (0, 0): " + dataFrame2.iat(0, 0));

		List<ArrayList<String>> d3 = dataFrame2.query("LatD", x -> Integer.parseInt(x) < 30);
		System.out.println("LatD < 30: " + d3);

		dataFrame2.sort("LatD", new IntAscending());
		System.out.println("Primera columna despres de sort LatD");
		System.out.println(dataFrame2.iat(0, 0));
		System.out.println(dataFrame2.iat(1, 0));
		System.out.println(dataFrame2.iat(2, 0));
		System.out.println(dataFrame2.iat(3, 0));

		DataFrameFactory factory3 = new TXTDataFactory();
		DataFrame dataFrame3 = factory3.readFile("example.txt");

		System.out.println("At (0, Code): " + dataFrame3.at(0, "Code"));
		System.out.println("Size: " + dataFrame3.size());
		System.out.println("Iat (0, 0): " + dataFrame3.iat(0, 0));

		List<ArrayList<String>> d4 = dataFrame3.query("Code", x -> Integer.parseInt(x) > 113);
		System.out.println("Code > 113: " + d4);

		dataFrame3.sort("Code", new IntAscending());
		System.out.println("Primera columna despres de sort Code");
		System.out.println(dataFrame3.iat(0, 0));
		System.out.println(dataFrame3.iat(1, 0));
		System.out.println(dataFrame3.iat(2, 0));

	}

}