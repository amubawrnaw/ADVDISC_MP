public class Vector{
	public final int[] dimension;
	public double[] vector;

	public Vector(int dimension){
		dimension = new int(){dimension};
		vector = new double[dimension];
		for(int i = 0 ; i < dimension ; i++){
			dimension[i] = 0;//initiate or something
		}
	}

	public Vector(double[] array, int dimension){
		this(dimension);
		for(int i = 0 ; i < dimension ; i++){
			vector[i] = array[i];
		}
	}
}