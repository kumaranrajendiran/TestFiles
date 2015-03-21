package DivideAndConquer;

public class BinarySearch {
 public static int foo(int a[], int key, int p , int r){
	 if(p>r || a[p] > key ){
		 return -1;
	 }
	 else if(key > a[r]){
		 return r+1;
	 }
	 else 
	 {
		 int mid = (p+r)/2;
		 System.out.println(p+ " "+ r);
		 if(key == a[mid] ||  (key> a[mid] && key < a[mid+1])){
			   return mid+1;
		 }
		 else if(key< a[mid]){
			 return foo(a, key,p, mid-1 );
		 }
		 else{	 
			 return foo(a, key, mid+1, r );
		 }
	 }	 
 }
 
 public static void main(String args[]){
	 int[] a= {1,3,4,6,8};
	 System.out.println(foo(a,4,0,0));
 }
}
