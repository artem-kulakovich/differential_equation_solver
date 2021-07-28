package by.bntu.fitr.entity;

public class Equation {

    private int id;
    private String equation;
    private double lowerLim;
    private double upperLim;
    private double step;
    private double initY;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public double getLowerLim() {
        return lowerLim;
    }

    public void setLowerLim(double lowerLim) {
        this.lowerLim = lowerLim;
    }

    public double getUpperLim() {
        return upperLim;
    }

    public void setUpperLim(double upperLim) {
        this.upperLim = upperLim;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public double getInitY() {
        return initY;
    }

    public void setInitY(double initY) {
        this.initY = initY;
    }
}
