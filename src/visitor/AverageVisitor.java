package visitor;

import composite.*;

public class AverageVisitor implements Visitor {

    public Double visit(DirectoryCOMP d, String label) {
        Double avg = null;
        boolean first = true;
        Double visited;
        for (DataFrameCOMP child : d.getChildren()) {
            if (child instanceof DirectoryCOMP) {
                visited = this.visit((DirectoryCOMP) child, label);
                if (visited != null && !first){
                    avg = (visited + avg)/2;
                } else if(visited != null && first){
                    avg = visited;
                    first = false;
                }
            } else if (child instanceof FileCOMP) {
                visited = this.visit((FileCOMP) child, label);
                if (visited != null && !first){
                    avg = (visited + avg)/2;
                } else if(visited != null && first){
                    avg = visited;
                    first = false;
                }
            }
        }
        return avg;
    }

    public Double visit(FileCOMP f, String label) {
        return f.getDataFrame().average(label);
    }

}
