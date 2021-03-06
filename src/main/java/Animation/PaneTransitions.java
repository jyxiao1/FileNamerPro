package Animation;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;


public class PaneTransitions {

    /**
     *
     * @param node
     */
    public static void popupTransition(Node node){

    }

    /**
     *
     * @param node
     */
    public static SequentialTransition slidingTransition(Node node, int direction, double width){
        TranslateTransition t = new TranslateTransition(Duration.millis(80), node);
        TranslateTransition t1 = new TranslateTransition(Duration.millis(12), node);
        TranslateTransition t2 = new TranslateTransition(Duration.millis(16), node);
        TranslateTransition t3 = new TranslateTransition(Duration.millis(25), node);
        TranslateTransition t4 = new TranslateTransition(Duration.millis(50), node);
        TranslateTransition t5 = new TranslateTransition(Duration.millis(100), node);

        t.setByX(direction*width*3/4);
        t1.setByX(direction*width*1/20);
        t2.setByX(direction*width*1/20);
        t3.setByX(direction*width*1/20);
        t4.setByX(direction*width*1/20);
        t5.setByX(direction*width*1/20);

        SequentialTransition s = new SequentialTransition(t,t1,t2,t3,t4,t5);
        return s;
    }

    public static SequentialTransition slowSlidingTransition(Node node, int direction, double width){
        TranslateTransition t = new TranslateTransition(Duration.millis(300), node);
        TranslateTransition t1 = new TranslateTransition(Duration.millis(18), node);
        TranslateTransition t2 = new TranslateTransition(Duration.millis(22), node);
        TranslateTransition t3 = new TranslateTransition(Duration.millis(27), node);
        TranslateTransition t4 = new TranslateTransition(Duration.millis(30), node);
        TranslateTransition t5 = new TranslateTransition(Duration.millis(35), node);
        TranslateTransition t6 = new TranslateTransition(Duration.millis(40), node);
        TranslateTransition t7 = new TranslateTransition(Duration.millis(50), node);
        TranslateTransition t8 = new TranslateTransition(Duration.millis(70), node);
        TranslateTransition t9 = new TranslateTransition(Duration.millis(90), node);
        TranslateTransition t10 = new TranslateTransition(Duration.millis(120), node);


        t.setByX(direction*width*2/3);
        t1.setByX(direction*width*1/30);
        t2.setByX(direction*width*1/30);
        t3.setByX(direction*width*1/30);
        t4.setByX(direction*width*1/30);
        t5.setByX(direction*width*1/30);
        t6.setByX(direction*width*1/30);
        t7.setByX(direction*width*1/30);
        t8.setByX(direction*width*1/30);
        t9.setByX(direction*width*1/30);
        t10.setByX(direction*width*1/30);

        SequentialTransition s = new SequentialTransition(t,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10);
        return s;
    }

    /**
     * Fades in an pane from invisible to partially visible
     */
    public static SequentialTransition partialFadeIn(Node icon){
        icon.setOpacity(0.0);

        FadeTransition f1 = new FadeTransition(Duration.millis(100),icon);
        FadeTransition f2 = new FadeTransition(Duration.millis(75),icon);
        FadeTransition f3 = new FadeTransition(Duration.millis(50),icon);
        FadeTransition f4 = new FadeTransition(Duration.millis(60),icon);


        f1.setFromValue(0.0);
        f1.setToValue(0.1);

        f2.setFromValue(0.1);
        f2.setToValue(0.2);

        f3.setFromValue(0.2);
        f3.setToValue(0.35);

        f4.setToValue(0.35);
        f4.setToValue(0.6);

        return new SequentialTransition(f1,f2,f3,f4);
    }

    /**
     * Fades in an pane from partially visible to invisible
     */
    public static SequentialTransition partialFadeOut(Node icon){
        icon.setOpacity(0.5);

        FadeTransition f1 = new FadeTransition(Duration.millis(100),icon);
        FadeTransition f2 = new FadeTransition(Duration.millis(75),icon);
        FadeTransition f3 = new FadeTransition(Duration.millis(50),icon);
        FadeTransition f4 = new FadeTransition(Duration.millis(60),icon);


        f1.setFromValue(0.6);
        f1.setToValue(0.35);

        f2.setFromValue(0.35);
        f2.setToValue(0.2);

        f3.setFromValue(0.2);
        f3.setToValue(0.1);

        f4.setToValue(0.1);
        f4.setToValue(0.0);

        return new SequentialTransition(f1,f2,f3,f4);
    }



    public static void slideDistance(Node node, double x, double y, int duration){
        TranslateTransition t = new TranslateTransition(Duration.millis(duration), node);
        t.setByX(x);
        t.setByY(y);
        t.play();
    }

}
