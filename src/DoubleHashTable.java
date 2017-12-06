import java.util.Collection;
import java.util.Map;
import java.util.Set;


public class DoubleHashTable <E1, E2> implements Map<E1, E2> {

    private Element<E1, E2> doubleHashTable[];
    private int sizeOfTable = 2;
    private int countElements = 0;

    public DoubleHashTable(){
        this.doubleHashTable = new Element[sizeOfTable];
    }

    public DoubleHashTable(int sizeOfTable){
        this.sizeOfTable = sizeOfTable;
        this.doubleHashTable = new Element[sizeOfTable];
    }

    @Override
    public int size() {
        return countElements;
    }

    //Свободный индекс в таблице
    private boolean isFreeIndex(){
        for (int i = 0; i < sizeOfTable; i++){
            if (doubleHashTable[i] == null){
                return true;
            }
        }
        return false;
    }

    //Поисик ключа в таблице
    private E1 findKey(E1 key){
        for (int i = 0;  i < sizeOfTable; i++){
            if (doubleHashTable[i] != null){
                if (doubleHashTable[i].getKey().equals(key)){
                    return doubleHashTable[i].getKey();
                }
                else
                    return null;
            }
            else
                return null;
        }
        return null;
    }

    @Override
    public E2 put(E1 key, E2 value) {
        int hashFunResult1 = helpHashFun1(key);
        int hashFunResult2 = helpHashFun2(key);
        if (findKey(key) != null){
            return null;
        }
        if (!isFreeIndex())
            return null;
        if (hashFunResult1 > sizeOfTable)
            return null;
        if (hashFunResult2 > sizeOfTable)
            return null;
        for (int i = 0; i < sizeOfTable; i++){
            int hash = mainHashFun(hashFunResult1, hashFunResult2, i);
            if (doubleHashTable[hash] == null){
               doubleHashTable[hash] = new Element(key, value);
               countElements++;
               return doubleHashTable[hash].getValue();
            }
        }
        return null;
    }
    @Override
    public E2 get(Object key) {
        if(findKey((E1)key) == null)
            return null;
        int hashFunResult1 = helpHashFun1((E1)key);
        int hashFunResult2 = helpHashFun2((E1)key);
        for (int i = 0; i < sizeOfTable; i++){
            int hash = mainHashFun(hashFunResult1, hashFunResult2, i);
            if (doubleHashTable[hash].getKey().equals((E1)key)){
                return  doubleHashTable[hash].getValue();
            }
        }
        return null;
    }

    @Override
    public E2 remove(Object key) {
        E2 elementValue = get(key);
        if(findKey((E1)key) == null)
            return null;
        int hashFunResult1 = helpHashFun1((E1)key);
        int hashFunResult2 = helpHashFun2((E1)key);
        for (int i = 0; i < sizeOfTable; i++){
            int hash = mainHashFun(hashFunResult1, hashFunResult2, i);
            if (doubleHashTable[hash].getKey().equals((E1)key)){
                doubleHashTable[hash] = null;
                countElements--;
                return  elementValue;
            }
        }
        return null;
    }

    public int mainHashFun (int hash1 , int hash2 , int count){
        return (hash1 + count * hash2) % sizeOfTable;
    }

    public int helpHashFun1(E1 key){
        return key.hashCode() % sizeOfTable;
    }

    public int helpHashFun2(E1 key){
        return 1 + key.hashCode() % ( sizeOfTable - 1);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        if (size() == 0 || findKey((E1)key) == null)
            return false;
        else
            return true;
    }

    @Override
    public boolean containsValue(Object value) {
        if (size() == 0)
            return false;
        for (int i = 0; i < sizeOfTable; i++){
            if (doubleHashTable[i].getValue().equals((E2)value))
                return true;
        }
        return false;
    }

    @Override
    public void putAll(Map<? extends E1, ? extends E2> m) {
        for (Map.Entry entry: m.entrySet())
            put((E1)entry.getKey(), (E2)entry.getValue());
    }

    @Override
    public void clear(){
        if (size() == 0)
            return;
        for (int i = 0; i <sizeOfTable; i++){
            doubleHashTable[i] = null;
        }
        countElements = 0;
    }


    @Override
    public Set<E1> keySet() {
        return null;
    }

    @Override
    public Collection<E2> values() {
        return null;
    }

    @Override
    public Set<Entry<E1, E2>> entrySet() {
        return null;
    }
}
