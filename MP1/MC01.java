import java.util.*;

public class MC01{
	public static void main(String[] args){
		//Driver class for Vector

		//input format: 
		//n m
		//n x m matrix
		//1 x n matrix ( constants )

		//sample input:
		//2 3
		//4 2 0 - matrix
		//6 9 6 - matrix
		//1 2 3 - constants

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		ArrayList<Vector> list = new ArrayList<>();

		for(int i = 0 ; i < n ; i++){
			double[] temp = new double[m];
			for(int k = 0 ; k < m ; k++){
				temp[k] = sc.nextInt();
			}
			Vector v = new Vector(temp, m);
			list.add(v);
		}

		double[] temp = new double[n];
		for(int k = 0 ; k < n ; k++){
			temp[k] = sc.nextInt();
		}
		Vector constants = new Vector(temp, n);

		Vector new_constants = Vector.Gauss_Jordan(list, m, constants);
		int span = Vector.span(list, m);
		String s = "Constants : ";
		for(int i = 0 ; i < new_constants.dimension ; i++){
			s+= new_constants.vector[i] + " ";
		}
		s+="Span : " + span;
		System.out.println(s);


	}
}