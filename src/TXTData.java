import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TXTData implements DataFrame {

    LinkedList<String> labelList = new LinkedList<>();
    LinkedList<ArrayList<String>> content = new LinkedList<>();

    public TXTData(String fileName) {
        CsvParserSettings settings = new CsvParserSettings();
        settings.detectFormatAutomatically();

        CsvParser parser = new CsvParser(settings);
        List<String[]> rows = parser.parseAll(new File(fileName));

        for (String label : rows.get(0)) {
            labelList.add(label);
            this.content.add(new ArrayList<>());
        }

        for (int i = 1; i < rows.size(); i++) {
            String[] line = rows.get(i);
            for (int j = 0; j < line.length; j++) {
                this.content.get(j).add(line[j]);
            }
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
