
import javafx.scene.control.Button;

/**
 *
 * @author McCracken
 */
public class ConnectButton extends Button {
    private int row;
    private int column;
    
    /***
     * the buttons base information
     * @param label red, black, or empty
     * @param row 
     * @param column 
     */
    public ConnectButton (String label, int row, int column){
        super(label);
        this.row = row;
        this.column = column;
        
    }
    
    /***
     * The row that was passed
     * @return 
     */
    public int getRow(){
        return this.row;
    }
    
    /***
     * The column that was passed
     * @return 
     */
    public int getColumn(){
        return this.column;
    }
    
    /***
     * the format of string
     * @return 
     */
    @Override
    public String toString(){
        String format = new String();
        format = "(<" + getRow() + ">,<" + getColumn() + ">)";
        return format;
    }
}
