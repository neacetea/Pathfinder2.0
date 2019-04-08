package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Maze implements Explorable<Position> {
	
	public Position start, goal;
	public String name;
	char[][] map ;

	public final static char WALKABLE_CHAR='0';

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
			if(map[i-1][j]==WALKABLE_CHAR){
				positions.add(new Position(i-1,j));
			}
		}

		if(i+1<map.length){
			if(map[i+1][j]==WALKABLE_CHAR){
				positions.add(new Position(i+1,j));
			}
		}

		if(j-1>=0){
			if(map[i][j-1]==WALKABLE_CHAR){
				positions.add(new Position(i,j-1));
			}
		}

		if(j+1<map[i].length){
			if(map[i][j+1]==WALKABLE_CHAR){
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
	
	void BuildMatrice(){
		
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

