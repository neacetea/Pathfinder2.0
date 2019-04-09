package sample;

import sun.reflect.generics.tree.Tree;

import java.util.*;

public class AlgorithmSolver<E> {


    public AlgorithmSolver(){

    }

    public Element<E> widthFirst(Explorable<E> graph){
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
                return finish;
            }
            else{
                //if the node is'nt already contained we add it
                if(!exploredElementsList.contains(eltNode)){
                    exploredElementsList.add(eltNode);
                    toExploreQueue.add(eltNode);
                }
            }
        }

    }
    return null;
    }

    public Element<E> aStar(HeuristicallyExplorable<E> graph){

        PriorityQueue<Element<E>> toExploreQueue=new PriorityQueue<Element<E>>(30,new SortElementByAStarCost());
        boolean arrived=false;
        List<E> nextSteps;
        HashSet<Element<E>> exploredElementsList=new HashSet<Element<E>>();

        Element<E> start=new Element<E>(graph.getStart());
        Element<E> goal=new Element<E>(graph.getGoal());
        start.setFCost(0);
        start.setGCost(0);

        toExploreQueue.add(start);

        while(toExploreQueue.size()!=0){
            Element<E> eltPreviousStep=toExploreQueue.remove();
            nextSteps=graph.getNextSteps(eltPreviousStep.getData());
            //
            for(E sglStep : nextSteps){
                //We first create the Element without it's cost to spare some CPU in case we are arrived
                Element<E> actual=new Element<E>(sglStep,eltPreviousStep);
                if(graph.isArrived(sglStep)){
                    return actual;
                }
                else{
                    if(!exploredElementsList.contains(actual.hashCode())){
                        //Process cost
                        actual.setGCost(graph.processGCost(eltPreviousStep.getData(),actual.getData(),eltPreviousStep.getGCost()));
                        double Gcost=actual.getGCost();
                        actual.setHCost(graph.processHCost(goal.getData(),actual.getData()));
                        double hcost=actual.getHCost();
                        actual.setFCost(actual.getHCost()+actual.getGCost());

                        toExploreQueue.add(actual);
                    }

                }
            }

        }


        return null;
    }



    /*
    public void Largeurtest(Maze maze)
    {
        maze.map[maze.start.i][maze.start.j] = 'D';
        maze.map[maze.goal.i][maze.goal.j] = 'A';
        Position[] NextSteps = new Position[4];

        for(char[] tc : maze.map)
        {
            for(char c : tc)
            {
                System.out.print(c);
            }
            System.out.println();
        }
        Queue<Position> positionsQueue = new LinkedList<Position>();
        positionsQueue.add(maze.start);

        int longueur = 0;

        while(!positionsQueue.isEmpty())
        {
            NextSteps = maze.getNextSteps(positionsQueue.element());
            for(Position pos : NextSteps)
            {
                if(pos != null)
                {
                    if(maze.isArrived(pos))
                        break;
                    else
                    {
                        positionsQueue.add(pos);
                        System.out.println("i = " + pos.i + " j = " + pos.j);
                    }
                }
            }
            if(positionsQueue.poll() == null)
                break;
        }

        for(char[] tc : maze.map)
        {
            for(char c : tc)
            {
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println("Longueur du parcours : " + longueur);
    }
    */


}

