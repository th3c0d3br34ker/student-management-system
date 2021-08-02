package app.components;

import java.io.FileInputStream;

import javafx.scene.image.Image;

public class CustomImage {

  public static Image getImage(String src) {
    Image image = null;
    try {
      image = new Image(new FileInputStream(src));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return image;
  }
}
