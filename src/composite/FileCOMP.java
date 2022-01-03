package composite;

import factory.*;
import visitor.*;

import java.util.*;
import java.util.function.Predicate;

public class FileCOMP implements DataFrame {

    Data data;

    public FileCOMP(String filePath) {
        if (filePath.contains(".csv")) {
            dataFrame = new CSVData(filePath);
        } else if(filePath.contains(".json")){
            dataFrame = new JSONData(filePath);
        } else if(filePath.contains(".txt")){
            dataFrame = new TXTData(filePath);
        }
    }

    public DataFrame getDataFrame() {
        return dataFrame;
    }

    public LinkedList<String> getLabelList() {
        return this.data.getLabelList();
    }

    public String at(int id, String label) {
        return data.at(id, label);
    }

    public String iat(int i, int j) {
        return data.iat(i,j);
    }

    public int columns() {
        return data.columns();
    }

    public int size() {
        return data.size();
    }

    public ArrayList<String> sort(String label, Comparator<Object> c) {
        return data.sort(label, c);
    }

    public Data query(String label, Predicate<String> predicate) {
        return data.query(label, predicate);
    }

    public Double max(String label) {
        return data.max(label);
    }

    public Double min(String label) {
        return data.min(label);
    }

    public Double average(String label) {
        return data.average(label);
    }

    public Double sum(String label) {
        return data.sum(label);
    }

    public LinkedList<ArrayList<String>> getContent(){
        return data.getContent();
    }

    public ArrayList<String> getColumn(String label) {
        return data.getColumn(label);
    }

    public void accept(Visitor v, String label) {
        v.visit(this, label);
    }

    public Iterator<ArrayList<String>> iterator() {
        return data.iterator();
    }

    public String toString() {
        return data.toString();
    }
}
