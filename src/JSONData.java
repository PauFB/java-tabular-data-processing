import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONData implements DataFrame {
	
	LinkedList<String> labelList = new LinkedList<>();
	LinkedList<ArrayList<String>> content = new LinkedList<>();

	public JSONData(String file) {
		JSONParser parser = new JSONParser();

        try {

			ContainerFactory orderedKeyFactory = new ContainerFactory() {
				public List<String> creatArrayContainer() {
					return new LinkedList<>();
				}

				public Map<String, String> createObjectContainer() {
					return new LinkedHashMap<>();
				}
			};

        	LinkedList<HashMap<String, String>> array = (LinkedList<HashMap<String, String>>)parser.parse(new FileReader(file), orderedKeyFactory);

			HashMap<String, String> jsonObject = array.get(0);
            for (int i = 0; i < jsonObject.keySet().size(); i++) {
            	//labelList.set(i, (String)jsonObject.keySet().toArray()[i]);
				labelList.add((String)jsonObject.keySet().toArray()[i]);
				content.add(new ArrayList<>());
            }

			//for (int i = 0; i < array.size(); i++) {
			//obj: array.get(i)
            for (HashMap<String, String> obj : array) {
            	 jsonObject = obj;
            	 for (int j = 0; j < jsonObject.size(); j++) {
            	 	//content.get(j).add(jsonObject.get(labelList.get(j)).toString());
					 content.get(j).add(String.valueOf(jsonObject.get(labelList.get(j))));
				 }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
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
		temp.sort(c);

		return temp;
	}

	// parametre sera un metode duna interface,
	// el qual es pot substituir per una lambda
	@Override
	public List<ArrayList<String>> query(String label, Predicate<String> func) {
		int col = labelList.indexOf(label);

		List<String> col_filtrada = content.get(col).stream().filter(func).collect(Collectors.toList());

		LinkedList<ArrayList<String>> aux = new LinkedList<>();
		for (int k = 0; k < this.columns(); k++){
			aux.add(new ArrayList<>());
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
