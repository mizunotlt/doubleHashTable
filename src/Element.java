import java.util.Map;

public class Element< E1, E2> implements Map.Entry<E1, E2> {
    private E1 key;
    private E2 value;

    public Element(E1 key, E2 value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public E1 getKey() {

        return key;
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
    public String toString() {
        return "(" + this.key + ":" + this.value + ")";
    }

}