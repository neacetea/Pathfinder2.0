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

	public float processFCost(){

		return 1.0f;
	}

	public float processGCost(Position previous,float previousG){
		float previousToSelfDistance=EuclidianDistance(previous);

		return previousToSelfDistance+previousG;
	}

	public float processHCost(Position goal){
		return EuclidianDistance(goal);
	}

	private float EuclidianDistance(Position pos2){
		return (float) Math.sqrt( Math.pow(i-pos2.getI(),2)+Math.pow(j-pos2.getJ(),2));
	}
}
