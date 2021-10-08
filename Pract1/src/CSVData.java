import java.io.*;
import java.util.*;

public class CSVData implements DataFrame {

	public CSVData(String fileName) {
		try {
			LinkedList<ArrayList<Object>> content = new LinkedList<ArrayList<Object>>();
			BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
			
			String row = fileReader.readLine();
			StringTokenizer st = new StringTokenizer(row,",");
			
			//Llegir capçalera
			while (st.hasMoreTokens()) {
				content.add(new ArrayList<Object>());		//Per cada nova entrada crear una nova columna
				content.getLast().add(st.nextElement());	// i posar l'element
			}
			
			//Llegir contingut
			while (row != null) {
				int i = 0;
				st = new StringTokenizer(row,",");
				
				while (st.hasMoreTokens()) {
					content.get(i).add(st.nextElement());		//Per cada nova entrada posar l'element
					i++;
				}				
				row = fileReader.readLine();
			}
			
			fileReader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("El fitxer d'entrada no existeix");
		} catch (IOException e) {
			System.out.println("Excepcio d'E/S: "+e.toString());
		}
	}
	
	@Override
	public Object at() {
		// TODO Auto-generated method stub
		return null;
	}

}
