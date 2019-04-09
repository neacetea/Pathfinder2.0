package sample;

public class ConsoleMain {

    public static void main(String[] args){
        Position start=new Position(0,0);
        Position finish=new Position(0,4);
        char[][] map = {{'0','0','1','1','0'},{'0','0','0','1','0'},{'0','1','0','0','0'},{'0','1','1','1','1'},{'0','0','0','1','0'}};

        Maze maze=new Maze(map,start,finish);
        maze.start =start;
        maze.goal =finish;

        maze.map[0][0]='D';
        //maze.map[0][4]='A';
        ConsoleView.showLab(maze.map);

        AlgorithmSolver<Position> algo=new AlgorithmSolver<Position>();
        Element<Position> finishAlgo=algo.WidthFirst(maze);

        //maze.map[finishAlgo.getData().i][finishAlgo.getData().j]='A';

        char [] [] resolution=maze.sendResolution(finishAlgo);
        ConsoleView.showLab(resolution);


    }
}
