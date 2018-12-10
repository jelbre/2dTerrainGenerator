package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;

import java.awt.*;


public class Controller {
    @FXML
    Slider sldFrequency;

    @FXML
    Slider sldLacunatiry;

    @FXML
    Slider sldAmplitude;

    @FXML
    Slider sldPersistance;

    @FXML
    Slider sldOctaves;

    @FXML
    Canvas drawCanvas;

    public void UpdateCanvas()
    {
        GraphicsContext gc = drawCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight());
        double[] points = getTerrain();
        double lastPoint = 0;
        if (points.length > 0)
        {
            double lineWidth = drawCanvas.getWidth() / points.length;
            for (int i = 0; i < points.length; i++) {
                if (i > 0)
                {
                    gc.strokeLine((i-1) * lineWidth, lastPoint, i * lineWidth, points[i]);
                    if (i%100 == 0)
                    {
                        gc.strokeLine(i, 0, i, drawCanvas.getHeight());
                    }
                }
                lastPoint = points[i];
            }
        }
    }

    public double[] getTerrain()
    {
        int frequency = (int)sldFrequency.getValue();
        int lacunarity = (int)sldLacunatiry.getValue();

        double amplitude = sldAmplitude.getValue();
        double persistance = sldPersistance.getValue();

        int octaves = (int)sldOctaves.getValue();

        int minHeight = 1;

        int width = (int)drawCanvas.getWidth();

        double[] yPoints = new double[width];

        for (int i = 0; i < width; i++) {
            yPoints[i] = minHeight;
        }

        for (int o = 0; o < octaves; o++) {
            double[] newPoints = getPerlinLine(amplitude, frequency, width);

            if (yPoints.length == newPoints.length)
            {
                for (int i = 0; i < yPoints.length; i++) {
                    yPoints[i] += newPoints[i];
                }
            }

            amplitude *= persistance;
            frequency /= lacunarity;
        }
        return yPoints;
    }

    double Interpolate(double pa, double pb, double px){
        double ft = px * Math.PI,
                f = (1 - Math.cos(ft)) * 0.5;
        return pa * (1 - f) + pb * f;
    }

    public double[] getPerlinLine(double amplitude, double frequency, int width) {
        double a = Math.random();
        double b = Math.random();
        double[] pos = new double[width];

        for (int x = 0; x < width; x++) {
            if (x % frequency == 0) {
                a = b;
                b = Math.random();
                pos[x] = a * amplitude;
            } else {
                pos[x] = Interpolate(a, b, (x % frequency) / frequency) * amplitude;
            }
        }

        return pos;
    }
}