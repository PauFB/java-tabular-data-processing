package visitor;

import factory.DataFrame;

public class SumVisitor implements Visitor {

    private final String label;
    private Double result;

    public SumVisitor(String label) {
        this.label = label;
    }

    public void visit(DataFrame df) {
        double sum = 0;
        int labelIndex = df.getLabelList().indexOf(label);

        if (labelIndex != -1) {
            try {
                for (String elem : df.getContent().get(labelIndex)) {
                    sum += Integer.parseInt(elem);
                }
                result = sum;
            } catch (NumberFormatException e) {
                result = null;
            }
        } else {
            result = null;
        }
    }

    public Double getResult() {
        return result;
    }

}
