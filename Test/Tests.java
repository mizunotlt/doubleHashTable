import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;


public class Tests {
    @Test
    public void Put(){
        int key1 = 2;
        int value1  = 34;
        int key2 = 0;
        int value2 = 123;
        DoubleHashTable<Integer, Integer> table1 = new DoubleHashTable();
        table1.put(key1,value1);
        table1.put(key2, value2);
        assertEquals(2, table1.size());
    }
}
