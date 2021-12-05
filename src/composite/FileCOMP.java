package composite;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileCOMP implements DataFrameCOMP {

    LinkedList<String> labelList = new LinkedList<>();
    LinkedList<ArrayList<String>> content = new LinkedList<>();

    public FileCOMP(String filePath) {
        CsvParserSettings settings = new CsvParserSettings();
        settings.detectFormatAutomatically();

        CsvParser parser = new CsvParser(settings);
        List<String[]> rows = parser.parseAll(new File(filePath));

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
    public int columns() {
        return this.labelList.size();
    }

    @Override
    public int size() {
        return this.content.get(0).size();
    }

}
