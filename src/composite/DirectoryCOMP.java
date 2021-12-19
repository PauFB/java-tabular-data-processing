package composite;

import factory.*;
import visitor.*;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;

public class DirectoryCOMP implements DataFrame {

    private final String name;
    private final List<DataFrame> children;

    public DirectoryCOMP(String directoryPath) {
        name = directoryPath;
        children = new LinkedList<>();
        File directory = new File(directoryPath);
        for (File file : directory.listFiles()) {
            if (!file.isDirectory()) {
                children.add(new FileCOMP(file.getAbsolutePath()));
                continue;
            }
            children.add(new DirectoryCOMP(file.getAbsolutePath()));
        }
    }

    public List<DataFrame> getChildren(){
        return children;
    }

    public String getName(){
        return name;
    }

    public String at(int id, String label) {
        return null;
    }

    public String iat(int i, int j) {
        return null;
    }

    public int columns() {
        int result = 0;
        for (DataFrame child : this.children)
            result += child.columns();
        return result;
    }

    public int size() {
        int result = 0;
        for (DataFrame child : this.children)
            result += child.size();
        return result;
    }

    public ArrayList<String> sort(String label, Comparator<Object> c) {
        return null;
    }

    public DataFrame query(String label, Predicate<String> predicate) {
        DataFrame result = null;
        boolean firstHasBeenAdded = false;
        for (DataFrame child : this.children) {
            if (!firstHasBeenAdded) {
                if (child.query(label, predicate) != null){
                    result = child.query(label, predicate);
                    firstHasBeenAdded = true;
                }
            } else if (child.query(label, predicate) != null) {
                for (int i = 0; i < result.getContent().size(); i++) {
                    result.getContent().get(i).addAll(child.query(label, predicate).getContent().get(i));
                }
            }
        }
        return result;
    }

    public Double max(String label) {
        return null;
    }

    public Double min(String label) {
        return null;
    }

    public Double average(String label) {
        return null;
    }

    public Double sum(String label) {
        return null;
    }

    public LinkedList<ArrayList<String>> getContent() {
        LinkedList<ArrayList<String>> aux = new LinkedList<>();
        for (DataFrame child : this.children) {
            aux.addAll(child.getContent());
        }
        return aux;
    }

    public ArrayList<String> getColumn(String label) {
        return null;
    }

    public void accept(Visitor v, String label) {
        v.visit(this, label);
    }

    public Iterator<ArrayList<String>> iterator() {
        return children.get(0).iterator();
    }

    @Override
    public String toString() {
        StringBuilder aux = new StringBuilder();
        for (DataFrame child : children){
            aux.append(child.toString()).append("\n");
        }
        return aux.toString();
    }
}
