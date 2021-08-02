package app.containers;

import app.Main;

import app.components.CustomButton;
import app.components.CustomImage;
import app.containers.instructor.FacultyLoginScene;
import app.containers.student.StudentLoginScene;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LandingScene {
  public static HBox getView() {
    new JFXPanel();

    CustomButton facultyLogin = new CustomButton("Faculty Login");
    CustomButton studentLogin = new CustomButton("Student Login");

    HBox view = new HBox();
    Image image = CustomImage.getImage("src/assets/landing-frame.png");
    ImageView imageView = new ImageView(image);

    HBox imageBox = new HBox();
    imageBox.setAlignment(Pos.CENTER);
    imageBox.setPadding(new Insets(16, 16, 16, 16));
    imageBox.setMinWidth(400);
    imageBox.getChildren().addAll(imageView);

    VBox vBox = new VBox();
    vBox.setAlignment(Pos.CENTER);
    vBox.setMinWidth(180);
    vBox.setSpacing(15);
    vBox.setPadding(new Insets(10, 10, 10, 10));
    vBox.setBackground(new Background(new BackgroundFill(Color.rgb(108, 98, 255), new CornerRadii(10), Insets.EMPTY)));
    vBox.getChildren().addAll(facultyLogin, studentLogin);

    view.getChildren().addAll(vBox, imageBox);

    view.setBackground(new Background(new BackgroundFill(Color.rgb(230, 245, 248), new CornerRadii(10), Insets.EMPTY)));

    studentLogin.setOnAction(event -> {
      Main.setScene(1, StudentLoginScene.getView());
    });

    facultyLogin.setOnAction(event -> {
      Main.setScene(2, FacultyLoginScene.getView());
    });

    return view;
  }

}
