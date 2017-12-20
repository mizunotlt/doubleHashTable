import java.util.Map;

public class Element< E1, E2> implements Map.Entry<E1, E2> {
    private E1 key;
    private E2 value;
    private boolean isFree;

    public Element(E1 key, E2 value) {
        this.key = key;
        this.value = value;
        this.isFree = false;
    }

    @Override
    public E1 getKey() {

        return key;
    }
    public boolean getIsFree(){
        return isFree;
    }
    public boolean changeFree(){
        return !this.isFree;
    }

    @Override
    public E2 getValue() {
        return value;
    }
    //????
    @Override
    public E2 setValue(E2 value) {
        this.value = value;
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        Element element = (Element) obj;
        return (this.key.equals(element.key) && this.value.equals(element.value));
    }

    @Override
    public int hashCode() {
        int result = key.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "(" + this.key + ":" + this.value + ")";
    }

}
