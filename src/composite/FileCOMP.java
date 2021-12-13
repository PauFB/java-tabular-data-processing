package composite;

import factory.*;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FileCOMP implements DataFrameCOMP {

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

    public ArrayList<String> getContent(String label) {
        return dataFrame.getContent(label);
    }

    public DataFrame getDataFrame() {
        return dataFrame;
    }

    public int columns() {
        return dataFrame.columns();
    }

    public int size() {
        return dataFrame.size();
    }

    public List<ArrayList<String>> query(String label, Predicate<String> predicate) {
        return dataFrame.query(label, predicate);
    }

    public Double accept(Visitor v, String label) {
        return v.visit(this, label);
    }

}
