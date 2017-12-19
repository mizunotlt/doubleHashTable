import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;


public class Tests {
    //Тест добавления элементов в таблицу
    @Test
    public void put(){

        int key1 = 2;
        int value1  = 34;
        int key2 = 0;
        int value2 = 123;
        int key3 = 12;
        int value3 = 1000;
        int key4 = 8;
        int value4 = 345;
        int key5 = 5;
        int value5 = 345;
        DoubleHashTable<Integer, Integer> table1 = new DoubleHashTable();
        table1.put(key1,value1);
        table1.put(key2, value2);
        table1.put(key3, value3);
        table1.put(key4, value4);
        table1.put(key5, value5);
        assertEquals(5, table1.size());
        assertEquals(null ,table1.put(key2, value2));

        DoubleHashTable<Integer, String> table2 = new DoubleHashTable();

        int keys1 = 2;
        String word1  = "34";
        int keys2 = 0;
        String word2 = "123";
        int keys3 = 12;
        String word3 = "123";
        table2.put(keys1,word1);
        table2.put(keys2, word2);
        table2.put(keys3, word3);
        assertEquals(3, table2.size());
    }

    @Test
    public void get(){

        int key1 = 2;
        int value1  = 34;
        int key2 = 0;
        int value2 = 123;
        int key3 = 12;
        int value3 = 1000;
        int key4 = 8;
        int value4 = 345;
        int key5 = 5;
        int value5 = 345;
        DoubleHashTable<Integer, Integer> table1 = new DoubleHashTable();
        table1.put(key1,value1);
        table1.put(key2, value2);
        table1.put(key3, value3);
        table1.put(key4, value4);
        table1.put(key5, value5);

        int get1 =  table1.get((Object)key4);

        assertEquals(value4, get1);

        DoubleHashTable<Integer, String> table2 = new DoubleHashTable();

        int keys1 = 2;
        String word1  = "34";
        int keys2 = 0;
        String word2 = "123";
        int keys3 = 12;
        String word3 = "12";
        table2.put(keys1,word1);
        table2.put(keys2, word2);
        table2.put(keys3, word3);
        String get2 = table2.get((Object) keys2);
        assertEquals("123", get2 );
    }

    @Test
    public void findKey(){

        int key1 = 2;
        int value1  = 34;
        int key2 = 0;
        int value2 = 123;
        int key3 = 12;
        int value3 = 1000;
        int key4 = 8;
        int value4 = 345;
        int key5 = 5;
        int value5 = 345;
        DoubleHashTable<Integer, Integer> table1 = new DoubleHashTable();
        table1.put(key1,value1);
        table1.put(key2, value2);
        table1.put(key3, value3);
        table1.put(key4, value4);
        table1.put(key5, value5);

        int get1 =  table1.findKey(key4);
        assertEquals(key4, get1);

        DoubleHashTable<Integer, String> table2 = new DoubleHashTable();

        int keys1 = 2;
        String word1  = "34";
        int keys2 = 0;
        String word2 = "123";
        int keys3 = 12;
        String word3 = "123";
        table2.put(keys1,word1);
        table2.put(keys2, word2);
        table2.put(keys3, word3);

        int get2 = table2.findKey(keys2);
        assertEquals(key2, get2 );
    }

    @Test
    public void remove(){

        int key1 = 2;
        int value1  = 34;
        int key2 = 0;
        int value2 = 123;
        int key3 = 12;
        int value3 = 1000;
        int key4 = 8;
        int value4 = 345;
        int key5 = 5;
        int value5 = 345;
        DoubleHashTable<Integer, Integer> table1 = new DoubleHashTable();
        table1.put(key1,value1);
        table1.put(key2, value2);
        table1.put(key3, value3);
        table1.put(key4, value4);
        table1.put(key5, value5);

        int valueRemove =  table1.remove((Object) key4);
        assertEquals(value4 , valueRemove);
        assertEquals(4, table1.size());

        DoubleHashTable<Integer, String> table2 = new DoubleHashTable();

        int keys1 = 2;
        String word1  = "34";
        int keys2 = 0;
        String word2 = "123";
        int keys3 = 12;
        String word3 = "123";
        table2.put(keys1,word1);
        table2.put(keys2, word2);
        table2.put(keys3, word3);

        String valueRemoves =  table2.remove((Object) key4);
        assertEquals(word2 , valueRemove);
        assertEquals(2, table1.size());
    }
}
