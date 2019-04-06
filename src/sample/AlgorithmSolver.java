package sample;

import java.util.*;

public class AlgorithmSolver<E> {


    private HashSet<Element<E>> nodeList;


    public AlgorithmSolver(){
        nodeList=new HashSet<Element<E>>();
    }

        public Element<E> WidthFirst(Explorable<E> graph){
        //Variable initializations
        Element<E> start=new Element<E>(graph.getStart());
        Element<E> finish;
        Queue<Element<E>> toExploreQueue=new LinkedList<Element<E>>();
        boolean arrived=false;
        List<E> nextSteps;

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
                    if(!nodeList.contains(eltNode)){
                        nodeList.add(eltNode);
                        toExploreQueue.add(eltNode);
                    }
                }
            }

        }
        return null;
    }

    /**
     * Send back an arrayList of positions representing the path from start to finish
     * @param path
     * @param finish
     * @return
     */
    private List makePath(HashSet<Element<E>> path,Element finish){
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

