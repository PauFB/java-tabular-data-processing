import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CSVData implements DataFrame {

	LinkedList<String> labelList = new LinkedList<>();
	LinkedList<ArrayList<String>> content = new LinkedList<ArrayList<String>>();

	public CSVData(String fileName) {

		try {

			BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

			String row = fileReader.readLine();
			StringTokenizer st = new StringTokenizer(row, ",");

			// Llegir la capçalera
			while (st.hasMoreTokens()) {
				content.add(new ArrayList<String>());		//Per cada nova entrada crear una nova columna
				this.labelList.add(st.nextToken()); 		// i afegir a LabelList
			}

			// Llegir el contingut
			row = fileReader.readLine();
			while (row != null) {
				int i = 0;
				st = new StringTokenizer(row,",");

				while (st.hasMoreTokens()) {
					content.get(i).add(st.nextToken());		//Per cada nova entrada posar l'element
					i++;
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
	public String at(int id, String label) {
		int labelIndex = labelList.indexOf(label);

		return content.get(labelIndex).get(id);
	}

	@Override
	public String iat(int i, int j) {
		return this.content.get(j).get(i);
	}

	@Override
	public int columns() {
		return this.labelList.size();
	}

	@Override
	public int size() {
		return this.content.get(0).size();
	}

	@Override
	public ArrayList<String> sort(String label, Comparator<Object> c) {
		int labelIndex = labelList.indexOf(label);
		
		ArrayList<String> temp = content.get(labelIndex);
		Collections.sort(temp, c);
		
		return temp;
	}

	// parametre sera un metode duna interface,
	// el qual es pot substituir per una lambda
	@Override
	public <T> List<ArrayList<String>> query(String label, Predicate<String> func) {
		int col = labelList.indexOf(label);

		List<String> col_filtrada = content.get(col).stream().filter(func).collect(Collectors.toList());

		LinkedList<ArrayList<String>> aux = new LinkedList<ArrayList<String>>();
		for (int k = 0; k < this.columns(); k++){
			aux.add(new ArrayList<String>());
		}

		for (int j = 0; j < this.size(); j++){
			if (col_filtrada.contains(content.get(col).get(j))){
				for (int i = 0; i < this.columns(); i++){
					aux.get(i).add(content.get(i).get(j));
				}
			}
		}

		return aux;
	}

}
