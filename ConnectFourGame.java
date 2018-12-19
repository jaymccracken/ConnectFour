
import java.util.Observable;


/**
 *
 * @author McCracken
 */
public class ConnectFourGame extends Observable{
    
    private int nColumns;
    private int nRows;
    private int numToWin;
    private ConnectFourEnum[][] grid;
    private ConnectFourEnum gameState;
    private ConnectFourEnum turn;
    
    /***
     * setting up the game board constructor chaining
     * @param initialTurn 
     */
    public ConnectFourGame (ConnectFourEnum initialTurn){
        this(8,8,4,initialTurn);
    }
    
    /***
     * constructor for setting up board depending on the client
     * @param nRows to track how many rows
     * @param nColumns to track how many columns
     * @param numToWin to set how many in a row is needed
     * @param initialTurn who will be starting the game
     */
    public ConnectFourGame(int nRows, int nColumns, int numToWin, ConnectFourEnum initialTurn){
        this.nColumns = nColumns;
        this.nRows = nRows;
        this.numToWin = numToWin;
        grid = new ConnectFourEnum[nRows][nColumns];
        reset(initialTurn);
    }
    
    /***
     * set all of the places to empty and put the game back to in progress and the turn to the first player
     * @param initialTurn 
     */
    public void reset (ConnectFourEnum initialTurn){
        gameState = ConnectFourEnum.IN_PROGRESS;
        turn = initialTurn;
        for (int i = 0; i < grid.length ; i++){
            for (int j = 0; j < grid[i].length; j++){
                grid[i][j] = ConnectFourEnum.EMPTY;
            }
        }
    }
    
    /***
     * The base of the game, allowing players to place a checker in a spot and checks if they won or not
     * @param row what row they player whats it
     * @param column what column the player whats it
     * @return
     * @throws IllegalArgumentException if the player tries to place a checker when there is an empty space below it
     */
    public ConnectFourEnum takeTurn (int row, int column) throws IllegalArgumentException{
        ConnectFourEnum tokenColor;
        int rowCounter;
        int colCounter;
        if (grid[0][column] == ConnectFourEnum.EMPTY && row != 0){
            throw new IllegalArgumentException();
        }
        
        if (getTurn() == ConnectFourEnum.RED && getGameState() == ConnectFourEnum.IN_PROGRESS){
            tokenColor = ConnectFourEnum.RED;
            grid[row][column] = ConnectFourEnum.RED;
            setChanged();
            notifyObservers();
            int i = row;
            int j = column;
            colCounter = -1;
            rowCounter = 0;
            
            while (grid[i][j--] == tokenColor){
                colCounter ++;
                if (j == -1){
                    break;
                }
            }
            
            j = column;
             
            while (grid[i][j++] == tokenColor){
                 colCounter ++;
                 if (j == 8){
                    break;
                }
             }
            
            j = column;
            
            while (grid[i--][j] == tokenColor){
                 rowCounter ++;
                 if (i == -1){
                    break;
                }
             }

            if (colCounter >= numToWin){
                gameState = ConnectFourEnum.RED;
                return gameState;     
            }
            
            if (rowCounter >= numToWin){
                gameState = ConnectFourEnum.RED;
                return gameState;     
            }
            turn = ConnectFourEnum.BLACK;     
        }
        
        else if (getTurn() == ConnectFourEnum.BLACK && getGameState() == ConnectFourEnum.IN_PROGRESS){
            tokenColor = ConnectFourEnum.BLACK;
            grid[row][column] = ConnectFourEnum.BLACK;
            setChanged();
            notifyObservers();
            int i = row;
            int j = column;
            colCounter = -1;
            rowCounter = 0;
            
            while (grid[i][j--] == tokenColor){
                colCounter ++;
                if (j == -1){
                    break;
                }
            }
            
            j = column;
             
            while (grid[i][j++] == tokenColor){
                 colCounter ++;
                 if (j == 8){
                    break;
                }
             }
            
            j = column;
            
            while (grid[i--][j] == tokenColor){
                 rowCounter ++;
                 if (i == -1){
                    break;
                }
             }

            if (colCounter >= numToWin){
                gameState = ConnectFourEnum.BLACK;
                return gameState;     
            }
            
            if (rowCounter >= numToWin){
                gameState = ConnectFourEnum.BLACK;
                return gameState;     
            }
            turn = ConnectFourEnum.RED;     
        }
        
        return getTurn();
    }
    
    /***
     * returns the game state
     * @return 
     */
    public ConnectFourEnum getGameState(){
        return this.gameState;
    }
    
    /***
     * Returns who's turn it is
     * @return 
     */
    public ConnectFourEnum getTurn(){
        return this.turn;
    }
    
    /***
     * prints out the board the top being the first row
     * @return 
     */
    @Override
    public String toString(){
        String board = new String();
        for (int i = 0; i < nRows; i++){
            for (int j = 0; j < nColumns; j++){
               board = board + grid[i][j]+ " | ";
            }
            board = board+"\n";
        }
        return board;
    }
}
