package visitor;

import composite.*;
import factory.DataFrame;

public class SumVisitor implements Visitor {

    private Double result = 0.0;

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
        Double partialSum = f.sum(label);
        if (partialSum != null){
            result += partialSum;
        }
    }

    public Double getResult() {
        return result;
    }

}
