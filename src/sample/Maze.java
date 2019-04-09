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
		/*
		try{
			positions[0] = map[e.i-1][e.j] == '0' ? new Position(e.i-1, e.j) : null;
			if(positions[0] != null)
				map[e.i-1][e.j] = 'x';
		}
		catch(Exception exception){
			positions[0] = null;
		}
		try{
			positions[1] = map[e.i][e.j+1] == '0' ? new Position(e.i, e.j+1) : null;
			if(positions[1] != null)
				map[e.i][e.j+1] = 'x';
		}
		catch(Exception exception){
			positions[1] = null;
		}
		try{
			positions[2] = map[e.i+1][e.j] == '0' ? new Position(e.i+1, e.j) : null;
			if(positions[2] != null)
				map[e.i+1][e.j] = 'x';
		}
		catch(Exception exception){
			positions[2] = null;
		}
		try{
			positions[3] = map[e.i][e.j-1] == '0' ? new Position(e.i, e.j-1) : null;
			if(positions[3] != null)
				map[e.i][e.j-1] = 'x';
		}
		catch(Exception exception){
			positions[3] = null;
		}
		*/
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

	public float processFCost(Position previous,Position actual,Position goal, float previousG){
		return processGCost(previous,actual,previousG)+processHCost(goal,actual);
	}

	public float processGCost(Position previous,Position actual,float previousG){
		float previousToSelfDistance=EuclidianDistance(actual,previous);

		return previousToSelfDistance+previousG;
	}

	public float processHCost(Position goal,Position actual){
		return EuclidianDistance(actual,goal);
	}

	private float EuclidianDistance(Position pos1,Position pos2){
		return (float) Math.sqrt( Math.pow(pos1.getI()-pos2.getI(),2)+Math.pow(pos2.getJ()-pos2.getJ(),2));
	}

	void BuildMatrice(){
		
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

