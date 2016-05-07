package mancala;

import java.io.IOException;

/** Mancala GUI tester
 * @author Janice Kim, Shlomo Nazarian, Justin Passanisi
 */
public class MancalaTest {

    /** main()
     * @param args the command line arguments
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException
    {
        DesignLayout[] designs = {new DesignOne(), new DesignTwo()};
        MancalaComponent mancala = new MancalaComponent(designs);
    }
    
    
}
