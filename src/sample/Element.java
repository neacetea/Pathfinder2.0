package sample;

public class Element <E> implements Comparable<Element<E>>{

    private E data;
    private Element<E> previous;
    private float fCost; // Used to store travels costs for a*
    private float gCost;
    private float hCost;

    public Element (E data){
        this.data=data;
        previous=null;
    }

    public Element (E data, Element previous){
        this.data=data;
        this.previous=previous;
    }

    public Element(E data, Element previous, float fCost){
        this.fCost=fCost;
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

    public float getFCost() {
        return fCost;
    }

    public void setFCost(float fCost) {
        this.fCost = fCost;
    }

    public float getGCost() {
        return gCost;
    }

    public void setGCost(float gCost) {
        this.gCost = gCost;
    }

    public float getHCost() {
        return hCost;
    }

    public void setHCost(float hCost) {
        this.hCost = hCost;
    }

    public int compareTo(Element<E> elt){
        if(fCost > elt.getFCost()){
            return 1;
        }
        else if(fCost < elt.getFCost()){
            return -1;
        }
        else{
            return 0;
        }
    }
}
