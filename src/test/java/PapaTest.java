import appline.Init;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class PapaTest {


    @BeforeEach
    void startUp() {
       Init.initWebdriver();
    }

    @AfterEach
    void tearDown() {
       appline.Init.getDriver().quit();
    }

}
