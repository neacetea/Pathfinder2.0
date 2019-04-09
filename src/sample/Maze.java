package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Maze implements HeuristicallyExplorable<Position> {
	
	public Position start, goal;
	public String name;
	char[][] map ;

	public final static String WALKABLE_CHAR="0DA";

	public Maze(){

	}

	public Maze(char[][] map,Position start,Position goal){
		this.map=map;
		this.start =start;
		this.goal =goal;
	}

	public List<Position> getNextSteps(Position e) {
		
		List<Position> positions = new ArrayList<Position>();

		int i=e.getI();
		int j=e.getJ();

		if(i-1>=0){
			if(WALKABLE_CHAR.contains(String.valueOf(map[i-1][j]))){
				positions.add(new Position(i-1,j));
			}
		}

		if(i+1<map.length){
			if(WALKABLE_CHAR.contains(String.valueOf(map[i+1][j]))){
				positions.add(new Position(i+1,j));
			}
		}

		if(j-1>=0){
			if(WALKABLE_CHAR.contains(String.valueOf(map[i][j-1]))){
				positions.add(new Position(i,j-1));
			}
		}

		if(j+1<map[i].length){
			if(WALKABLE_CHAR.contains(String.valueOf(map[i][j+1]))){
				positions.add(new Position(i,j+1));
			}
		}

		return positions;
	}

	public boolean isArrived(Position e) {
		if(e.i == goal.i && e.j == goal.j)
			return true;
		else
			return false;
	}

	public Position getStart() {
		return start;
	}

	public Position getGoal(){
		return goal;
	}

	public double processFCost(Position previous,Position actual,Position goal, double previousG){
		return processGCost(previous,actual,previousG)+processHCost(goal,actual);
	}

	public double processGCost(Position previous,Position actual,double previousG){
		double previousToSelfDistance=EuclidianDistance(actual,previous);

		return previousToSelfDistance+previousG;
	}

	public double processHCost(Position goal,Position actual){
		return EuclidianDistance(actual,goal);
	}

	private double EuclidianDistance(Position pos1,Position pos2){
		return Math.sqrt( Math.pow(pos1.getI()-pos2.getI(),2)+Math.pow(pos1.getJ()-pos2.getJ(),2));
	}

	public static void main (String [] args){

	}

	/**
	 *
	 * @param finalLinkedElement an element that link the solution
	 * @return
	 */
	public char [][] sendResolution(Element<Position> finalLinkedElement){
		char [] [] resolution=map.clone();
		Element<Position> current=finalLinkedElement.getPrevious();

		while(current.getPrevious()!=null){
			resolution[current.getData().i][current.getData().j]='M';
			current=current.getPrevious();
		}
		return resolution;
	}

	public void readFile(String filename)
	{
		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(filename);
			br = new BufferedReader(fr);

			int r;

			int i = 0;
			int y = 0;
			char ch;
			name = br.readLine();
			String position = br.readLine();
			start = new Position(Integer.parseInt(position.split("\t")[1]),Integer.parseInt(position.split("\t")[0])+4);
			position = br.readLine();
			goal = new Position(Integer.parseInt(position.split("\t")[1]),Integer.parseInt(position.split("\t")[0])+4);
			position = br.readLine();
			map = new char[Integer.parseInt(position.split("\t")[1])][Integer.parseInt(position.split("\t")[0])+4];
			fr.close();
			br.close();
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
			while ((r = fr.read()) != -1) {

					if ((char) r == '\n') {
						System.out.println("");
						y += 1;
						i = 0;
					}
					else {
						if(y > 4) {
						ch = (char) r;
						if (ch == '+' || ch == '-' || ch == '|') {
							map[i][y] = '1';
							System.out.print(map[i][y]);
						}
						else if (ch == ' ') {
							map[i][y] = '0';
							System.out.print(map[i][y]);
						}
						i++;
					}
				}
			}


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}

	}
}

