package app.containers.instructor;

import app.Main;
import app.components.CustomButton;
import app.components.CustomImage;

import app.utils.database;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class FacultyRegisterScene {
  public static HBox getView() {

    CustomButton registerButton = new CustomButton("Register");
    CustomButton loginButton = new CustomButton("Login");

    TextField idField = new TextField();
    idField.setPromptText("Registration Number...");
    TextField userField = new TextField();
    userField.setPromptText("Username...");
    PasswordField passwordField = new PasswordField();
    passwordField.setPromptText("Password...");
    Text errorText = new Text();

    HBox hBox = new HBox();
    Image image = CustomImage.getImage("src/assets/teacher-frame.png");
    ImageView imageView = new ImageView(image);

    imageView.setFitWidth(420);

    VBox vBox = new VBox();
    vBox.setAlignment(Pos.CENTER);
    vBox.setSpacing(15);
    vBox.setPadding(new Insets(10, 10, 10, 10));
    vBox.setBackground(new Background(new BackgroundFill(Color.rgb(108, 98, 255), new CornerRadii(10), Insets.EMPTY)));
    vBox.getChildren().addAll(idField, userField, passwordField, registerButton, errorText, loginButton);

    HBox imageBox = new HBox();
    imageBox.setAlignment(Pos.CENTER);
    imageBox.setPadding(new Insets(16, 45, 16, 16));
    imageBox.getChildren().addAll(imageView);

    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(imageBox, vBox);
    hBox.setBackground(new Background(new BackgroundFill(Color.rgb(230, 245, 248), new CornerRadii(10), Insets.EMPTY)));

    loginButton.setOnAction(event -> {
      Main.setScene(2, FacultyLoginScene.getView());
    });

    registerButton.setOnAction(event -> {
      String id = idField.getText();
      String username = userField.getText();
      String password = passwordField.getText();
      int role = 2;

      if (username.isEmpty())
        errorText.setText("Please enter your name!");
      else if (password.isEmpty())
        errorText.setText("Please enter the semester");
      else {
        try {
          if (database.startConnection()) {
            int result = database.register(id, username, password, role);

            if (result == 1) {
              errorText.setText("Success");
              FacultyDashboardScene.setID(id);
              Main.setScene(6, FacultyDashboardScene.getView());
            } else {
              errorText.setText("Failed to Register");
            }
          } else {
            errorText.setText("Failed");
          }
        } catch (Exception e) {
          e.printStackTrace();
          errorText.setText("Failed");
        } finally {
          database.closeConnection();
        }
      }
    });

    return hBox;
  }
}
