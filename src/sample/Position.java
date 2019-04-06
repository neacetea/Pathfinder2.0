package sample;

public class Position {
	
	int i,j;
	
	Position(int i, int j)
	{
		this.i = i; this.j = j;
	}

	//Use an 'elegant pairing function', i&j must stay positive
	public int hashCode() {
		return (i >= j) ? (i * i + i + j) : (j * j + i);
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}


}
