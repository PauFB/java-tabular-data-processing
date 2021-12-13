package visitor;

import composite.*;

public class AverageVisitor implements Visitor {

    public Double visit(DirectoryCOMP d, String label) {
        ArrayList<String> list;
        for (DataFrameCOMP child : d.getChildren()) {
            if (child instanceof DirectoryCOMP) {
                this.visit((DirectoryCOMP) child, label);
            } else if (child instanceof FileCOMP) {
                list = ((FileCOMP) child).getContent(label);
                if (list != null) {
                    for (String value : list) {
                        accumulator += Integer.parseInt(value);
                        nElements++;
                    }
                }
            }
        }
        return this.accumulator / this.nElements;
    }

    public Double visit(FileCOMP f, String label) {
        return f.getDataFrame().average(label);
    }

}
