package composite;

import factory.*;

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

    public int columns() {
        return dataFrame.columns();
    }

    public int size() {
        return dataFrame.size();
    }

}
