package visitor;

import composite.*;

public class MaximumVisitor implements Visitor {

    public Double visit(DirectoryCOMP d, String label) {
        double max = Integer.MIN_VALUE;
        Double visited;
        for (DataFrameCOMP child : d.getChildren()) {
            if (child instanceof DirectoryCOMP){
                visited = this.visit((DirectoryCOMP) child, label);
                if (visited != null && visited > max) {
                    max = visited;
                }
            } else if (child instanceof FileCOMP){
                visited = this.visit((FileCOMP) child, label);
                if (visited != null && visited > max) {
                    max = visited;
                }
            }
        }
        return max;
    }

    public Double visit(FileCOMP f, String label) {
        return f.getDataFrame().max(label);
    }

}
