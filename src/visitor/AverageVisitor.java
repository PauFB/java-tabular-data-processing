package visitor;

import factory.DataFrame;

public class AverageVisitor implements Visitor {

    private final String label;
    private Double result;

    public AverageVisitor(String label) {
        this.label = label;
    }

    public void visit(DataFrame df) {
        double avg = 0;
        int labelIndex = df.getLabelList().indexOf(label);

        if (labelIndex != -1) {
            try {
                for (String elem : df.getContent().get(labelIndex)) {
                    avg += Integer.parseInt(elem);
                }
                result = avg / df.getContent().get(labelIndex).size();
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
