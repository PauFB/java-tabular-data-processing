package visitor;

import factory.DataFrame;

public class MaximumVisitor implements Visitor {

    private final String label;
    private Double result;

    public MaximumVisitor(String label) {
        this.label = label;
    }

    public void visit(DataFrame df){
        double max = Integer.MIN_VALUE;
        int labelIndex = df.getLabelList().indexOf(label);

        if (labelIndex != -1) {
            try {
                for (String elem : df.getContent().get(labelIndex)) {
                    max = Math.max(Integer.parseInt(elem), max);
                }
                result = max;
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
