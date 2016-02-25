package editor;

import javafx.application.Application;
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

    private LinkedList<Text> textList;
    private LinkedList<Double> xrenderList;
    private LinkedList<Double> yrenderList;
    

    public Editor() {
        // Create a rectangle to surround the text that gets displayed.  Initialize it with a size
        // of 0, since there isn't any text yet.
        textBoundingBox = new Rectangle(0, 0);
        /**Storing the textList of the document. Initialize it with an empty LinkedList.*/
        textList = new LinkedList<Text>();

        
    }
    /** An EventHandler to handle keys that get pressed. */
    private class KeyEventHandler implements EventHandler<KeyEvent> {
        int textCenterX;
        int textCenterY;

        final Group rootThis;
        /** The Text to display on the screen. */
        /*****
        private Text displayText = new Text(STARTING_TEXT_POSITION_X, STARTING_TEXT_POSITION_Y, "");
        *****/
        KeyEventHandler(final Group root, int windowWidth, int windowHeight) {
            textCenterX = 0;
            textCenterY = 0;
            rootThis = root;
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
                    // add this text to the root each time when new text is typed
                    rootThis.getChildren().add(typedText);
                    //store this text in the textList
                    textList.addLast(typedText);
                    keyEvent.consume();
                }
                //render all the text
                renderEngine();
                
            } else if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
                // Arrow keys should be processed using the KEY_PRESSED event, because KEY_PRESSED
                // events have a code that we can check (KEY_TYPED events don't have an associated
                // KeyCode).
                KeyCode code = keyEvent.getCode();
                if (code == KeyCode.UP) {
                    fontSize += 5;
                    renderEngine();
                } else if (code == KeyCode.DOWN) {
                    fontSize = Math.max(0, fontSize - 5);
                    renderEngine();
                }
                if (code == KeyCode.BACK_SPACE) {
                    textList.removeLast();
                    renderEngine();
                }
            }
        }

        /**update all the rendering information when it's called*/
        private void renderEngine() {
            /**clear all the existed rendering information,
             * by initiate xrenderList and yrenderList instead of in the Editor constructor */
            xrenderList = new LinkedList<Double>();
            yrenderList = new LinkedList<Double>();

            /**update the rendering information*/
            int characterNumber = textList.size();
            //int rowNumber = (int) Math.round((double) characterNumber / WINDOW_WIDTH);

            //for the first character in the first row
            Text currentText = textList.getFirst();
            // Calculate the position so that the text will be at top left on the screen.
            double textTop = 0;
            double textLeft = 0;
            xrenderList.addLast(textLeft);
            yrenderList.addLast(textTop);
            currentText.setX(textLeft);
            currentText.setY(textTop);
            currentText.setTextOrigin(VPos.TOP);
            currentText.setFont(Font.font(fontName, fontSize));
            double textHeight = currentText.getLayoutBounds().getHeight();
            double textWidth = currentText.getLayoutBounds().getWidth();
            xrenderList.addLast(textWidth);
            yrenderList.addLast(textTop);
            currentText.toFront();

            double currentWidth = textWidth;
            double currentHeight = textTop;
            //for the rest text
            int textIndex = 1;
            while (textIndex < characterNumber) {
                currentText = textList.get(textIndex);
                currentText.setX(xrenderList.get(textIndex));
                currentText.setY(yrenderList.get(textIndex));
                currentText.setTextOrigin(VPos.TOP);
                currentText.setFont(Font.font(fontName, fontSize));
                textWidth = currentText.getLayoutBounds().getWidth();
                textHeight = currentText.getLayoutBounds().getHeight();
                currentWidth += textWidth;
                currentHeight = textTop;
                xrenderList.addLast(currentWidth);
                yrenderList.addLast(currentHeight);
                textIndex += 1;
            }
            /**layout all the text based on these undated rendering information*/

        }
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a Node that will be the parent of all things displayed on the screen.
        Group root = new Group();
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




        
        primaryStage.setTitle("Editor");

        // This is boilerplate, necessary to setup the window where things are displayed.
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
