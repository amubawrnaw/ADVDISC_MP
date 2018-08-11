import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Matrix {
	public final int row_dimension;
	public final int col_dimension;
	public List<Vector> matrix;
	public Matrix(int dimension){
		this.row_dimension=dimension;
		this.col_dimension=dimension;
		matrix=new ArrayList<Vector>();
		for(int i=0;i<dimension;i++){
			Vector temp= new Vector(dimension);
			temp.vector[i]=1;
			matrix.add(temp);	
		}
	}
	
	public Matrix(List<Vector> list,int dimension){
		this.col_dimension=dimension;
		this.row_dimension=list.size();
		this.matrix=list;
	}
	
	public Matrix times(Matrix other){
		if(this.col_dimension!=other.row_dimension)
			return null;
		List<Vector> sol=new ArrayList<Vector>();
//		for(int i=0;i<other.col_dimension;i++){
//			Vector row_temp=new Vector(other.row_dimension);
//			for(int k=0;k<other.row_dimension;k++)
//				row_temp.vector[k]=other.matrix.get(k).vector[i];
////			for(int k=0;k<col_dimension;k++){
////				
////			}
//			for(Vector temp:this.matrix){
//				
//				for(int j=0;j<temp.dimension;j++)
//					
//			}
		for(Vector row_temp:this.matrix){
			Vector temp=new Vector(other.col_dimension);
			for(int i=0;i<other.col_dimension;i++){
				//Vector col_temp=new Vector(other.row_dimension);
				for(int k=0;k<other.row_dimension;k++)
					temp.vector[i]+=other.matrix.get(k).vector[i]*row_temp.vector[k];		
			}
		}
		return new Matrix(sol,other.col_dimension);
	}
	
	public double det (){
		double det=1;
		for(int i=0;i<row_dimension;i++){
			Vector curr=matrix.get(i);
			int j=0;
			while(j<curr.dimension){
				if(curr.vector[j]!=0)
					break;
				
				j++;
			}

			if(j<curr.dimension){
				curr=curr.scale(1/curr.vector[j]);
				det*=curr.vector[j];
				matrix.set(i, curr);
				for(int k=i+1;k<row_dimension;k++){
					Vector tempcurr=new Vector(curr.vector,curr.dimension);
					Vector temp=matrix.get(k);
					double scalesize=temp.vector[j]*(-1);
					matrix.set(k, temp.add(tempcurr.scale(scalesize)));
				}
			}else
				return 0;
			
		}

		for(int i=0;i<row_dimension;i++){
			int k=0;
			for(;k<col_dimension;k++){
				Vector curr=matrix.get(k);
				if(curr.vector[i]==1){
					Collections.swap(matrix, k, i);
					if(k!=i)
						det*=-1;
					break;
				}
			}
			if(k>=col_dimension)
				return 0;
		}
		return det;
	}
	public Matrix inverse(){
		Matrix inverse=new Matrix(row_dimension);
		if(row_dimension!=col_dimension)
			return null;
		for(int i=0;i<row_dimension;i++){
			Vector curr=matrix.get(i);
			int j=0;
			while(j<curr.dimension){
				if(curr.vector[j]!=0)
					break;
				j++;
			}
			if(j<curr.dimension){
				//constants.vector[i]=constants.vector[i]/curr.vector[j];
				Vector inv_curr=inverse.matrix.get(i);
				inv_curr=inv_curr.scale(1/curr.vector[j]);
				inverse.matrix.set(i, curr);
				curr=curr.scale(1/curr.vector[j]);
				matrix.set(i, curr);
				for(int k=i+1;k<row_dimension;k++){
					Vector tempcurr=new Vector(curr.vector,curr.dimension);
					Vector tempinv_curr =new Vector(inv_curr.vector,inv_curr.dimension);
					Vector temp=matrix.get(k);
					Vector tempinv=inverse.matrix.get(k);
					double scalesize=temp.vector[j]*(-1);
					//constants.vector[k]+=constants.vector[i]*scalesize;
					matrix.set(k, temp.add(tempcurr.scale(scalesize)));
					inverse.matrix.set(k, tempinv.add(tempinv_curr.scale(scalesize)));
				}
			}else
				return null;
		}
		for(int i=row_dimension-1;i>=0;i--){

			Vector curr=matrix.get(i);
			int j=0;
			while(j<curr.dimension){
				if(curr.vector[j]!=0)
					break;
				
				j++;
			}
			if(j<curr.dimension){
				Vector inv_curr=inverse.matrix.get(i);
				inv_curr=inv_curr.scale(1/curr.vector[j]);
				inverse.matrix.set(i, curr);				curr=curr.scale(1/curr.vector[j]);
				matrix.set(i, curr);
				for(int k=i-1;k>=0;k--){
					Vector tempcurr=new Vector(curr.vector,row_dimension);
					Vector tempinv_curr =new Vector(inv_curr.vector,inv_curr.dimension);
					Vector temp=matrix.get(k);
					Vector tempinv=inverse.matrix.get(k);
					double scalesize=temp.vector[j]*(-1);
					inverse.matrix.set(k, tempinv.add(tempinv_curr.scale(scalesize)));
					matrix.set(k, temp.add(tempcurr.scale(scalesize)));
				}

			}else
				return null;
		}
		for(int i=0;i<row_dimension;i++){
			for(int k=0;k<row_dimension;k++){
				Vector curr=matrix.get(k);
				if(curr.vector[i]==1){
					Collections.swap(matrix, k, i);
					Collections.swap(inverse.matrix, k, i);
					break;
				}
			}
		}
		return inverse;
	}
}
