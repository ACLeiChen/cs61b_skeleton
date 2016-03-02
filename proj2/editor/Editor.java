package editor;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.LinkedList;
//cannot use the TextFlow, TextArea, TextInputControl, or HTMLEditor classes

public class Editor extends Application {
    private final Rectangle textBoundingBox;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;

    private static final int STARTING_FONT_SIZE = 20;
    private static final int STARTING_TEXT_POSITION_X = 250;
    private static final int STARTING_TEXT_POSITION_Y = 250;
    private int fontSize = STARTING_FONT_SIZE;
    private String fontName = "Verdana";

    Group root;
    private TextList textList;
    private int cursorX;
    private int cursorY;


    public Editor() {
        // Create a rectangle to surround the text that gets displayed.  Initialize it with a size
        // of 0, since there isn't any text yet.
        root = new Group();
        textBoundingBox = new Rectangle(0, 0);
        /**Storing the textList of the document. Initialize it with an empty LinkedList.*/
        textList = new TextList();
        cursorX = 0;
        cursorY = 0;
        
    }
    /**
     * An EventHandler to handle keys that get pressed.
     */
    private class KeyEventHandler implements EventHandler<KeyEvent> {
        int textCenterX;
        int textCenterY;

        /*****
         * private Text displayText = new Text(STARTING_TEXT_POSITION_X, STARTING_TEXT_POSITION_Y, "");
         *****/
        KeyEventHandler(final Group root, int windowWidth, int windowHeight) {
            textCenterX = 0;
            textCenterY = 0;

            /***********
             // Initialize some empty text and add it to root so that it will be displayed.
             displayText = new Text(textCenterX, textCenterY, "");
             // Always set the text origin to be VPos.TOP! Setting the origin to be VPos.TOP means
             // that when the text is assigned a y-position, that position corresponds to the
             // highest position across all letters (for example, the top of a letter like "I", as
             // opposed to the top of a letter like "e"), which makes calculating positions much
             // simpler!
             displayText.setTextOrigin(VPos.TOP);
             displayText.setFont(Font.font(fontName, fontSize));

             // All new Nodes need to be added to the root in order to be displayed.
             root.getChildren().add(displayText);
             ***********/
        }

        @Override
        public void handle(KeyEvent keyEvent) {
            if (keyEvent.getEventType() == KeyEvent.KEY_TYPED) {
                // Use the KEY_TYPED event rather than KEY_PRESSED for letter keys, because with
                // the KEY_TYPED event, javafx handles the "Shift" key and associated
                // capitalization.
                String characterTyped = keyEvent.getCharacter();
                if (characterTyped.length() > 0 && characterTyped.charAt(0) != 8) {
                    // Ignore control keys, which have non-zero length, as well as the backspace
                    // key, which is represented as a character of value = 8 on Windows.
                    Text typedText = new Text(characterTyped);
                    typedText.setTextOrigin(VPos.TOP);
                    typedText.setFont(Font.font(fontName, fontSize));
                    //store this text in the textList
                    textList.insert(typedText, root);
                    keyEvent.consume();
                }
                //render all the text
                textList.renderText(WINDOW_WIDTH, fontName, fontSize);
                renderBlinkingCursor();
            } else if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
                // Arrow keys should be processed using the KEY_PRESSED event, because KEY_PRESSED
                // events have a code that we can check (KEY_TYPED events don't have an associated
                // KeyCode).
                KeyCode code = keyEvent.getCode();
                if (code == KeyCode.UP) {
                    if (textList.size() != 0) {
                        fontSize += 5;
                        textList.renderText(WINDOW_WIDTH, fontName, fontSize);
                        renderBlinkingCursor();
                    }
                } else if (code == KeyCode.DOWN) {
                    if (textList.size() != 0) {
                        fontSize = Math.max(0, fontSize - 5);
                        textList.renderText(WINDOW_WIDTH, fontName, fontSize);
                        renderBlinkingCursor();
                    }
                }
                if (code == KeyCode.BACK_SPACE) {
                    if (textList.size() != 0) {
                        textList.delete(root);
                        textList.renderText(WINDOW_WIDTH, fontName, fontSize);
                        renderBlinkingCursor();
                    }
                }
            }
        }
        private void handleDownArrow() {

        }
        private void handleUpArrow() {

        }
        private void handleLeftArrow() {

        }
        private void handleRightArrow() {

        }
        private void handleClick(int cursorX, int cursorY) {

        }

        private void renderBlinkingCursor() {
            if (textList.size() == 0) {
                // For empty list, the position is the upper left hand corner of the screen.
                textBoundingBox.setX(0);
                textBoundingBox.setY(0);
            }else{
                Text currentText = textList.getCurrentText();
                // Figure out the size of the current text.
                double textHeight = currentText.getLayoutBounds().getHeight();
                double textWidth = currentText.getLayoutBounds().getWidth();
                double textPostionX = currentText.getX();
                double textPostionY = currentText.getY();
                // Re-size and re-position the bounding box.
                textBoundingBox.setHeight(textHeight);
                textBoundingBox.setWidth(1);
                // For rectangles, the position is the upper left hand corner.
                textBoundingBox.setX(textPostionX + textWidth);
                textBoundingBox.setY(textPostionY);
            }
            // Many of the JavaFX classes have implemented the toString() function, so that
            // they print nicely by default.
            System.out.println("Bounding box: " + textBoundingBox);
        }
    }
    /** An EventHandler to handle changing the color of the rectangle. */
    private class RectangleBlinkEventHandler implements EventHandler<ActionEvent> {
        private int currentColorIndex = 0;
        private Color[] boxColors = {Color.BLACK, Color.TRANSPARENT};

        RectangleBlinkEventHandler() {
            // Set the color to be the first color in the list.
            changeColor();
        }

        private void changeColor() {
            textBoundingBox.setFill(boxColors[currentColorIndex]);
            currentColorIndex = (currentColorIndex + 1) % boxColors.length;
        }

        @Override
        public void handle(ActionEvent event) {
            changeColor();
        }
    }

    /** Makes the text bounding box change color periodically. */
    public void makeRectangleColorChange() {
        // Create a Timeline that will call the "handle" function of RectangleBlinkEventHandler
        // every 1 second.
        final Timeline timeline = new Timeline();
        // The rectangle should continue blinking forever.
        timeline.setCycleCount(Timeline.INDEFINITE);
        RectangleBlinkEventHandler cursorChange = new RectangleBlinkEventHandler();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), cursorChange);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }


    @Override
    public void start(Stage primaryStage) {
        // Create a Node that will be the parent of all things displayed on the screen.
        //Group root = new Group();
        // The Scene represents the window: its height and width will be the height and width
        // of the window displayed.
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);

        // To get information about what keys the user is pressing, create an EventHandler.
        // EventHandler subclasses must override the "handle" function, which will be called
        // by javafx.
        EventHandler<KeyEvent> keyEventHandler =
                new KeyEventHandler(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        // Register the event handler to be called for all KEY_PRESSED and KEY_TYPED events.
        scene.setOnKeyTyped(keyEventHandler);
        scene.setOnKeyPressed(keyEventHandler);

        // All new Nodes need to be added to the root in order to be displayed.
        root.getChildren().add(textBoundingBox);
        makeRectangleColorChange();

        primaryStage.setTitle("Editor");
        // This is boilerplate, necessary to setup the window where things are displayed.
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
