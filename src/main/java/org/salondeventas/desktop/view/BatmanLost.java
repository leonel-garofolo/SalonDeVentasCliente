package org.salondeventas.desktop.view;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class BatmanLost extends Application {

    class WingClipper {
        @FXML
        private ImageView imageView;

        @FXML
        public void initialize() {
            // set a clip to apply rounded border to the original image.
            Rectangle clip = new Rectangle(
                imageView.getFitWidth(), imageView.getFitHeight()
            );
            clip.setArcWidth(20);
            clip.setArcHeight(20);
            imageView.setClip(clip);

            // snapshot the rounded image.
            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);
            WritableImage image = imageView.snapshot(parameters, null);

            // remove the rounding clip so that our effect can show through.
            imageView.setClip(null);

            // apply a shadow effect.
            imageView.setEffect(new DropShadow(20, Color.BLACK));

            // store the rounded image in the imageView.
            imageView.setImage(image);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource(
                "batmanlostinthemix.fxml"
            )
        );
        loader.setController(new WingClipper());

        Pane batman = loader.load();

        stage.setTitle("Where's Batman?");
        stage.setScene(new Scene(batman));
        stage.show();
    }
}