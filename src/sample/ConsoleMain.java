package sample;

import java.util.HashSet;

public class ConsoleMain {

    public static void main(String[] args){
        Position start=new Position(0,0);
        Position finish=new Position(0,4);
        char[][] map = {{'0','0','1','1','0'},{'0','0','0','1','0'},{'0','1','0','0','0'},{'0','1','1','1','1'},{'0','0','0','1','0'}};

        Maze maze=new Maze(map,start,finish);
        maze.start =start;
        maze.goal =finish;

        ConsoleView.showLab(maze.map);

        AlgorithmSolver<Position> algo=new AlgorithmSolver<Position>();
        Element<Position> finishAlgo=algo.WidthFirst(maze);

        maze.map[finishAlgo.getData().i][finishAlgo.getData().j]='A';

        Element<Position> current=finishAlgo;
        while(current.getPrevious()!=null){
            current=current.getPrevious();
            maze.map[current.getData().i][current.getData().j]='M';
        }

        ConsoleView.showLab(maze.map);


    }
}
