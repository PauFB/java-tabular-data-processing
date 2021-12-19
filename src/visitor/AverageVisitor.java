package visitor;

import composite.*;
import factory.DataFrame;

import java.util.ArrayList;

public class AverageVisitor implements Visitor {

    double accumulator = 0.0;
    int nElements = 0;
    Double result = null;

    // no retornar resultats
    // guardar el resultat en una variable de cada classe "visitor"
    public void visit(DirectoryCOMP d, String label) {
        ArrayList<String> list;
        for (DataFrame child : d.getChildren()) {
            if (child instanceof DirectoryCOMP) {
                visit((DirectoryCOMP) child, label);
            } else if (child instanceof FileCOMP) {
                list = child.getColumn(label);
                if (list != null) {
                    for (String value : list) {
                        accumulator += Integer.parseInt(value);
                        nElements++;
                    }
                }
            }
        }
        if (nElements != 0){
            result = accumulator/nElements;
        }
    }

    public void visit(FileCOMP f, String label) {
        result = f.average(label);
    }

    public Double getResult() {
        return result;
    }
}
