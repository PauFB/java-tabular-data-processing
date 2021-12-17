package visitor;

import composite.*;
import factory.DataFrame;

import java.util.ArrayList;

public class AverageVisitor implements Visitor {

    Double accumulator = 0.0;
    int nElements = 0;
    Double result = 0.0;

    // no retornar resultats
    // guardar el resultat en una variable de cada classe "visitor"
    public void visit(DirectoryCOMP d, String label) {
        ArrayList<String> list;
        for (DataFrame child : d.getChildren()) {
            if (child instanceof DirectoryCOMP) {
                this.visit((DirectoryCOMP) child, label);
            } else if (child instanceof FileCOMP) {
                list = ((FileCOMP) child).getColumn(label);
                if (list != null) {
                    for (String value : list) {
                        accumulator += Integer.parseInt(value);
                        nElements++;
                    }
                }
            }
        }
        if (accumulator == 0 && nElements == 0){
            this.result = null;
        }
        this.result = this.accumulator / this.nElements;
    }

    public void visit(FileCOMP f, String label) {
        this.result = f.average(label);
    }

    public Double getResult() {
        return this.result;
    }
}
