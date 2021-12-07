package visitor;

import composite.*;

public class SumVisitor implements Visitor {

    public Double visit(DirectoryCOMP d, String label) {
        double sum = 0;
        Double visited;
        for (DataFrameCOMP child : d.getChildren()) {
            if (child instanceof DirectoryCOMP){
                visited = this.visit((DirectoryCOMP) child, label);
                if (visited != null){
                    sum += visited;
                }
            } else if (child instanceof FileCOMP){
                visited = this.visit((FileCOMP) child, label);
                if (visited != null){
                    sum += visited;
                }
            }
        }
        return sum;
    }

    public Double visit(FileCOMP f, String label) {
        return f.getDataFrame().sum(label);
    }

}
