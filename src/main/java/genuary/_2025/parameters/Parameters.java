package genuary._2025.parameters;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static processing.core.PConstants.TAU;

public final class Parameters {
    public static final long SEED = 11012025;

    public static final int WIDTH = 2025;
    public static final int HEIGHT = 2025;
    public static final int MARGIN = 50;
    public static final int SMOOTH = 8;
    public static final float MIN_X = -3;
    public static final float MAX_X = 3;
    public static final float MIN_Y = -3;
    public static final float MAX_Y = 3;
    public static final float STEP = (MAX_X - MIN_X) / (2.321f * WIDTH);
    public static final int MINIMUM_VARIATIONS = 3;
    public static final int MAXIMUM_VARIATIONS = 5;
    public static final Color BACKGROUND_COLOR = new Color(250);
    public static final Color STROKE_COLOR = new Color(20, 15);

    public static final float LINEAR_PARAMETER = 1f;
    public static final float SINUSOIDAL_PARAMETER = 1f;
    public static final float HYPERBOLIC_PARAMETER = 1f;
    public static final float PDJ_A_PARAMETER = 0.1f;
    public static final float PDJ_B_PARAMETER = 1.9f;
    public static final float PDJ_C_PARAMETER = -0.8f;
    public static final float PDJ_D_PARAMETER = -1.2f;
    public static final float PDJ_PARAMETER = 1f;
    public static final float JULIA_PARAMETER = 1f;
    public static final float SECH_PARAMETER = 1f;


    /**
     * Helper method to extract the constants in order to save them to a json file
     *
     * @return a Map of the constants (name -> value)
     */
    public static Map<String, Object> toJsonMap() throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();

        Field[] declaredFields = Parameters.class.getDeclaredFields();
        for (Field field : declaredFields) {
            map.put(field.getName(), field.get(Parameters.class));
        }

        return Collections.singletonMap(Parameters.class.getSimpleName(), map);
    }

    public record Color(float red, float green, float blue, float alpha) {
        public Color(float red, float green, float blue) {
            this(red, green, blue, 255);
        }

        public Color(float grayscale, float alpha) {
            this(grayscale, grayscale, grayscale, alpha);
        }

        public Color(float grayscale) {
            this(grayscale, 255);
        }

        public Color(String hexCode) {
            this(decode(hexCode));
        }

        public Color(Color color) {
            this(color.red, color.green, color.blue, color.alpha);
        }

        public static Color decode(String hexCode) {
            return switch (hexCode.length()) {
                case 2 -> new Color(Integer.valueOf(hexCode, 16));
                case 4 -> new Color(Integer.valueOf(hexCode.substring(0, 2), 16),
                        Integer.valueOf(hexCode.substring(2, 4), 16));
                case 6 -> new Color(Integer.valueOf(hexCode.substring(0, 2), 16),
                        Integer.valueOf(hexCode.substring(2, 4), 16),
                        Integer.valueOf(hexCode.substring(4, 6), 16));
                case 8 -> new Color(Integer.valueOf(hexCode.substring(0, 2), 16),
                        Integer.valueOf(hexCode.substring(2, 4), 16),
                        Integer.valueOf(hexCode.substring(4, 6), 16),
                        Integer.valueOf(hexCode.substring(6, 8), 16));
                default -> throw new IllegalArgumentException();
            };
        }
    }
}
