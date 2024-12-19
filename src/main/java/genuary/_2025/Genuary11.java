package genuary._2025;

import genuary._2025.variation.Formula;
import genuary._2025.variation.VariationUtils;
import processing.core.PApplet;
import processing.core.PVector;

import static genuary._2025.parameters.Parameters.*;
import static genuary._2025.save.SaveUtil.saveSketch;

public class Genuary11 extends PApplet {
    public static void main(String[] args) {
        PApplet.main(Genuary11.class);
    }

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
        randomSeed(SEED);
        noiseSeed(floor(random(MAX_INT)));
        smooth(SMOOTH);

        VariationUtils.setPApplet(this);
    }

    @Override
    public void setup() {
        background(BACKGROUND_COLOR.red(), BACKGROUND_COLOR.green(), BACKGROUND_COLOR.blue());
        stroke(STROKE_COLOR.red(), STROKE_COLOR.green(), STROKE_COLOR.blue(), STROKE_COLOR.alpha());
        noFill();
        noLoop();
    }

    @Override
    public void draw() {
        Formula formula = new Formula(this);
        System.out.println(formula.getName());

        for (float y = MIN_Y; y <= MAX_Y; y += STEP) {
            for (float x = MIN_X; x <= MAX_X; x += STEP) {
                drawVariation(x, y, formula);
            }
        }
        saveSketch(this);
    }

    private void drawVariation(float x, float y, Formula formula) {
        PVector v = new PVector(x, y);

        v = formula.apply(v);

        wrap(v);

        float xx = map(v.x + 0.003f * randomGaussian(), MIN_X, MAX_X, MARGIN, WIDTH - MARGIN);
        float yy = map(v.y + 0.003f * randomGaussian(), MIN_Y, MAX_Y, MARGIN, HEIGHT - MARGIN);


        point(xx, yy);
    }

    private void wrap(PVector v) {
        v.x = (v.x - MIN_X) % (MAX_X - MIN_X);
        if (v.x < 0) {
            v.x += MAX_X - MIN_X;
        }
        v.y = (v.y - MIN_Y) % (MAX_Y - MIN_Y);
        if (v.y < 0) {
            v.y += MAX_Y - MIN_Y;
        }
        v.x += MIN_X;
        v.y += MIN_Y;
    }
}