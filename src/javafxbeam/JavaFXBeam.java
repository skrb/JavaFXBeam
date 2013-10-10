package javafxbeam;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaFXBeam extends Application {
    private Group root;

    @Override
    public void start(Stage stage) throws IOException {
        root = new Group();
        ImageView view = new ImageView(new Image(getClass().getResource("man.png").toString()));
        root.getChildren().add(view);
        view.setTranslateX(10.0); view.setTranslateY(50.0);
        
        Button button = new Button("ビーム！！");
        button.setOnAction(e -> beam());
        button.setTranslateX(180.0); button.setTranslateY(10.0);
        root.getChildren().add(button);
        
        Scene scene = new Scene(root, 400, 200);
        
        stage.setScene(scene);
        stage.show();
    }
    
    private void beam() {
        final Line beam = new Line(105, 88, 105, 88);
        beam.setStroke(Color.YELLOW);
        beam.setStrokeWidth(10.0);
        beam.setStrokeLineCap(StrokeLineCap.ROUND);
        beam.setEffect(new Bloom());
        root.getChildren().add(beam);
        
        new Timeline(
            new KeyFrame(Duration.millis(100.0), 
                         new KeyValue(beam.endXProperty(), 450)),
            new KeyFrame(Duration.millis(900.0), 
                         new KeyValue(beam.endXProperty(), 450),
                         new KeyValue(beam.startXProperty(), 105)),
            new KeyFrame(Duration.millis(1000.0), 
                         e -> root.getChildren().remove(beam),
                         new KeyValue(beam.startXProperty(), 450))
        ).play();
    }

    public static void main(String... args) {
        launch(args);
    }
}
