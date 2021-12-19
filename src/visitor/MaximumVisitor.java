package visitor;

import composite.*;
import factory.DataFrame;

public class MaximumVisitor implements Visitor {

    private Double result = Double.MIN_VALUE;

    public void visit(DirectoryCOMP d, String label) {
        for (DataFrame child : d.getChildren()) {
            if (child instanceof DirectoryCOMP) {
                visit((DirectoryCOMP) child, label);
            } else if (child instanceof FileCOMP){
                visit((FileCOMP) child, label);
            }
        }
    }

    public void visit(FileCOMP f, String label) {
        Double maxValue = f.max(label);
        if (maxValue != null && maxValue > result){
            result = maxValue;
        }
    }

    public Double getResult() {
        return result;
    }

}
