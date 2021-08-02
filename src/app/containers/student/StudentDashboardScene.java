package app.containers.student;

import java.sql.ResultSet;

import app.components.CustomButton;
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

public class StudentDashboardScene {
  private static String id;

  public static HBox getView() {

    HBox view = new HBox();

    CustomButton exitButton = new CustomButton("Exit");
    exitButton.setBackgroundColor(255, 99, 71);

    Text errorText = new Text();

    CustomText idText = new CustomText(id);

    CustomText attendanceText = new CustomText();
    CustomText heading1 = new CustomText("Subject");
    CustomText heading2 = new CustomText("Scored Marks");
    heading1.setMinWidth(200);
    heading2.setMinWidth(200);

    HBox tableHeading = new HBox();
    tableHeading.setMinWidth(480);
    tableHeading.setSpacing(15);
    tableHeading.setPadding(new Insets(10, 10, 10, 10));
    tableHeading.getChildren().addAll(heading1, heading2);

    VBox tableBody = new VBox();
    tableHeading.setMinWidth(480);
    tableHeading.setSpacing(15);
    tableHeading.setPadding(new Insets(10, 10, 10, 10));
    tableBody
        .setBackground(new Background(new BackgroundFill(Color.rgb(108, 98, 255), new CornerRadii(10), Insets.EMPTY)));

    VBox textViewBox = new VBox();
    textViewBox.setMinWidth(480);
    textViewBox.setSpacing(15);
    textViewBox.setPadding(new Insets(10, 10, 10, 10));
    textViewBox.getChildren().addAll(tableHeading, tableBody);

    VBox vBox = new VBox();
    vBox.setAlignment(Pos.CENTER);
    vBox.setMinWidth(160);
    vBox.setSpacing(15);
    vBox.setPadding(new Insets(10, 10, 10, 10));
    vBox.setBackground(new Background(new BackgroundFill(Color.rgb(108, 98, 255), new CornerRadii(10), Insets.EMPTY)));
    vBox.getChildren().addAll(idText, attendanceText, errorText, exitButton);

    view.setAlignment(Pos.CENTER);
    view.setBackground(new Background(new BackgroundFill(Color.rgb(230, 245, 248), new CornerRadii(10), Insets.EMPTY)));
    view.getChildren().addAll(textViewBox, vBox);

    try {
      if (database.startConnection()) {
        ResultSet result;
        result = database.getMarks(id);

        while (result.next()) {
          CustomText markSub = new CustomText();
          CustomText markVal = new CustomText();

          markSub.setText(result.getString(2));
          markSub.setMinWidth(200);
          markVal.setText(result.getString(3));
          markVal.setMinWidth(200);

          HBox row = new HBox();
          row.setSpacing(15);
          row.setPadding(new Insets(10, 10, 10, 10));
          row.getChildren().addAll(markSub, markVal);
          tableBody.getChildren().addAll(row);
        }

        result = database.getAttendance(id);
        while (result.next()) {
          attendanceText.setText("Attendance : " + result.getString(2) + "%");
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

    exitButton.setOnAction(event -> {
      Platform.exit();
    });

    return view;
  }

  public static void setID(String newID) {
    id = newID;
  }
}
