/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author jmccr
 */
public class ConnectFourApplication extends Application implements Observer{
    
    public final static int NUM_COLUMNS = 8;
    public final static int NUM_ROWS = 8;
    public final static int NUM_TO_WIN = 4;
    public final static int BUTTON_SIZE = 20;
    
    private ConnectFourGame gameEngine = new ConnectFourGame(NUM_ROWS, NUM_COLUMNS, NUM_TO_WIN, ConnectFourEnum.RED );
    private ConnectButton[][] buttons;
    int[] rowCol = new int[2];
    
    /***
     * Setting up all of the GUI, the buttons, text fields and the windows. 
     * The first row on the bottom
     * 
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
        gameEngine.addObserver(this);
        buttons = new ConnectButton[NUM_ROWS] [NUM_COLUMNS];
        
        for(int i = 0 ; i < buttons.length; i++){
           for (int j = 0; j < buttons[i].length; j++){
               buttons[i][j] = new ConnectButton(ConnectFourEnum.EMPTY.toString(), i, j);
           } 
        }
        
        ButtonHandler handler = new ButtonHandler(gameEngine, rowCol);
        Alert end = new Alert(Alert.AlertType.INFORMATION);
        
        BorderPane root = new BorderPane();
        GridPane board = new GridPane();
        TextField turnText = new TextField();
        
        turnText.setEditable(false);
        
        if (gameEngine.getTurn() == ConnectFourEnum.RED){
            turnText.setText("RED Begins");
        }
        else if (gameEngine.getTurn() == ConnectFourEnum.BLACK){
            turnText.setText("BLACK Begins");
        }
        
        Pane row1 = new HBox();
        Pane row2 = new HBox();
        Pane row3 = new HBox();
        Pane row4 = new HBox();
        Pane row5 = new HBox();
        Pane row6 = new HBox();
        Pane row7 = new HBox();
        Pane row8 = new HBox();
        
        Button pick = new Button ("Take My Turn");
        pick.setOnAction(new EventHandler<ActionEvent>() {
            
            /***
             * controlling what the text fields say based on differnt events and when the game ends
             * @param event 
             */
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Drop The Checker");
                gameEngine.takeTurn(rowCol[0], rowCol[1]);
                
                if (gameEngine.getTurn() == ConnectFourEnum.RED){
                    turnText.setText("It's RED's Turn");
                }
                else if (gameEngine.getTurn() == ConnectFourEnum.BLACK){
                    turnText.setText("It's BLACK's Turn");
                }
                
                if (gameEngine.getGameState() != ConnectFourEnum.IN_PROGRESS){
                    end.setTitle("Information Alert");
                    end.setHeaderText("Game Over");
                    
                    if (gameEngine.getGameState() == ConnectFourEnum.RED){
                        end.setContentText("RED WINS");
                        end.showAndWait();
                        gameEngine.reset(ConnectFourEnum.BLACK);
                        for(int i = 0 ; i < buttons.length; i++){
                             for (int j = 0; j < buttons[i].length; j++){
                                buttons[i][j] = new ConnectButton(ConnectFourEnum.EMPTY.toString(), i, j);
                            } 
                        } 
                        turnText.setText("BLACK BEGINS");
                    }
                    
                    else if (gameEngine.getGameState() == ConnectFourEnum.BLACK){
                        end.setContentText("BLACK WINS");
                        end.showAndWait();
                        gameEngine.reset(ConnectFourEnum.RED);
                        for(int i = 0 ; i < buttons.length; i++){
                             for (int j = 0; j < buttons[i].length; j++){
                                buttons[i][j] = new ConnectButton(ConnectFourEnum.EMPTY.toString(), i, j);
                            } 
                        }
                        turnText.setText("RED Begins");
                    }
                }
            }
        });
        
        for (int i =0; i < buttons.length; i++){
            buttons[0][i].setMinHeight(20.0);
            buttons[0][i].setMinWidth(63.0);
            buttons[0][i].setMaxWidth(63.0);
            buttons[0][i].setOnAction(handler);
            row1.getChildren().add(buttons[0][i]);
        }
        for (int i =0; i < buttons.length; i++){
            buttons[1][i].setMinHeight(20.0);
            buttons[1][i].setMinWidth(63.0);
            buttons[1][i].setMaxWidth(63.0);
            buttons[1][i].setOnAction(handler);
            row2.getChildren().add(buttons[1][i]);
        }
        for (int i =0; i < buttons.length; i++){
            buttons[2][i].setMinHeight(20.0);
            buttons[2][i].setMinWidth(63.0);
            buttons[2][i].setMaxWidth(63.0);
            buttons[2][i].setOnAction(handler);
            row3.getChildren().add(buttons[2][i]);
        }
        for (int i =0; i < buttons.length; i++){
            buttons[3][i].setMinHeight(20.0);
            buttons[3][i].setMinWidth(63.0);
            buttons[3][i].setMaxWidth(63.0);
            buttons[3][i].setOnAction(handler);
            row4.getChildren().add(buttons[3][i]);
        }
        for (int i =0; i < buttons.length; i++){
            buttons[4][i].setMinHeight(20.0);
            buttons[4][i].setMinWidth(63.0);
            buttons[4][i].setMaxWidth(63.0);
            buttons[4][i].setOnAction(handler);
            row5.getChildren().add(buttons[4][i]);
        }
        for (int i =0; i < buttons.length; i++){
            buttons[5][i].setMinHeight(20.0);
            buttons[5][i].setMinWidth(63.0);
            buttons[5][i].setMaxWidth(63.0);
            buttons[5][i].setOnAction(handler);
            row6.getChildren().add(buttons[5][i]);
        }
        for (int i =0; i < buttons.length; i++){
            buttons[6][i].setMinHeight(20.0);
            buttons[6][i].setMinWidth(63.0);
            buttons[6][i].setMaxWidth(63.0);
            buttons[6][i].setOnAction(handler);
            row7.getChildren().add(buttons[6][i]);
        }
        for (int i =0; i < buttons.length; i++){
            buttons[7][i].setMinHeight(20.0);
            buttons[7][i].setMinWidth(63.0);
            buttons[7][i].setMaxWidth(63.0);
            buttons[7][i].setOnAction(handler);
            row8.getChildren().add(buttons[7][i]);
        }
        
        board.addRow(0, row8);
        board.addRow(1, row7);
        board.addRow(2, row6);
        board.addRow(3, row5);
        board.addRow(4, row4);
        board.addRow(5, row3);
        board.addRow(6, row2);
        board.addRow(7, row1);
        
        root.setTop(turnText);
        root.setCenter(board);
        root.setBottom(pick);
        Scene scene = new Scene(root, 510, 380);
        
        primaryStage.setTitle("ConnectFour");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /***
     * changing the board when it needs updating
     * @param o
     * @param arg 
     */
    public void update(Observable o, Object arg){
        buttons[rowCol[0]][rowCol[1]].setText(gameEngine.getTurn().toString());
    }
    
    class ButtonHandler implements EventHandler<ActionEvent>{
        private int[] rowCol;
        
        /***
         * setting the array 
         * @param game
         * @param rowCol 
         */
        public ButtonHandler(ConnectFourGame game, int[] rowCol){
            this.rowCol = rowCol;
        }
        
        /***
         * setting up the handler to do differnt things based on the event
         * @param event 
         */
        @Override
        public void handle (ActionEvent event){
            ConnectButton button = (ConnectButton) event.getSource();
            this.rowCol[0] = button.getRow();
            this.rowCol[1] = button.getColumn();
        }
    }
    
}
