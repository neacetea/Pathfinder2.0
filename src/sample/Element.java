package sample;

public class Element <E> {

    private E data;
    private Element<E> previous;

    public Element (E data){
        this.data=data;
        previous=null;
    }

    public Element (E data,Element previous){
        this.data=data;
        this.previous=previous;
    }

    public int hashCode(){
        return data.hashCode();
    }

    public E getData() {
        return data;
    }

    public Element<E> getPrevious() {
        return previous;
    }

}
