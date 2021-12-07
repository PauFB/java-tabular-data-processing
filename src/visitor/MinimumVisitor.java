package visitor;

import composite.*;

public class MinimumVisitor implements Visitor {

    public Double visit(DirectoryCOMP d, String label) {
        double min = Integer.MAX_VALUE;
        Double visited;
        for (DataFrameCOMP child : d.getChildren()) {
            if (child instanceof DirectoryCOMP){
                visited = this.visit((DirectoryCOMP) child, label);
                if (visited != null && visited < min) {
                    min = visited;
                }
            } else if (child instanceof FileCOMP){
                visited = this.visit((FileCOMP) child, label);
                if (visited != null && visited < min) {
                    min = visited;
                }
            }
        }
        return min;
    }

    public Double visit(FileCOMP f, String label) {
        return f.getDataFrame().min(label);
    }

}
