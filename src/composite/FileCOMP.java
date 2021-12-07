package composite;

import factory.*;
import visitor.Visitor;

public class FileCOMP implements DataFrameCOMP {

    DataFrame dataFrame;

    public FileCOMP(String filePath) {
        if (filePath.contains(".csv")){
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

    public int columns() {
        return dataFrame.columns();
    }

    public int size() {
        return dataFrame.size();
    }

    public Double accept(Visitor v, String label) {
        return v.visit(this, label);
    }

}
