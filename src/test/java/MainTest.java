import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.timeout;

class MainTest {
    @Timeout(22)
    @Disabled
    @Test
    void main() {
        Main main=new Main();try {

            Main.main(new String[1]);
        }catch(Exception e){
            String mes=e.getMessage();
            System.out.println(mes);
        }
    }
    //@Timeout("junit.jupiter.execution.timeout.thread.mode.default")
}