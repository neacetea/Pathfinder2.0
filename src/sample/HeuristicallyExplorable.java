package sample;

public interface HeuristicallyExplorable<E> extends Explorable<E> {

    public double processFCost(E previous,E actual, E goal,double previousG );
    public double processGCost(E previous,E actual,double previousG);
    public double processHCost(E goal,E actual);
}
