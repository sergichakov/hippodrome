import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
class HippodromeTest {
    @Test
    void should_throwIllegalArgumentException_when_horsesIsNull_Hippodrome() {
        assertThrows(IllegalArgumentException.class, ()-> new Hippodrome(null));
    }
    @Test
    void should_throwExceptionWithMessage_when_horsesIsNull_Hippodrome(){
        String exceptString="";
        try{
            new Hippodrome(null);
        }catch(Exception e){
            exceptString=e.getMessage();
        }
        assertEquals("Horses cannot be null.",exceptString);
    }
    @Test
    void should_throwIllegalArgumentException_when_horsesIsEmpty_Hippodrome() {
        assertThrows(IllegalArgumentException.class,
                ()-> new Hippodrome(new ArrayList<Horse>()));
    }
    @Test
    void should_throwExceptionWithMessage_when_horsesIsEmpty_Hippodrome(){
        String exceptString="";
        try{
            new Hippodrome(new ArrayList<Horse>());
        }catch(Exception e){
            exceptString=e.getMessage();
        }
        assertEquals("Horses cannot be empty.",exceptString);
    }
    @Test
    void should_GivenHorsesEqualsGottenHorses_getHorses(){
        List<Horse> horses=new ArrayList<>();
        for(int k=1;k<=30;k++){
            horses.add(new Horse("Horsy_"+k,1));
        }
        Hippodrome hip=new Hippodrome(horses);
        List<Horse> retHorses= hip.getHorses();
        assertEquals(horses.size(),retHorses.size());
        for (int k=0;k<30;k++){
            assertEquals(horses.get(k),retHorses.get(k));
        }
    }
    @Test
    void should_callEvery_horseMoveInGivenList_move(){
        List<Horse> horses=new ArrayList<>();
        for(int k=1;k<=30;k++){
            Horse horse=mock(Horse.class);
            horses.add(horse);//new Horse("Horsy_"+k,1));
        }
        Hippodrome hip=new Hippodrome(horses);
        hip.move();
        for(Horse horse:horses){
            verify(horse,times(1)).move();
        }
    }
    @Test
    void should_getWinner_return_HorseWithLongestDistance(){
        List<Horse> horses=new ArrayList<>();
        for(int k=1;k<=30;k++){
            Horse horse=new Horse("Horsy"+k,1.0);
            horses.add(horse);//new Horse("Horsy_"+k,1));
        }
        Hippodrome hippodrome=new Hippodrome(horses);
        Horse win=horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();
        assertEquals(win,hippodrome.getWinner());
    }
}