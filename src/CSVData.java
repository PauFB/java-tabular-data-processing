import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CSVData implements DataFrame {

	LinkedList<Object> labelList = new LinkedList<>();
	LinkedList<ArrayList<Object>> content = new LinkedList<ArrayList<Object>>();

	public CSVData(String fileName) {

		try {

			BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

			String row = fileReader.readLine();
			StringTokenizer st = new StringTokenizer(row, ",");

			// Llegir la capçalera
			while (st.hasMoreTokens()) {
				this.labelList.add(st.nextElement()); // i posar l'element
			}

			// Llegir el contingut
			row = fileReader.readLine();
			while (row != null) {
				this.content.add(new ArrayList<Object>()); // Per cada nova entrada crear una nova columna
				st = new StringTokenizer(row, ",");

				while (st.hasMoreTokens()) {
					this.content.getLast().add(st.nextElement()); // Per cada nova entrada posar l'element
				}
				row = fileReader.readLine();
			}

			fileReader.close();

		} catch (FileNotFoundException e) {
			System.out.println("El fitxer d'entrada no existeix");
		} catch (IOException e) {
			System.out.println("Excepcio d'E/S: " + e.toString());
		}
	}

	@Override
	public Object at(String id, String label) {

		int labelIndex = labelList.indexOf(label);
		int index = content.get(0).indexOf(id);

		return content.get(labelIndex).get(index);
	}

	@Override
	public Object iat(int i, int j) {
		return this.content.get(i).get(j);
	}

	@Override
	public int columns() {
		return this.labelList.size();
	}

	@Override
	public int size() {
		return this.content.size();
	}

	@Override
	public void sort(Comparator<ArrayList<Object>> c) {
		Collections.sort(content, c);
	}

	@Override
	public List<ArrayList<Object>> query(Predicate<ArrayList<Object>> predicate) {
		// int labelIndex = labelList.indexOf(label);
		return content.stream().filter(predicate).collect(Collectors.toList());
		
	}

}
