package visitor;

import composite.*;

public interface Visitor {

    public Double visit(DirectoryCOMP d, String label);

    public Double visit(FileCOMP e, String label);

}