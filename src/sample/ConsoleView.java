package sample;

public class ConsoleView {

    public static void showLab(char[][] maze){
        for(int i=0;i<maze.length;i++){
            for(int j=0;j<maze[i].length;j++){
                System.out.print(maze[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();

    }


}
