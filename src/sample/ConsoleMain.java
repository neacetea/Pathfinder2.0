package sample;

public class ConsoleMain {

    public static void main(String[] args){
        //Position start=new Position(6,6);
        //Position finish=new Position(20,20);
        char[][] map = {{'0','0','1','1','0'},{'0','0','0','1','0'},{'0','1','0','0','0'},{'0','1','1','1','1'},{'0','0','0','1','0'}};

        //Maze maze=new Maze(map,start,finish);
        Maze maze=new Maze();
        maze.readFile("D:/Code/Java/Pathfinder2.0/src/maps/map.txt");
/*
        maze.start =start;
        maze.goal =finish;

        maze.map[0][0]='D';
        maze.map[0][4]='A';

         */
        ConsoleView.showLab(maze.map);

        AlgorithmSolver<Position> algo=new AlgorithmSolver<Position>();
        Element<Position> finishAlgo=algo.widthFirst(maze);

        char [] [] resolution=maze.sendResolution(finishAlgo);
        ConsoleView.showLab(resolution);

    }
}
