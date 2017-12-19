import java.util.*;


public class DoubleHashTable <E1, E2> implements Map<E1, E2> {

    private Element<E1, E2> doubleHashTable[];
    private int sizeOfTable = 4;
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
    public E1 findKey(E1 key){
        int hashFunResult1 = helpHashFun1((E1)key);
        int hashFunResult2 = helpHashFun2((E1)key);
        for (int i = 0;  i < sizeOfTable; i++){
            int hash = mainHashFun(hashFunResult1, hashFunResult2, i);
            if(doubleHashTable[hash] != null)
                if (doubleHashTable[hash].getKey().equals(key))
                    return doubleHashTable[hash].getKey();
        }
        return null;
    }

    private E2 putHelp (E1 key, E2 value, Element<E1, E2> table[]){
        int hashFunResult1 = helpHashFun1(key);
        int hashFunResult2 = helpHashFun2(key);
        for (int i = 0; i < sizeOfTable; i++){
            int hash = mainHashFun(hashFunResult1, hashFunResult2, i);
            if (table[hash] == null){
                table[hash] = new Element(key, value);
                return table[hash].getValue();
            }
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
        if (!isFreeIndex() || hashFunResult1 > sizeOfTable || hashFunResult2 > sizeOfTable ){
            sizeOfTable = 2 * sizeOfTable;
            Element<E1, E2> newDoubleHashTable[] = new Element[sizeOfTable];
            for (int i = 0; i < doubleHashTable.length; i++){
                putHelp(doubleHashTable[i].getKey(), doubleHashTable[i].getValue(), newDoubleHashTable);
            }
            doubleHashTable = newDoubleHashTable;
        }
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

    private int mainHashFun (int hash1 , int hash2 , int count){

        return (hash1 + count * hash2) % (sizeOfTable);
    }

    private int helpHashFun1(E1 key){

        return key.hashCode() % sizeOfTable;
    }

    private int helpHashFun2(E1 key){

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
            if(doubleHashTable[i] == null)
                continue;
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

    //Итератор для ключей
    public class IteratorForKeys implements Iterator<E1>{

        int nextIndex = 0;
        E1 currentKey;
        Element<E1, E2> table[] = doubleHashTable;

        private  IteratorForKeys(){
        }

        public boolean hasNextElement(){
            int i = nextIndex;
            currentKey = null;
            while (i <= table.length  - 1 && currentKey == null){
                if (table[i] != null){
                    currentKey = table[i].getKey();
                }
                else
                    i++;
            }
            nextIndex = i;
            return currentKey != null;
        }

        @Override
        public boolean hasNext() {
            return hasNextElement();
        }

        @Override
        public E1 next() {
            if (!hasNextElement())
                throw new NoSuchElementException("Error");
            nextIndex++;
            return currentKey;
        }

        @Override
        public void remove() {
            if ( nextIndex > table.length)
                throw new NoSuchElementException("Error");
            table[nextIndex - 1] = null;
            countElements--;
        }
    }

    //Итератор для значений
    public class IteratorForValues implements Iterator<E2>{

        int nextIndex = 0;
        E2 currentValue;
        Element<E1, E2> table[] = doubleHashTable;
        private  IteratorForValues(){
        }

        public boolean hasNextElement(){
            int i = nextIndex;
            currentValue = null;
            while (i <= table.length - 1 && currentValue == null){
                if (table[i] != null){
                    currentValue = table[i].getValue();
                }
                else
                    i++;
            }
            nextIndex = i;
            return currentValue != null;
        }

        @Override
        public boolean hasNext() {
            return hasNextElement();
        }

        @Override
        public E2 next() {
            if (!hasNextElement())
                throw new NoSuchElementException("Error");
            nextIndex++;
            return currentValue;
        }

        @Override
        public void remove() {
            if (nextIndex > table.length)
                throw new NoSuchElementException("Error");
            table[nextIndex - 1] = null;
            countElements--;
        }
    }

    public Iterator<E1> iteratorForKeys() {
        return new IteratorForKeys();
    }

    public Iterator<E2> iteratorForValues() {
        return new IteratorForValues();
    }

    private  Set<E1> keySet;

    @Override
    public Set<E1> keySet() {
        keySet = new KeySet();
        return keySet;
    }

    private class KeySet extends AbstractSet<E1> {

        @Override
        public Iterator<E1> iterator() {
            return iteratorForKeys();
        }

        @Override
        public boolean contains(Object obj){
            return DoubleHashTable.this.containsKey(obj);
        }

        @Override
        public void clear(){
            DoubleHashTable.this.clear();
        }

        @Override
        public int size() {
            return DoubleHashTable.this.size();
        }

        @Override
        public boolean remove (Object key) {
            Iterator <E1> iteratorForKeys = iterator();
            E1 keys = (E1) key;
             do{
                 E1 i = iteratorForKeys.next();
                if (i == keys){
                    iteratorForKeys.remove();
                    return true;
                }

            }
            while (iteratorForKeys.hasNext());
            return false;
        }
    }

    private Collection<E2> values;

    @Override
    public Collection<E2> values() {
        values = new Values();
        return values;
    }
    private class Values extends AbstractCollection<E2>{

        @Override
        public Iterator<E2> iterator() {
            return  iteratorForValues();
        }

        @Override
        public int size() {

            return  DoubleHashTable.this.size();
        }

        @Override
        public boolean remove (Object value){
            Iterator <E2> iteratorForValue = iterator();
            E2 values = (E2) value;
            do{
                E2 i = iteratorForValue.next();
                if (i == values){
                    iteratorForValue.remove();
                    return true;
                }

            }
            while (iteratorForValue.hasNext());
            return false;
        }
    }

    //Итератор для элемента
    public class IteratorForEntry implements Iterator<Entry<E1, E2>>{

        int nextIndex = 0;
        Entry<E1, E2> currentEntry;
        Element<E1, E2> table[] = doubleHashTable;
        private  IteratorForEntry(){
        }

        public boolean hasNextElement(){
            int i = nextIndex;
            currentEntry = null;
            while (i <= table.length - 1 && currentEntry == null){
                if (table[i] != null){
                    currentEntry = table[i];
                }
                else
                    i++;
            }
            nextIndex = i;
            return currentEntry != null;
        }

        @Override
        public boolean hasNext() {
            return hasNextElement();
        }

        @Override
        public Entry<E1, E2> next() {
            if (!hasNextElement())
                throw new NoSuchElementException("Error");
            nextIndex++;
            return currentEntry;
        }

        @Override
        public void remove() {
            if (nextIndex > table.length)
                throw new NoSuchElementException("Error");
            table[nextIndex - 1] = null;
            countElements--;
        }
    }

    public Iterator<Entry<E1, E2>> iteratorForEntry() {
        return new IteratorForEntry();
    }
    private  Set<Entry<E1, E2>> entrySet;

    @Override
    public Set<Entry<E1, E2>> entrySet() {
        entrySet = new EntrySet();
        return entrySet;
    }

    private class EntrySet extends AbstractSet<Entry<E1, E2>>{

        @Override
        public Iterator<Entry<E1, E2>> iterator() {
            return iteratorForEntry();
        }

        @Override
        public int size() {

            return DoubleHashTable.this.size();
        }

        @Override
        public boolean remove (Object entry){
            Iterator <Entry<E1, E2>> iteratorForEntry = iterator();
            Entry<E1, E2> entrys = (Entry<E1, E2>) entry;
            do{
                Entry<E1, E2> i = iteratorForEntry.next();
                if (i.equals(entrys)){
                    iteratorForEntry.remove();
                    return true;
                }
            }
            while (iteratorForEntry.hasNext());
            return false;
        }
    }

    @Override
    public int hashCode(){
        int result = 0;
        for(Element<E1, E2> elem : doubleHashTable)
            result = result + elem.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object object){
        DoubleHashTable table = (DoubleHashTable) object;
        if(table.size() != this.size())
            return false;
        if(getClass() != object.getClass())
            return false;
        Set setKey = this.keySet();
        Set otherSetKey = table.keySet();
        Collection collectionValue = this.values();
        Collection otherCollectionValue = table.values();
        if (setKey.equals(otherSetKey) && collectionValue.equals(otherCollectionValue))
            return true;
        return false;
    }
}
