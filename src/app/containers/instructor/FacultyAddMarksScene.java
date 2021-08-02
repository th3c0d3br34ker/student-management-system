package app.containers.instructor;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import app.Main;
import app.components.CustomButton;
import app.components.CustomText;
import app.utils.database;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class FacultyAddMarksScene {
  private static String id;
  private static String selectedStudent;

  public static HBox getView() {
    HBox view = new HBox();

    Text errorText = new Text();

    List<String> studentList = new ArrayList<String>();

    try {
      if (database.startConnection()) {
        ResultSet studentSet = database.getStudents();

        while (studentSet.next()) {
          System.out.println("Student ID : " + studentSet.getString(1));
          studentList.add(studentSet.getString(1));
        }
      } else {
        errorText.setText("Failed to Connect");
        System.out.println("Failed to Connect");
      }
    } catch (Exception e) {
      e.printStackTrace();
      errorText.setText("Failed");
    } finally {
      database.closeConnection();
    }

    ComboBox<String> students = new ComboBox<String>(FXCollections.observableArrayList(studentList));
    students.setMinWidth(100);

    CustomButton addButton = new CustomButton("Add");
    CustomButton backButton = new CustomButton("Back");
    CustomButton exitButton = new CustomButton("Exit");
    exitButton.setBackgroundColor(255, 99, 71);

    CustomText idText = new CustomText();
    idText.setText(id);

    TextField subjectField = new TextField();
    subjectField.setPromptText("Enter Subject");
    subjectField.maxWidth(100);

    TextField marksField = new TextField();
    marksField.setPromptText("Enter Subject");
    marksField.maxWidth(100);

    CustomText headingID = new CustomText("#");
    CustomText heading1 = new CustomText("Subject");
    CustomText heading2 = new CustomText("Marks");
    headingID.setMinWidth(120);
    heading1.setMinWidth(120);
    heading2.setMinWidth(120);

    HBox tableHeading = new HBox();
    tableHeading.setMinWidth(400);
    tableHeading.setSpacing(15);
    tableHeading.setPadding(new Insets(10, 10, 10, 10));
    tableHeading.getChildren().addAll(headingID, heading1, heading2);

    HBox tableBody = new HBox();
    tableBody.setMinWidth(400);
    tableBody.setAlignment(Pos.CENTER);
    tableBody.setSpacing(15);
    tableBody.setPadding(new Insets(10, 10, 10, 10));
    tableBody
        .setBackground(new Background(new BackgroundFill(Color.rgb(108, 98, 255), new CornerRadii(10), Insets.EMPTY)));
    tableBody.getChildren().addAll(students, subjectField, marksField);

    VBox textViewBox = new VBox();
    textViewBox.setAlignment(Pos.CENTER);
    textViewBox.setMinWidth(480);
    textViewBox.setSpacing(15);
    textViewBox.setPadding(new Insets(10, 10, 10, 10));
    textViewBox.getChildren().addAll(tableHeading, tableBody, addButton);

    VBox vBox = new VBox();
    vBox.setAlignment(Pos.CENTER);
    vBox.setMinWidth(160);
    vBox.setSpacing(15);
    vBox.setPadding(new Insets(10, 10, 10, 10));
    vBox.setBackground(new Background(new BackgroundFill(Color.rgb(108, 98, 255), new CornerRadii(10), Insets.EMPTY)));
    vBox.getChildren().addAll(idText, backButton, errorText, exitButton);

    students.setOnAction(event -> {
      selectedStudent = students.getValue();
    });

    addButton.setOnAction(event -> {
      try {
        String id = selectedStudent;
        String subject = subjectField.getText();
        String marks = marksField.getText();

        if (database.startConnection()) {
          if (database.updateMarks(id, subject, marks) == 1) {
            errorText.setText("Success");
          } else {
            errorText.setText("Failed");
          }
        } else {
          errorText.setText("Failed to Connect.");
        }
      } catch (Exception e) {
        e.printStackTrace();

      } finally {
        database.closeConnection();
      }
    });

    backButton.setOnAction(event -> {
      FacultyDashboardScene.setID(id);
      Main.setScene(6, FacultyDashboardScene.getView());
    });

    exitButton.setOnAction(event -> {
      Platform.exit();
    });

    view.getChildren().addAll(textViewBox, vBox);
    return view;
  }

  public static void setID(String newID) {
    id = newID;
  }
}
