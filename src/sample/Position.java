package sample;

public class Position {
	
	int i,j;
	
	Position(int i, int j)
	{
		this.i = i; this.j = j;
	}
	@Override
	public int hashCode() {
		int tmp = ( j +  ((i+1)/2));
		return i +  ( tmp * tmp);
	}

	@Override
	public boolean equals(Object p1){
		if(!(p1 instanceof Position)) return false;
		Position p2=(Position) p1;
		return (this.i==p2.getI()&&this.j==p2.getJ());
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	private float EuclidianDistance(Position pos2){
		return (float) Math.sqrt( Math.pow(i-pos2.getI(),2)+Math.pow(j-pos2.getJ(),2));
	}
}
