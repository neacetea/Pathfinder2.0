package sample;

public interface HeuristicallyExplorable<E> extends Explorable<E> {

    public float processFCost(E previous,E actual, E goal,float previousG );
    public float processGCost(E previous,E actual,float previousG);
    public float processHCost(E goal,E actual);
}
