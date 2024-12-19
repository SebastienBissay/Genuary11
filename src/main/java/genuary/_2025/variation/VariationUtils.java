package genuary._2025.variation;

import processing.core.PApplet;

import static processing.core.PApplet.floor;
import static processing.core.PConstants.PI;

public class VariationUtils {
    private static PApplet pApplet;

    private VariationUtils() {
        throw new UnsupportedOperationException("This is an utility class and should not be instantiated");
    }

    public static void setPApplet(PApplet pApplet) {
        VariationUtils.pApplet = pApplet;
    }

    public static float omega() {
        return PI * floor(pApplet.random(2));
    }

    public static float lambda() {
        return 2 * floor(pApplet.random(2)) - 1f;
    }

    public static float psi() {
        return pApplet.random(1);
    }
}
