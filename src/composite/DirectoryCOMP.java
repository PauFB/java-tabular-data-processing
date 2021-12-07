package composite;

import java.io.File;
import java.util.*;

public class DirectoryCOMP implements DataFrameCOMP {

    private String name;
    private List<DataFrameCOMP> children;

    /*
    public DirectoryCOMP(String directoryPath) {
        this.name = directoryPath;
        this.children = new LinkedList<>();
        File directory = new File(directoryPath);
        for (File file : directory.listFiles()) {
            if (!file.isDirectory())
                this.children.add(new FileCOMP(file.getAbsolutePath()));
            // TODO
            // afegir els subdirectoris a la llista de fills
        }
    }
    */

    public DirectoryCOMP(String name) {
        this.name = name;
        children = new LinkedList<>();
    }

    public void addChild(DataFrameCOMP child) {
        children.add(child);
    }

    public int columns() {
        int result = 0;
        for (DataFrameCOMP child : this.children)
            result += child.columns();
        return result;
    }

    public int size() {
        int result = 0;
        for (DataFrameCOMP child : this.children)
            result += child.size();
        return result;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

}
