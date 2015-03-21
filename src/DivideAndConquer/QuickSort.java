package DivideAndConquer;

public class QuickSort {
	public static void foo(int a[],int p , int r){
		int x= a[r];
		int i= p-1;
		int min = p-1;
		int j ;
		for(j =p; j<r; j++){
			if(a[j] <= x){
				i = i+1;
				if(a[j] < x){
					min = min+1;
					int temp = a[min];
					a[min]= a[j];
					a[j]= temp;
					if(a[j]==x){
						temp = a[i];
						a[i]= a[j];
						a[j]= temp;
					}
				}
				else
				{
						int temp = a[i];
						a[i]= a[j];
						a[j]= temp;
					
				}
			}
		}
		int temp = a[i+1];
		a[i+1]= a[j];
		a[j]= temp;
		for(int t=0;t<=r;t++){
			System.out.println(a[t]);
		}
		System.out.println((min+1)+" "+(i+1));
	 }
	 
	 public static void main(String args[]){
		 int[] a= {2,1,2,6,2,4,1,0,1,7,2,2,1,0,2};
		 foo(a,0,13);
	 }
}
