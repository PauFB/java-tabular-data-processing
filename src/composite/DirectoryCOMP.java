package composite;

import visitor.Visitor;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;

public class DirectoryCOMP implements DataFrameCOMP {

    private String name;
    private List<DataFrameCOMP> children;

    public DirectoryCOMP(String directoryPath) {
        this.name = directoryPath;
        this.children = new LinkedList<>();
        File directory = new File(directoryPath);
        for (File file : directory.listFiles()) {
            if (!file.isDirectory()) {
                this.children.add(new FileCOMP(file.getAbsolutePath()));
                continue;
            }
            this.children.add(new DirectoryCOMP(file.getAbsolutePath()));
        }
    }

    public void addChild(DataFrameCOMP child) {
        children.add(child);
    }

    public List<DataFrameCOMP> getChildren(){
        return children;
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

    public DataFrameCOMP query(String label, Predicate<String> predicate) {
        DataFrameCOMP result = null;
        Boolean firstHasBeenAdded = false;
        for (DataFrameCOMP child : this.children) {
            if (!firstHasBeenAdded && child instanceof FileCOMP) {
                result = ((FileCOMP) child).clone();
                firstHasBeenAdded = true;
            } else {
                result.getContent().addAll(child.query(label, predicate).getContent());
            }
        }
        return result;
    }

    public Double accept(Visitor v, String label) {
        return v.visit(this, label);
    }

}
