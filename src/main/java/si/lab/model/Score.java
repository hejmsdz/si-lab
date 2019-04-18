package si.lab.model;

public enum Score {
    A(5),
    B(4.5),
    C(4),
    D(3.5),
    E(3),
    F(2);
    private double value;

    Score(double value) {
        this.value = value;
    }

    double getValue() {
        return this.value;
    }
}
