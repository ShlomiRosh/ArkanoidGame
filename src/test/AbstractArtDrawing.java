/* name: shalomi rosh
   id: 308154418
   oop
*/
package test;

import biuoop.GUI;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import java.util.Random;
import java.awt.Color;
/**
 * A program that receives a number of lines displays their crop points in red
 * And the midpoints are blue.
 * @author Shlomi Rosh
 */
public class AbstractArtDrawing {
    private final int maxLine = 10;
    private Line[] segment = new Line[maxLine];
    private Point[] cut = new Point[101];
    private Point start, end, mid;
    private Line setLine;
    private  GUI gui = new GUI("Random Lines", 800, 600);
    private DrawSurface draw = gui.getDrawSurface();

    /**
     * progrem that draw line on the screan.
     */
    public void drawLine() {
        setSegments();
        setMiddPoints();
        this.gui.show(draw);
    }
    /**
     * A program that calculates the cutpoints inserts into an array and extension to paint.
     */
    public void setMiddPoints() {
        for (int i = 0; i < maxLine; i++) {
            for (int j = 1; j < maxLine; j++) {
                if (segment[i].isIntersecting(segment[j])) {
                    cut[j] = segment[i].intersectionWith(segment[j]);
                    pintCuts();
                }
            }
        }
    }
    /**
     * A program that keeps the lines in the array sends them to paint.
     */
    public void setSegments() {
        for (int i = 0; i < maxLine; i++) {
            segment[i] = getLine(segment[i]);
            pintLine();
            pintMidd();
        }
    }
    /**
     * Constructor.
     * @param line , point that start thr line
     * @return Line
     */
    public Line getLine(Line line) {
        Random rand = new Random();
        double x1 = (double) (rand.nextInt(800) + 1);
        double y1 = (double) (rand.nextInt(600) + 1);
        double x2 = (double) (rand.nextInt(800) + 1);
        double y2 = (double) (rand.nextInt(600) + 1);
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.mid = new Point((x1 + x2) / 2, (y1 + y2) / 2);
        this.setLine = new Line(start, end);
        line = setLine;
        return line;
    }
    /**
     * A program that paints a row.
     */
    public void pintLine() {
        draw.setColor(Color.BLACK);
        this.draw.drawLine((int) this.start.getX(), (int) this.start.getY(),
                (int) this.end.getX(), (int) this.end.getY());
    }
    /**
     * A program that paints a middel point.
     */
    public void pintMidd() {
        draw.setColor(Color.BLUE);
        draw.fillCircle((int) this.mid.getX(), (int) this.mid.getY(), 3);
    }
    /**
     * A program that paints a pointcuts.
     */
    public void pintCuts() {
        for (int i = 0; i < 101; i++) {
            if (cut[i] != null) {
                draw.setColor(Color.RED);
                draw.fillCircle((int) this.cut[i].getX(), (int) this.cut[i].getY(), 3);
            }
        }
    }
    /**
     * The program that runs it all.
     * @param args Not used here
     */
    public static void main(String[] args) {
        AbstractArtDrawing tester;
        tester = new AbstractArtDrawing();
        tester.drawLine();
    }
}


