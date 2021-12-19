package visitor;

import composite.*;
import factory.DataFrame;

public class MinimumVisitor implements Visitor {

    private Double result = Double.MAX_VALUE;

    public void visit(DirectoryCOMP d, String label) {
        for (DataFrame child : d.getChildren()) {
            if (child instanceof DirectoryCOMP){
                visit((DirectoryCOMP) child, label);
            } else if (child instanceof FileCOMP){
                visit((FileCOMP) child, label);
            }
        }
    }

    public void visit(FileCOMP f, String label) {
        Double minValue = f.min(label);
        if (minValue != null && minValue < result){
            result = minValue;
        }
    }

    @Override
    public Double getResult() {
        return result;
    }
}
