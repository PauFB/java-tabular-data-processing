// import java.util.function.Predicate;

public class Main {

	public static void main(String[] args) {

		DataFrameFactory factory = new CSVDataFactory();
		DataFrame dataFrame = factory.readFile("DimenLookupAge8277.csv");

		// Predicate<Object> pred = DataFrame.getcontent().get(3) -> (DataFrame.getcontent().get(3) > 99);
		System.out.print(dataFrame.iat(0, 0));
		System.out.print("\n");
		System.out.print(dataFrame.size());
		
	}

}
