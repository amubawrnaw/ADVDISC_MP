import java.util.*;

public class Vector{
	public final int dimension;
	public double[] vector;

	public Vector(int dimension){
		this.dimension = dimension;
		vector = new double[[dimension];
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

	public Vector scale(double scalar){
		for(int i = 0 ; i < dimension ; i++){
			vector[i] *= scalar;
		}
		return this;
	}

	public Vector add(Vector addend){
		if(dimension != addend.dimension) System.out.println("Error lmao");
		else{
			for(int i = 0 ; i < dimension ; i++){
				vector[i] += addend.vector[i];
			}
		}
		return this;
	}
	

}