package app.components;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class CustomScrollPane extends ScrollPane {

  public CustomScrollPane(Node node) {
    super(node);
    setFitToHeight(true);
    setFitToWidth(true);
    setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
  }
}
