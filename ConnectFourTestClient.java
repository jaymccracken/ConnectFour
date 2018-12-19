
import java.util.Scanner;

/**
 *
 * @author McCracken
 */

public class ConnectFourTestClient {
     public static void main(String args[]) {
        ConnectFourGame game = new ConnectFourGame(ConnectFourEnum.RED);
        Scanner scanner = new Scanner(System.in);                   //create a new scanner
        do {
            System.out.println(game.toString());                    //print the game board
            System.out.println(game.getTurn() +                     //have player take turn by inputing values
                ": Where do you want to put your token? Enter row column");
            int row = scanner.nextInt();                            //scan for value
            int column = scanner.nextInt();                         //scan for value
            scanner.nextLine();
            game.takeTurn(row, column);                             //have the new player take turn
            
        } while (game.getGameState() == ConnectFourEnum.IN_PROGRESS); //the gamestate is still inprogress
        System.out.println( game.getGameState());                   //print the end result when it is not inprogress
       
    }
}
