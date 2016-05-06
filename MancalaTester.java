package mancala;

/** Mancala GUI tester
 * @author Janice Kim, Shlomo Nazarian, Justin Passanisi
 */
public class MancalaTester {

    /** main()
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        DesignLayout[] designs = {new DesignOne(), new DesignTwo()};
        MancalaComponent mancala = new MancalaComponent(designs);
    }
    
    
}
