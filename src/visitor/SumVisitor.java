package visitor;

import composite.*;

public class SumVisitor implements Visitor {

    public Double visit(DirectoryCOMP d, String label) {
        double totalSum = 0;
        Double partialSum;
        for (DataFrameCOMP child : d.getChildren()) {
            if (child instanceof DirectoryCOMP){
                partialSum = this.visit((DirectoryCOMP) child, label);
                if (partialSum != null){
                    totalSum += partialSum;
                }
            } else if (child instanceof FileCOMP){
                partialSum = this.visit((FileCOMP) child, label);
                if (partialSum != null){
                    totalSum += partialSum;
                }
            }
        }
        return totalSum;
    }

    public Double visit(FileCOMP f, String label) {
        return f.getDataFrame().sum(label);
    }

}
