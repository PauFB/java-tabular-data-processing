import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONData implements DataFrame {
	
	LinkedList<String> labelList = new LinkedList<>();
	LinkedList<ArrayList<String>> content = new LinkedList<ArrayList<String>>();
	
	public JSONData(String file) {
		JSONParser parser = new JSONParser();

        try {
        	JSONArray array = (JSONArray) parser.parse(new FileReader(file));
            
            JSONObject jsonObject = (JSONObject) array.get(0);
            for (int i = 0; i < jsonObject.keySet().size(); i++) {
            	//labelList.set(i, (String)jsonObject.keySet().toArray()[i]);
				labelList.add((String)jsonObject.keySet().toArray()[i]);
				content.add(new ArrayList<String>());
            }
            
            
            for (int i = 0; i < array.size(); i++) {
            	 jsonObject = (JSONObject) array.get(i);

            	 for (int j = 0; j < jsonObject.size(); j++){
            	 	content.get(j).add(jsonObject.get(labelList.get(j)).toString());
				 }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
