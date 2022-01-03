package composite;

import factory.*;
import visitor.*;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;

public class DirectoryCOMP implements DataFrame {

    private Data data;

    public DirectoryCOMP(String directoryPath) {
        name = directoryPath;
        children = new LinkedList<>();
        File directory = new File(directoryPath);
        FileCOMP f;
        DirectoryCOMP d;
        File[] files = directory.listFiles();

        try {
            if (!files[0].isDirectory()) {
                f = new FileCOMP(files[0].getAbsolutePath());
                labelList = (LinkedList<String>) f.getLabelList().clone();
            } else {
                d = new DirectoryCOMP(files[0].getAbsolutePath());
                labelList = (LinkedList<String>) d.getLabelList().clone();
            }

            for (String label : labelList)
                content.add(new ArrayList<>());

            for (File file : directory.listFiles()) {
                if (!file.isDirectory()) {
                    f = new FileCOMP(file.getAbsolutePath());
                    for (int i = 0; i < labelList.size(); i++) {
                        content.get(i).addAll(f.getContent().get(i));
                    }
                } else {
                    d = new DirectoryCOMP(file.getAbsolutePath());
                    for (int i = 0; i < labelList.size(); i++) {
                        content.get(i).addAll(d.getContent().get(i));
                    }
                }
            }
            this.data = new Data(labelList, content);
        } catch (Exception e) {
            this.data = new Data();
        }
    }

    public String at(int id, String label) {
        return this.data.at(id, label);
    }

    public String iat(int i, int j) {
        return null;
    }

    public int columns() {
        return this.data.columns();
    }

    public LinkedList<String> getLabelList() {
        return this.data.getLabelList();
    }

    public int size() {
        return this.data.size();
    }

    public ArrayList<String> sort(String label, Comparator<Object> c) {
        return this.data.sort(label, c);
    }

    public Data query(String label, Predicate<String> predicate) {
        return this.data.query(label, predicate);
    }

    public Double max(String label) {
        double maxValue = Double.MIN_VALUE;
        for (DataFrame child : children){
            if (child.max(label) != null){
                maxValue = Math.max(child.max(label), maxValue);
            }
        }
        return maxValue;
    }

    public Double min(String label) {
        double minValue = Double.MAX_VALUE;
        for (DataFrame child : children) {
            if (child.min(label) != null) {
                minValue = Math.min(child.min(label), minValue);
            }
        }
        return minValue;
    }

    public Double average(String label) {
        ArrayList<String> list;
        double accumulator = 0.0;
        int nElements = 0;
        for (DataFrame child : children) {
            list = child.getColumn(label);
            if (list != null) {
                for (String value : list) {
                    accumulator += Integer.parseInt(value);
                    nElements++;
                }
            }
        }
        if (nElements != 0){
            return accumulator/nElements;
        }
        return null;
    }

    public Double sum(String label) {
        Double sum = 0.0;
        for (DataFrame child : children) {
            if (child.sum(label) != null){
                sum += child.sum(label);
            }
        }
        return sum;
    }

    public LinkedList<ArrayList<String>> getContent() {
        LinkedList<ArrayList<String>> aux = new LinkedList<>();
        for (DataFrame child : this.children) {
            aux.addAll(child.getContent());
        }
        return aux;
    }

    public ArrayList<String> getColumn(String label) {
        return this.data.getColumn(label);
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
