package visitor;

import composite.*;

public interface Visitor {

    void visit(DirectoryCOMP d, String label);

    void visit(FileCOMP f, String label);

    Double getResult();

}
