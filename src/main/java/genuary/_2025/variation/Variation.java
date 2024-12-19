package genuary._2025.variation;

import processing.core.PVector;

import java.util.function.UnaryOperator;

import static genuary._2025.parameters.Parameters.*;
import static genuary._2025.variation.VariationUtils.psi;
import static processing.core.PApplet.*;

public enum Variation {
    LINEAR("linear", v -> PVector.mult(v, LINEAR_PARAMETER)),
    SINUSOIDAL("sinusoidal", v -> new PVector(sin(v.x), sin(v.y)).mult(SINUSOIDAL_PARAMETER)),
    HYPERBOLIC("hyperbolic", v -> {
        float r = (float) (v.mag() + 1e-10);
        float theta = v.heading();
        return new PVector(sin(theta) / r, r * cos(theta)).mult(HYPERBOLIC_PARAMETER);
    }),
    PDJ("pdj", v -> new PVector(
            sin(PDJ_A_PARAMETER * v.y) - cos(PDJ_B_PARAMETER * v.x),
            sin(PDJ_C_PARAMETER * v.x) - cos(PDJ_D_PARAMETER * v.y)
    ).mult(PDJ_PARAMETER)),
    JULIA("julia", v -> {
        float r = JULIA_PARAMETER * sqrt(v.mag());
        float theta = 0.5f * v.heading() + floor(2 * psi()) * PI;
        return new PVector(cos(theta), sin(theta)).mult(r);
    }),
    SECH("sech", v -> {
        float d = cos(2 * v.y) + (float) Math.cosh(2 * v.x);
        if (d != 0) {
            d = SECH_PARAMETER * 2 / d;
        }
        return new PVector(d * cos(v.y) * (float) Math.cosh(v.x),
                -d * sin(v.y) * (float) Math.sinh(v.x));
    });

    public final String displayName;
    public final UnaryOperator<PVector> operator;

    Variation(String displayName, UnaryOperator<PVector> operator) {
        this.displayName = displayName;
        this.operator = operator;
    }
}
