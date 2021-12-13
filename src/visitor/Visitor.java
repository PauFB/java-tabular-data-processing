package visitor;

import composite.*;

public interface Visitor {

    Double visit(DirectoryCOMP d, String label);

    Double visit(FileCOMP f, String label);

}
