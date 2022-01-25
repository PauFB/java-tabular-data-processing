package visitor;

import factory.DataFrame;

public class MinimumVisitor implements Visitor {

    private final String label;
    private Double result;

    public MinimumVisitor(String label) {
        this.label = label;
    }

    public void visit(DataFrame df){
        double min = Integer.MAX_VALUE;
        int labelIndex = df.getLabelList().indexOf(label);

        if (labelIndex != -1) {
            try {
                for (String elem : df.getContent().get(labelIndex)) {
                    min = Math.min(Integer.parseInt(elem), min);
                }
                result = min;
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
