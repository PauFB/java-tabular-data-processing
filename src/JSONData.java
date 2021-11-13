import java.io.*;
import java.util.*;
import java.util.function.Predicate;
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
            }
            
            
            for (int i = 0; i < array.size(); i++) {
            	 jsonObject = (JSONObject) array.get(i);
            }
            //jsonObject = (JSONObject) array.get(0);

            /*String name = (String) jsonObject.get("name");
            System.out.println(name);

            String city = (String) jsonObject.get("city");
            System.out.println(city);

            String job = (String) jsonObject.get("job");
            System.out.println(job);

            // loop array
            JSONArray cars = (JSONArray) jsonObject.get("cars");
            Iterator<String> iterator = cars.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }*/
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String iat(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int columns() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<String> sort(String label, Comparator<Object> c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<ArrayList<String>> query(String label, Predicate<String> predicate) {
		// TODO Auto-generated method stub
		return null;
	}

}
