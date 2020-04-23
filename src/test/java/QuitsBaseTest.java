import Global.Configuration;
import Global.Properties;
import Modele.GameManager;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class QuitsBaseTest {

    @Test
    public void TestConfiguration() {
        try{
            Properties.Load();
            assertNotNull(Configuration.Lis("Maximized"));
        }
        catch (Exception e){
            fail();
        }
        System.out.println("Configuration OK");
    }

    @Test
    public void TestInstanciationModele() {
        GameManager.InstanceGame();
        System.out.println("Modele Instanciation OK");
    }

}