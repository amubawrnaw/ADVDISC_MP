import java.util.*;

public class MC02{
	public static void printMatrix(Matrix m){
		String s = "";
		for(int i = 0 ; i < m.matrix.size() ; i++){
			for(int k = 0 ; k < m.matrix.get(i).vector.length ; k++){
				s += m.matrix.get(i).vector[k] + " ";
			}
			s+="\n";
		}
		System.out.println(s);
	}

	public static void main(String[] args){
		//MC02.printMatrix(new Matrix(5));

		Vector v1 = new Vector(new double[]{5.0,4.0}, 2);
		Vector v2 = new Vector(new double[]{4.0,5.0}, 2);
		List<Vector> l1 = new ArrayList<>();
		l1.add(v1);
		l1.add(v2);
		Matrix m1 = new Matrix(l1, 2);
		MC02.printMatrix(m1.inverse());
	}
}