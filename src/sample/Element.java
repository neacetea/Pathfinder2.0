package sample;

public class Element <E> implements Comparable<Element<E>>{

    private E data;
    private Element<E> previous;
    private double fCost; // Used to store travels costs for a*
    private double gCost;
    private double hCost;

    public Element (E data){
        this.data=data;
        previous=null;
    }

    public Element (E data, Element previous){
        this.data=data;
        this.previous=previous;
    }

    public Element(E data, Element previous, double fCost){
        this.fCost=fCost;
        this.data=data;
        this.previous=previous;
    }

    @Override
    public int hashCode(){
        return data.hashCode();
    }

    @Override
    public boolean equals(Object e1){
        if(!(e1 instanceof Element)) return false;
        Element e2= (Element) e1;
        return data.equals( e2.getData());
    }

    public E getData() {
        return data;
    }

    public Element<E> getPrevious() {
        return previous;
    }

    public double getFCost() {
        return fCost;
    }

    public void setFCost(double fCost) {
        this.fCost = fCost;
    }

    public double getGCost() {
        return gCost;
    }

    public void setGCost(double gCost) {
        this.gCost = gCost;
    }

    public double getHCost() {
        return hCost;
    }

    public void setHCost(double hCost) {
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
