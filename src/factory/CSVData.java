package factory;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CSVData implements DataFrame {

	LinkedList<String> labelList = new LinkedList<>();
	LinkedList<ArrayList<String>> content = new LinkedList<>();

	public LinkedList<ArrayList<String>> getContent() {
		return content;
	}

	public CSVData(LinkedList<String> labelList, LinkedList<ArrayList<String>> content) {
		this.labelList = labelList;
		this.content = content;
	}

	public CSVData(String fileName) {

		try {

			BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

			String row = fileReader.readLine();
			StringTokenizer st = new StringTokenizer(row, ",");

			// Llegir la capçalera
			while (st.hasMoreTokens()) {
				content.add(new ArrayList<>());		//Per cada nova entrada crear una nova columna
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
			System.out.println("Excepcio d'E/S: " + e);
		}
	}

	public ArrayList<String> getContent(String label) {
		int labelIndex = this.labelList.indexOf(label);
		if (labelIndex != -1) {
			return this.content.get(labelIndex);
		}
		return null;
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

		if (labelIndex != -1){
			ArrayList<String> temp = (ArrayList<String>) content.get(labelIndex).clone();
			temp.sort(c);
			return temp;
		}
		return null;
	}

	@Override
	public DataFrame query(String label, Predicate<String> func) {
		int col = labelList.indexOf(label);

		if (col != -1) {
			List<String> filtered_column = content.get(col).stream().filter(func).collect(Collectors.toList());

			LinkedList<ArrayList<String>> aux = new LinkedList<>();
			for (int k = 0; k < this.columns(); k++){
				aux.add(new ArrayList<>());
			}

			for (int j = 0; j < this.size(); j++){
				if (filtered_column.contains(content.get(col).get(j))){
					for (int i = 0; i < this.columns(); i++){
						aux.get(i).add(content.get(i).get(j));
					}
				}
			}

			return new CSVData(labelList, aux);
		}
		return null;
	}

	@Override
	public Double max(String label) {
		double max = Integer.MIN_VALUE;
		int labelIndex = labelList.indexOf(label);

		if (labelIndex != -1){
			ArrayList<String> temp = (ArrayList<String>) content.get(labelIndex).clone();
			try {
				for (String elem : temp){
					if (Integer.parseInt(elem) > max){
						max = Integer.parseInt(elem);
					}
				}
				return max;
			} catch (NumberFormatException e) {
				System.out.println(e);
				return null;
			}
		}
		return null;
	}

	@Override
	public Double min(String label) {
		double min = Integer.MAX_VALUE;
		int labelIndex = labelList.indexOf(label);

		if (labelIndex != -1){
			ArrayList<String> temp = (ArrayList<String>) content.get(labelIndex).clone();
			try{
				for (String elem : temp){
					if (Integer.parseInt(elem) < min) {
						min = Integer.parseInt(elem);
					}
				}
				return min;
			} catch (NumberFormatException e){
				System.out.println(e);
				return null;
			}
		}
		return null;
	}

	@Override
	public Double average(String label) {
		double avg = 0;
		int labelIndex = labelList.indexOf(label);

		if (labelIndex != -1){
			ArrayList<String> temp = (ArrayList<String>) content.get(labelIndex).clone();
			try{
				for (String elem : temp){
					avg += Integer.parseInt(elem);
				}
				return avg/temp.size();
			} catch (NumberFormatException e){
				System.out.println(e);
				return null;
			}
		}
		return null;
	}

	@Override
	public Double sum(String label) {
		double sum = 0;
		int labelIndex = labelList.indexOf(label);

		if (labelIndex != -1){
			ArrayList<String> temp = (ArrayList<String>) content.get(labelIndex).clone();
			try{
				for (String elem : temp){
					sum += Integer.parseInt(elem);
				}
				return sum;
			} catch (NumberFormatException e){
				System.out.println(e);
				return null;
			}
		}
		return null;
	}

	@Override
	public Iterator<ArrayList<String>> iterator() {
		return content.iterator();
	}
}
