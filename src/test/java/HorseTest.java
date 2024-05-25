import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;

class HorseTest {

    @Test
    void should_throwIllegalArgumentException_when_nameIsNull_Horse() {
        assertThrows(IllegalArgumentException.class, ()-> new Horse(null,1));
    }
    @Test
    void should_throwExceptionWithString_when_nameIsNull_Horse(){
        String exceptString="";
        try{
            new Horse(null,1);
        }catch(Exception e){
            exceptString=e.getMessage();
        }
        assertEquals("Name cannot be null.",exceptString);
    }
    @ParameterizedTest
    @ValueSource(strings = {" ", " \t", " \n" , "\t\t ", "\n\n"})
    void should_throwIllegalArgumentException_when_nameIsSpace_Horse(String whitespace){
        assertThrows(IllegalArgumentException.class, ()-> new Horse(whitespace,1));
    }
    @Test
    void should_throwExceptionWithString_when_nameIsBlank_Horse(){
        String whitespace="";
        String exceptString="";
        try{
            new Horse(whitespace,1);
        }catch(Exception e){
            exceptString=e.getMessage();
        }
        assertEquals("Name cannot be blank.",exceptString);
    }
    @Test
    void should_throwIllegalArgumentException_when_speedIsNegative_Horse(){
        double negative=-1.1;
        assertThrows(IllegalArgumentException.class, ()-> new Horse("st",negative));
    }
    @Test
    void should_throwExceptionWithMessage_when_speedIsNegative_Horse(){double negative=-22.3;//){
        String exceptMes="";
        try{
            new Horse("Horsy", negative);
        }catch(Exception e){
            exceptMes=e.getMessage();
        }
        assertEquals("Speed cannot be negative.",exceptMes);
    }
    @Test
    void should_throwIllegalArgumentException_when_distanceIsNegative_Horse(){
        double negative=-2.3;
        assertThrows(IllegalArgumentException.class, ()-> new Horse("st",1.0,negative));
    }
    @Test
    void should_throwExceptionWithMessage_when_distanceIsNegative_Horse(){
        double negative=-398.6;
        String exceptMes="";
        try{
            new Horse("Horsy",1.0,negative);
        }catch(Exception e){
            exceptMes=e.getMessage();
        }
        assertEquals("Distance cannot be negative.",exceptMes);
    }
    @Test
    void should_returnString_as_Horse_name_getName(){
        String name="Horsy";
        Horse horse=new Horse(name,1);
        assertEquals(name,horse.getName());
    }
    @Test
    void should_returnSameSpeed_as_Horse_speed_getSpeed(){
        double speed=3.0;
        Horse horse=new Horse("name",speed);
        assertEquals(speed,horse.getSpeed());
    }
    @Test
    void should_returnSameDistance_as_Horse_distance_getDistance(){
        double distance=3.0;
        Horse horse=new Horse("name",1.0,distance);
        assertEquals(distance,horse.getDistance());
    }
    @Test
    void should_call_getRandomDouble_gottenFrom_Horse_move(){
        Horse horse=new Horse("horsy",1.0,1.0);
        try(MockedStatic<Horse> mocked=mockStatic(Horse.class)){
            horse.move();
            mocked.verify(()->Horse.getRandomDouble(0.2,0.9),times(1));
        }
    }
    @ParameterizedTest
    @CsvSource({"0.7,1.7", "0.35,1.35", "0.035,1.035" })
    void should_call_getRandomDouble_andReturnDistance_from_Horse_move(
            double retDouble,double afterMove) throws IllegalAccessException, NoSuchFieldException {
        Horse horse=new Horse("horsy",1.0,1.0);
        try(MockedStatic<Horse> mocked=mockStatic(Horse.class)){
            mocked.when(()->Horse.getRandomDouble(0.2,0.9)).thenReturn(retDouble);
            horse.move();
            Field nameRef=Horse.class.getDeclaredField("distance");
            nameRef.setAccessible(true);
            double nameValue=(double)nameRef.get(horse);
            assertEquals(afterMove,horse.getDistance());

        }
    }
}