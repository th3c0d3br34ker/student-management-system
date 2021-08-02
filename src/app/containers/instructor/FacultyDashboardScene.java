package app.containers.instructor;

import java.sql.ResultSet;

import app.Main;
import app.components.CustomButton;
import app.components.CustomScrollPane;
import app.components.CustomText;
import app.utils.database;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class FacultyDashboardScene {
  private static String id;

  public static HBox getView() {
    HBox view = new HBox();

    CustomButton addMarksButton = new CustomButton("Add Marks");
    CustomButton addAttendanceButton = new CustomButton("Add Attendance");
    CustomButton exitButton = new CustomButton("Exit");
    exitButton.setBackgroundColor(255, 99, 71);

    Text errorText = new Text();

    CustomText idText = new CustomText();
    idText.setText(id);

    CustomText headingID = new CustomText("#");
    CustomText heading1 = new CustomText("Subject");
    CustomText heading2 = new CustomText("Scored Marks");
    heading1.setMinWidth(120);
    heading2.setMinWidth(120);
    headingID.setMinWidth(120);

    HBox tableHeading = new HBox();
    tableHeading.setMinWidth(480);
    tableHeading.setSpacing(15);
    tableHeading.setPadding(new Insets(10, 10, 10, 10));
    tableHeading.getChildren().addAll(headingID, heading1, heading2);

    VBox tableBody = new VBox();
    tableHeading.setMinWidth(480);
    tableHeading.setSpacing(15);
    tableHeading.setPadding(new Insets(10, 10, 10, 10));
    tableBody
        .setBackground(new Background(new BackgroundFill(Color.rgb(108, 98, 255), new CornerRadii(10), Insets.EMPTY)));

    CustomScrollPane scrollableTableBody = new CustomScrollPane(tableBody);

    VBox textViewBox = new VBox();
    textViewBox.setMinWidth(480);
    textViewBox.setSpacing(15);
    textViewBox.setPadding(new Insets(10, 10, 10, 10));
    textViewBox.getChildren().addAll(tableHeading, scrollableTableBody);

    VBox vBox = new VBox();
    vBox.setAlignment(Pos.CENTER);
    vBox.setMinWidth(160);
    vBox.setSpacing(15);
    vBox.setPadding(new Insets(10, 10, 10, 10));
    vBox.setBackground(new Background(new BackgroundFill(Color.rgb(108, 98, 255), new CornerRadii(10), Insets.EMPTY)));
    vBox.getChildren().addAll(idText, addMarksButton, addAttendanceButton, exitButton);

    view.setAlignment(Pos.CENTER);
    view.setBackground(new Background(new BackgroundFill(Color.rgb(230, 245, 248), new CornerRadii(10), Insets.EMPTY)));
    view.getChildren().addAll(textViewBox, vBox);

    addAttendanceButton.setOnAction(event -> {
      FacultyAddAttendanceScene.setID(id);
      Main.setScene(7, FacultyAddAttendanceScene.getView());
    });

    addMarksButton.setOnAction(event -> {
      FacultyAddMarksScene.setID(id);
      Main.setScene(8, FacultyAddMarksScene.getView());
    });

    exitButton.setOnAction(event -> {
      Platform.exit();
    });

    try {
      if (database.startConnection()) {
        ResultSet students = database.getStudents();

        while (students.next()) {
          ResultSet marks = database.getMarks(students.getString(1));

          while (marks.next()) {
            HBox row = new HBox();
            row.setSpacing(15);
            row.setPadding(new Insets(10, 10, 10, 10));

            CustomText id = new CustomText();
            CustomText markSub = new CustomText();
            CustomText markVal = new CustomText();

            id.setText(students.getString(1));
            id.setMinWidth(120);
            markSub.setText(marks.getString(2));
            markSub.setMinWidth(120);
            markVal.setText(marks.getString(3));
            markVal.setMinWidth(120);

            row.getChildren().clear();
            row.getChildren().addAll(id, markSub, markVal);
            tableBody.getChildren().add(row);
          }

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

    return view;
  }

  public static void setID(String newID) {
    id = newID;
  }
}
