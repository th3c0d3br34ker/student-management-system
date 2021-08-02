package app.containers.student;

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

public class StudentLoginScene {

  public static HBox getView() {

    CustomButton loginButton = new CustomButton("Login");
    CustomButton registerButton = new CustomButton("Register");

    TextField userField = new TextField();
    userField.setPromptText("Username...");
    PasswordField passwordField = new PasswordField();
    passwordField.setPromptText("Password...");
    Text errorText = new Text();

    HBox hBox = new HBox();
    Image image = CustomImage.getImage("src/assets/student-frame.png");
    ImageView imageView = new ImageView(image);

    imageView.setFitWidth(420);

    VBox vBox = new VBox();
    vBox.setAlignment(Pos.CENTER);
    vBox.setSpacing(15);
    vBox.setPadding(new Insets(10, 10, 10, 10));
    vBox.setBackground(new Background(new BackgroundFill(Color.rgb(108, 98, 255), new CornerRadii(10), Insets.EMPTY)));
    vBox.getChildren().addAll(userField, passwordField, loginButton, errorText, registerButton);

    HBox imageBox = new HBox();
    imageBox.setAlignment(Pos.CENTER);
    imageBox.setPadding(new Insets(16, 45, 16, 16));
    imageBox.getChildren().addAll(imageView);

    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(imageBox, vBox);
    hBox.setBackground(new Background(new BackgroundFill(Color.rgb(230, 245, 248), new CornerRadii(10), Insets.EMPTY)));

    registerButton.setOnAction(event -> {
      Main.setScene(3, StudentRegisterScene.getView());
    });

    loginButton.setOnAction(event -> {
      String username = userField.getText();
      String password = passwordField.getText();

      if (username.isEmpty())
        errorText.setText("Please enter the username");
      else if (password.isEmpty())
        errorText.setText("Please enter the password");
      else {
        try {
          if (database.startConnection()) {
            ResultSet result = database.login(username, password);

            if (result.next()) {
              if (result.getInt(4) != UserRoles.Student) {
                errorText.setText("Please Login as Student");
              } else {
                errorText.setText("Success");
                StudentDashboardScene.setID(result.getString(1));
                System.out.println("ID : " + result.getString(1));
                Main.setScene(4, StudentDashboardScene.getView());
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
