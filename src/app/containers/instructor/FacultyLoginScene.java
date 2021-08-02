package app.containers.instructor;

import java.sql.ResultSet;

import app.Main;
import app.components.CustomButton;
import app.components.CustomImage;

import app.utils.database;
import app.utils.constants.UserRoles;

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

public class FacultyLoginScene {

  public static HBox getView() {

    CustomButton loginButton = new CustomButton("Login");
    CustomButton registerButton = new CustomButton("Register");

    TextField userField = new TextField();
    userField.setPromptText("Username...");
    PasswordField passwordField = new PasswordField();
    passwordField.setPromptText("Password...");
    Text errorText = new Text();

    HBox hBox = new HBox();
    hBox.setAlignment(Pos.CENTER);

    Image image = CustomImage.getImage("src/assets/teacher-frame.png");
    ImageView imageView = new ImageView(image);
    imageView.setFitWidth(420);

    HBox imageBox = new HBox();
    imageBox.setAlignment(Pos.CENTER);
    imageBox.setPadding(new Insets(16, 45, 16, 16));
    imageBox.getChildren().addAll(imageView);

    VBox vBox = new VBox();
    vBox.setAlignment(Pos.CENTER);
    vBox.setSpacing(16);
    vBox.setPadding(new Insets(10, 10, 10, 10));
    vBox.setBackground(new Background(new BackgroundFill(Color.rgb(108, 98, 255), new CornerRadii(10), Insets.EMPTY)));
    vBox.getChildren().addAll(userField, passwordField, loginButton, errorText, registerButton);

    hBox.getChildren().addAll(imageBox, vBox);

    hBox.setBackground(new Background(new BackgroundFill(Color.rgb(230, 245, 248), new CornerRadii(10), Insets.EMPTY)));

    registerButton.setOnAction(event -> {
      Main.setScene(5, FacultyRegisterScene.getView());
    });

    loginButton.setOnAction(event -> {
      String username = userField.getText();
      String password = passwordField.getText();

      if (username.isEmpty())
        errorText.setText("Please enter your name!");
      else if (password.isEmpty())
        errorText.setText("Please enter the semester");
      else {
        try {
          if (database.startConnection()) {
            ResultSet result = database.login(username, password);

            if (result.next()) {
              if (result.getInt(4) != UserRoles.Faculty) {
                errorText.setText("Please Login as Faculty");
              } else {
                System.out.println("ID : " + result.getString(1));
                FacultyDashboardScene.setID(result.getString(1));
                Main.setScene(6, FacultyDashboardScene.getView());
              }
            } else {
              errorText.setText("Invalid username or password");
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
