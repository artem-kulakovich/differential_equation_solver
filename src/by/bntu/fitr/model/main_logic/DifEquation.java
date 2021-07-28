package by.bntu.fitr.model.main_logic;


import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DifEquation {
    private double lowerLimit; //нижний предел
    private double upperLimit; //верхний предел
    private double step; //шаг
    private double yInit; //у начальное
    private Parser parser; //com объект
    private List<String> info; //вспомогательная переменная для хранения информации об уравнении

    double[] x; //результаты x
    double[] y; //результаты y


    public DifEquation() {

    }

    public DifEquation(double lowerLimit, double upperLimit, double step, double yInit, Parser parser) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.yInit = yInit;
        this.step = step;
        this.parser = parser;
    }


    //3*x - 2*y + 5;
    //свободные члены идут последние
    //запись должна кончаться проблеом
    //метод дл решения диф ур
    public void rungeMethod() throws RemoteException {
        info = new ArrayList<>();
        double n = (upperLimit - lowerLimit) / step;
        x = new double[((int) n + 1)];
        y = new double[((int) n + 1)];
        x[0] = lowerLimit;
        y[0] = yInit;


        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat decimalFormat = (DecimalFormat) nf;
        decimalFormat.applyPattern(".###");


        for (int i = 0; i < n; i++) {
            double k1 = Double.parseDouble(decimalFormat.format(getStep() * getFunction(x[i], y[i])));
            double k2 = Double.parseDouble(decimalFormat.format( getStep() * getFunction((x[i] + 0.5 * getStep()), y[i] + 0.5 * k1)));
            double k3 = Double.parseDouble(decimalFormat.format( getStep() * getFunction(x[i] + 0.5 * getStep(), y[i] + 0.5 * k2)));
            double k4 = Double.parseDouble(decimalFormat.format( getStep() * getFunction(x[i] + 0.5 * getStep(), y[i] + 0.5 * k3)));

            y[i + 1] = Double.parseDouble(decimalFormat.format (y[i] + (double) 1 / 6 * (k1 + 2 * k2 + 2 * k3 + k4)));
            x[i + 1] = Double.parseDouble(decimalFormat.format( x[i] + getStep()));

        }
    }


    public double getFunction(double x, double y) throws RemoteException {
        setInfo(x, y, parser.parse(x, y));
        return parser.parse(x, y);
    }

    public double getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(double lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public double getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(double upperLimit) {
        this.upperLimit = upperLimit;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public double[] getX() {
        return x;
    }

    public void setX(double[] x) {
        this.x = x;
    }

    public double[] getY() {
        return y;
    }

    public void setY(double[] y) {
        this.y = y;
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public void setInfo(double x, double y, double res) throws RemoteException {
        String str = ("Уравнение вида:\t" + parser.getEquation() + ";"
                + "Коориданата x:" + String.format("%.3f", x) + ";" + "Координата y:" + String.format("%.3f", y) + ";"
                + "Результат:\t" + String.format("%.3f", res));
        info.add(str);
    }

    public List<String> getInfo() {
        return info;
    }
}
