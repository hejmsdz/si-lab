package si.lab.model;

import java.util.Date;

public class Grade {
    private int id;
    private Score value;
    private Date insertedAt;
    private Course course;

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
}
