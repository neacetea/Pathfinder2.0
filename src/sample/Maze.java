package sample;

import java.io.*;

public class Maze implements Explorable<Position> {
	
	public Position Start, Goal;
	public String name;
	char[][] map = {{'0','0','1','1','0'},{'0','0','0','1','0'},{'0','1','0','0','0'},{'0','1','1','1','1'},{'0','0','0','0','0'}};

	public Position[] GetNextSteps(Position e) {
		
		Position[] positions = new Position[4];
		
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
		return positions;
	}

	public boolean IsArrived(Position e) {
		if(e.i == Goal.i && e.j == Goal.j)
			return true;
		else
			return false;
	}

	public Position GetStart() {
		
		return Start;
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
			Start = new Position(Integer.parseInt(position.split("\t")[1]),Integer.parseInt(position.split("\t")[0])+4);
			position = br.readLine();
			Goal = new Position(Integer.parseInt(position.split("\t")[1]),Integer.parseInt(position.split("\t")[0])+4);
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

