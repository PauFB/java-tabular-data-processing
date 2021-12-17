package composite;

import factory.*;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

public class FileCOMP implements DataFrame {

    DataFrame dataFrame;

    public FileCOMP(String filePath) {
        if (filePath.contains(".csv")) {
            dataFrame = new CSVData(filePath);
        } else if(filePath.contains(".json")){
            dataFrame = new JSONData(filePath);
        } else if(filePath.contains(".txt")){
            dataFrame = new TXTData(filePath);
        }
    }

    public ArrayList<String> getColumn(String label) {
        return dataFrame.getColumn(label);
    }

    public DataFrame getDataFrame() {
        return dataFrame;
    }

    @Override
    public String at(int id, String label) {
        return dataFrame.at(id, label);
    }

    @Override
    public String iat(int i, int j) {
        return dataFrame.iat(i,j);
    }

    public int columns() {
        return dataFrame.columns();
    }

    public int size() {
        return dataFrame.size();
    }

    @Override
    public ArrayList<String> sort(String label, Comparator<Object> c) {
        return dataFrame.sort(label, c);
    }

    public DataFrame query(String label, Predicate<String> predicate) {
        return dataFrame.query(label, predicate);
    }

    @Override
    public Double max(String label) {
        return dataFrame.max(label);
    }

    @Override
    public Double min(String label) {
        return dataFrame.min(label);
    }

    @Override
    public Double average(String label) {
        return dataFrame.average(label);
    }

    @Override
    public Double sum(String label) {
        return dataFrame.sum(label);
    }

    public LinkedList<ArrayList<String>> getContent(){
        return dataFrame.getContent();
    }

    public void accept(Visitor v, String label) {
        v.visit(this, label);
    }

    @Override
    public Iterator<ArrayList<String>> iterator() {
        return dataFrame.iterator();
    }
}
