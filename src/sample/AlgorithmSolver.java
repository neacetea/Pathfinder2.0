package sample;

import java.util.LinkedList;
import java.util.Queue;

public class AlgorithmSolver {
    public void Largeur(Maze maze)
    {
        maze.map[maze.Start.i][maze.Start.j] = 'D';
        maze.map[maze.Goal.i][maze.Goal.j] = 'A';
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
        positionsQueue.add(maze.Start);

        int longueur = 0;

        while(!positionsQueue.isEmpty())
        {
            NextSteps = maze.GetNextSteps(positionsQueue.element());
            for(Position pos : NextSteps)
            {
                if(pos != null)
                {
                    if(maze.IsArrived(pos))
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
}

