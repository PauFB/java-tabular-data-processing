package composite;

import factory.DataFrame;
import visitor.Visitor;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;

public class DirectoryCOMP implements DataFrame {

    private String name;
    private List<DataFrame> children;

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

    public List<DataFrame> getChildren(){
        return children;
    }

    @Override
    public String at(int id, String label) {
        return null;
    }

    @Override
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

    @Override
    public ArrayList<String> sort(String label, Comparator<Object> c) {
        return null;
    }

    public DataFrame query(String label, Predicate<String> predicate) {
        DataFrame result = null;
        Boolean firstHasBeenAdded = false;
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

    @Override
    public Double max(String label) {
        return null;
    }

    @Override
    public Double min(String label) {
        return null;
    }

    @Override
    public Double average(String label) {
        return null;
    }

    @Override
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

    @Override
    public ArrayList<String> getColumn(String label) {
        return null;
    }

    public void accept(Visitor v, String label) {
        v.visit(this, label);
    }

    @Override
    public Iterator<ArrayList<String>> iterator() {
        return null;
    }
}
