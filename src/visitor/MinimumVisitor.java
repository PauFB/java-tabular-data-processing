package visitor;

import composite.*;

public class MinimumVisitor implements Visitor {

    public Double visit(DirectoryCOMP d, String label) {
        double min = Integer.MAX_VALUE;
        Double minValue;
        for (DataFrameCOMP child : d.getChildren()) {
            if (child instanceof DirectoryCOMP){
                minValue = this.visit((DirectoryCOMP) child, label);
                if (minValue != null && minValue < min) {
                    min = minValue;
                }
            } else if (child instanceof FileCOMP){
                minValue = this.visit((FileCOMP) child, label);
                if (minValue != null && minValue < min) {
                    min = minValue;
                }
            }
        }
        return min;
    }

    public Double visit(FileCOMP f, String label) {
        return f.getDataFrame().min(label);
    }

}
