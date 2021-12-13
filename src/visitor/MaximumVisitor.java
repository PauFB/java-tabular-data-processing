package visitor;

import composite.*;

public class MaximumVisitor implements Visitor {

    public Double visit(DirectoryCOMP d, String label) {
        double max = Integer.MIN_VALUE;
        Double maxValue;
        for (DataFrameCOMP child : d.getChildren()) {
            if (child instanceof DirectoryCOMP) {
                maxValue = this.visit((DirectoryCOMP) child, label);
                if (maxValue != null && maxValue > max) {
                    max = maxValue;
                }
            } else if (child instanceof FileCOMP){
                maxValue = this.visit((FileCOMP) child, label);
                if (maxValue != null && maxValue > max) {
                    max = maxValue;
                }
            }
        }
        return max;
    }

    public Double visit(FileCOMP f, String label) {
        return f.getDataFrame().max(label);
    }

}
