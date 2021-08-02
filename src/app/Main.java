package app;

import java.util.ArrayList;
import java.util.List;

import app.containers.LandingScene;
import app.containers.instructor.FacultyAddAttendanceScene;
import app.containers.instructor.FacultyAddMarksScene;
import app.containers.instructor.FacultyDashboardScene;
import app.containers.instructor.FacultyLoginScene;
import app.containers.instructor.FacultyRegisterScene;
import app.containers.student.StudentDashboardScene;
import app.containers.student.StudentLoginScene;
import app.containers.student.StudentRegisterScene;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    private static StackPane rootPane;
    private static List<HBox> views = new ArrayList<HBox>();
    private static int curr_idx = 0;

    public static void main(String[] args) {
        System.out.println("Application Started!");
        views.add(LandingScene.getView()); // 0

        views.add(StudentLoginScene.getView()); // 1
        views.add(FacultyLoginScene.getView()); // 2

        views.add(StudentRegisterScene.getView()); // 3
        views.add(StudentDashboardScene.getView()); // 4

        views.add(FacultyRegisterScene.getView()); // 5
        views.add(FacultyDashboardScene.getView()); // 6

        views.add(FacultyAddAttendanceScene.getView()); // 7
        views.add(FacultyAddMarksScene.getView()); // 8
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            rootPane = new StackPane();
            rootPane.getChildren().addAll(views.get(0));
            Scene scene = new Scene(rootPane, 640, 400);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Student-Faculty Manager");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setScene(int idx, HBox scene) {
        views.set(idx, scene);
        rootPane.getChildren().removeAll(views.get(curr_idx));
        rootPane.getChildren().addAll(views.get(idx));
        curr_idx = idx;
    }
}
