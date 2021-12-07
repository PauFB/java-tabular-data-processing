package visitor;

import composite.*;

public interface Visitor {

    public void visit(DirectoryCOMP e);

    public void visit(FileCOMP e);

}