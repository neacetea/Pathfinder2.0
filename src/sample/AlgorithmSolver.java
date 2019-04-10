package sample;

import java.util.*;

public class AlgorithmSolver<E> {


    private HashSet<Element<E>> nodeList;
    double elapsedTime;
    public long stepCount = 0;

    public AlgorithmSolver(){
        nodeList=new HashSet<Element<E>>();
    }

    public Element<E> widthFirst(Explorable<E> graph){

        long startTime = System.currentTimeMillis();


        //Variable initializations
        Element<E> start=new Element<E>(graph.getStart());
        Element<E> finish;
        Queue<Element<E>> toExploreQueue=new LinkedList<Element<E>>();
        boolean arrived=false;
        List<E> nextSteps;
        HashSet<Element<E>> exploredElementsList=new HashSet<Element<E>>();

        //Adding the first step
        toExploreQueue.add(start);

        //We will continue until there is no element left to explore, or until we has arrived ofc
        while(toExploreQueue.size()!=0&&arrived==false){
            Element<E> eltPreviousStep=toExploreQueue.remove();
            nextSteps=graph.getNextSteps(eltPreviousStep.getData());
            for(E sglNode : nextSteps){
                Element<E> eltNode=new Element<E>(sglNode,eltPreviousStep);
                if(graph.isArrived(sglNode)) {
                    finish = eltNode;
                    long stopTime = System.currentTimeMillis();
                    elapsedTime = stopTime - startTime;
                    return finish;
                }
                else{
                    //if the node is'nt already contained we add it
                    if(!exploredElementsList.contains(eltNode)){
                        exploredElementsList.add(eltNode);
                        stepCount++;
                        toExploreQueue.add(eltNode);
                    }
                }
            }

        }

        return null;
    }
}

