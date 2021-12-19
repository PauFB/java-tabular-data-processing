package factory;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import visitor.Visitor;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;

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

    public LinkedList<ArrayList<String>> getContent() {
        return content;
    }

    public TXTData(LinkedList<String> labelList, LinkedList<ArrayList<String>> content) {
        this.labelList = labelList;
        this.content = content;
    }

    public ArrayList<String> getColumn(String label) {
        int labelIndex = this.labelList.indexOf(label);
        if (labelIndex != -1) {
            return this.content.get(labelIndex);
        }
        return null;
    }

    @Override
    public void accept(Visitor v, String label) {}

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

        if (col != -1){
            List<String> filtered_column = content.get(col).stream().filter(func).toList();

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

            return new TXTData(labelList, aux);
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

        if (labelIndex != -1) {
            ArrayList<String> temp = (ArrayList<String>) content.get(labelIndex).clone();
            try {
                for (String elem : temp){
                    sum += Integer.parseInt(elem);
                }
                return sum;
            } catch (NumberFormatException e) {
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

    @Override
    public String toString() {
        StringBuilder aux = new StringBuilder();
        int i, j, n_blanks;
        for (String label : labelList){
            aux.append(label);
            n_blanks = 32 - label.length();
            for (j = 0; (n_blanks - j) > 4; j += 4){
                aux.append("\t");
            }
        }
        aux.append("\n");
        for (i = 0; i < size(); i++){
            for (ArrayList<String> col : content){
                aux.append(col.get(i));
                n_blanks = 32 - col.get(i).length();
                for (j = 0; (n_blanks - j) > 4; j += 4){
                    aux.append("\t");
                }
            }
            aux.append("\n");
        }
        return aux.toString();
    }
}
