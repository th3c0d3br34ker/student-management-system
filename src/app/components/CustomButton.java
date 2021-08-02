package app.components;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class CustomButton extends Button {
  public CustomButton(String label) {
    super(label);
    setTextFill(Paint.valueOf("#FFFFFF"));
    setPadding(new Insets(8));
    setBackground(new Background(new BackgroundFill(Color.rgb(47, 46, 65), new CornerRadii(8), Insets.EMPTY)));
  }

  public void setBackgroundColor(int red, int green, int blue) {
    setBackground(new Background(new BackgroundFill(Color.rgb(red, green, blue), new CornerRadii(8), Insets.EMPTY)));
  }
}
