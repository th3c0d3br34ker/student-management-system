package app.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class CustomText extends Label {

  public CustomText() {
    super();
    setPadding(new Insets(8));
    setTextFill(Color.rgb(255, 255, 255));
    setBackground(new Background(new BackgroundFill(Color.rgb(47, 46, 65), new CornerRadii(8), Insets.EMPTY)));
  }

  public CustomText(String label) {
    super(label);
    setPadding(new Insets(8));
    setTextFill(Color.rgb(255, 255, 255));
    setBackground(new Background(new BackgroundFill(Color.rgb(47, 46, 65), new CornerRadii(8), Insets.EMPTY)));
  }

}
