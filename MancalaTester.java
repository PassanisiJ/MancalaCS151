package mancala;

/**
 *
 * @author 
 */
public class MancalaTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        DesignLayout[] designs = {new DesignOne(), new DesignTwo()};
		MancalaComponent mancala = new MancalaComponent(designs);
    }
    
}
