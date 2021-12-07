package visitor;

import composite.*;

public interface Visitor {

    public void visit(DirectoryCOMP d);

    public void visit(FileCOMP e);

}