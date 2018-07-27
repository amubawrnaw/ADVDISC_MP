import java.util.*;

public class Vector{
	public final int dimension;
	public double[] vector;

	public Vector(int dimension){
		this.dimension = dimension;
		vector = new double[dimension];
		for(int i = 0 ; i < dimension ; i++){
			vector[i] = 0;//initiate or something
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
	public static Vector Gauss_Jordan (List<Vector> vectors, int dimension, 
			Vector constants){
		if(vectors.size()!=dimension||
				constants.dimension!=dimension)
			return null;
		for(int i=0;i<dimension;i++){
			Vector curr=vectors.get(i);
			constants.vector[i]=constants.vector[i]/curr.vector[i];
			curr=curr.scale(1/curr.vector[i]);
			vectors.set(i, curr);
			for(int k=i+1;k<dimension;k++){
				Vector tempcurr=new Vector(curr.vector,dimension);
				Vector temp=vectors.get(k);
				double scalesize=temp.vector[i]*(-1);
				constants.vector[k]+=constants.vector[i]*scalesize;
				vectors.set(k, temp.add(tempcurr.scale(scalesize)));
			}
		}
		for(int i=dimension-1;i>=0;i--){
			Vector curr=vectors.get(i);
			constants.vector[i]=constants.vector[i]/curr.vector[i];
			curr=curr.scale(1/curr.vector[i]);
			vectors.set(i, curr);
			for(int k=i-1;k>=0;k--){
				Vector tempcurr=new Vector(curr.vector,dimension);
				Vector temp=vectors.get(k);
				double scalesize=temp.vector[i]*(-1);
				constants.vector[k]+=constants.vector[i]*scalesize;
				vectors.set(k, temp.add(tempcurr.scale(scalesize)));
			}
		}
		return constants;
	}
	public static int span (List<Vector> vectors, int dimension){
		int span=0;
		
		for(int i=0;i<dimension;i++){
			Vector curr=vectors.get(i);
			int j=0;
			while(j<curr.dimension){
				if(curr.vector[j]!=0)
					break;
				
				j++;
			}
//			System.out.println(curr.vector[0]+" "+curr.vector[1]+" "+curr.vector[2]+" "+j);
//			System.out.println();
			

			if(j<curr.dimension){
				curr=curr.scale(1/curr.vector[j]);
				vectors.set(i, curr);
				for(int k=i+1;k<dimension;k++){
					Vector tempcurr=new Vector(curr.vector,curr.dimension);
					Vector temp=vectors.get(k);
					double scalesize=temp.vector[j]*(-1);
					vectors.set(k, temp.add(tempcurr.scale(scalesize)));
				}
			}
//			for(int a=0;a<dimension;a++){
//				Vector currt=vectors.get(a);
//				System.out.println(currt.vector[0]+" "+currt.vector[1]+" "+currt.vector[2]);
//				
//			}
//			System.out.println();
			
		}
//		for(int i=dimension-1;i>=0;i--){
//			Vector curr=vectors.get(i);
//			curr=curr.scale(1/curr.vector[i]);
//			vectors.set(i, curr);
//			for(int k=i-1;k>=0;k--){
//				Vector tempcurr=new Vector(curr.vector,dimension);
//				Vector temp=vectors.get(k);
//				double scalesize=temp.vector[i]*(-1);
//				vectors.set(k, temp.add(tempcurr.scale(scalesize)));
//			}
//		}
		for(int i=0;i<dimension;i++){
			Vector curr=vectors.get(i);
			for(int k=0;k<curr.dimension;k++){
				if(curr.vector[k]>0){
					span++;
					break;
				}
			}
		}
		
		return span;
	}
	

}