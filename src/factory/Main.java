package factory;

import java.util.*;

public class Main {

	public static void main(String[] args) {

		System.out.println("****	Prova CSV	****");
		DataFrameFactory factory = new CSVDataFactory();
		DataFrame dataFrame = factory.readFile("DimenLookupAge8277.csv");

		System.out.println("At (0, Code): " + dataFrame.at(0, "Code"));
		System.out.println("Iat (0, 0): " + dataFrame.iat(0, 0));
		System.out.println("Columns: " + dataFrame.columns());
		System.out.println("Size: " + dataFrame.size());
		ArrayList<String> CSVsort = dataFrame.sort("Code", new IntAscending());
		System.out.println("Primera columna despres de sort Code ascendent: " + CSVsort);
		DataFrame CSVquery = dataFrame.query("Code", x -> Integer.parseInt(x) > 113);
		System.out.println("Code > 113: " + CSVquery.getContent() + "\n");


		System.out.println("****	Prova JSON	****");
		DataFrameFactory factory2 = new JSONDataFactory();
		DataFrame dataFrame2 = factory2.readFile("cities.json");

		System.out.println("At (0, LatD): " + dataFrame2.at(0, "LatD"));
		System.out.println("Iat (0, 0): " + dataFrame2.iat(0, 0));
		System.out.println("Columns: " + dataFrame2.columns());
		System.out.println("Size: " + dataFrame2.size());
		ArrayList<String> JSONsort = dataFrame2.sort("LatD", new IntAscending());
		System.out.println("Primera columna despres de sort LatD ascendent: " + JSONsort);
		DataFrame JSONquery = dataFrame2.query("LatD", x -> Integer.parseInt(x) < 30);
		System.out.println("LatD < 30: " + JSONquery.getContent() + "\n");

		System.out.println("****	Prova TXT	****");
		DataFrameFactory factory3 = new TXTDataFactory();
		DataFrame dataFrame3 = factory3.readFile("example.txt");

		System.out.println("At (0, Code): " + dataFrame3.at(0, "Code"));
		System.out.println("Iat (0, 0): " + dataFrame3.iat(0, 0));
		System.out.println("Columns: " + dataFrame3.columns());
		System.out.println("Size: " + dataFrame3.size());
		ArrayList<String> TXTsort = dataFrame3.sort("Code", new IntAscending());
		System.out.println("Primera columna despres de sort Code ascendent: " + TXTsort);
		DataFrame TXTquery = dataFrame3.query("Code", x -> Integer.parseInt(x) > 113);
		System.out.println("Code > 113: " + TXTquery.getContent() + "\n");
	}

}
