package DivideAndConquer;

public class MaximumSubarraySum {
	
	public static int[] actualImplementation(int a[], int p, int r){
		
		if(p<r){
		    int  mid = (p+r)/2;
			int left[]   =  foo(a,p, mid);
			int right[]  =  foo(a, mid+1, r);
			
		    int lsum =0, lmax= 0, rsum=0, rmax=0, sum=0;		    
		    for(int i=mid; i>= p; i--){
		    	sum = sum + a[i];
		    	if(lsum < sum){
		    		lmax= i;
		    		lsum = sum;
		    	}
		    }
		    sum =0 ;
		    for(int i=mid+1; i<= r; i++){
		    	sum = sum + a[i];
		    	if(rsum < sum){
		    		rmax= i;
		    		rsum = sum;
		    	}
		    }
		    int[] center = {lsum+rsum,lmax,rmax };
			if(left[2]>= right[2] && left[2]>= center[2] ){
				return left ;
			}
			else if(right[2]>= left[2] && right[2]>= center[2]){
				return right;
			}
			else
				return center;
		}
		else{
				int tmp[] = {a[p], p, r};
				return tmp;
			}
	}

	
	
	public static int[] foo(int a[], int p, int r){
		
		if(p<r){
		    int  mid = (p+r)/2;
			int left[]   =  foo(a,p, mid);
			int right[]  =  foo(a, mid+1, r);
			
			/* 
			index 0 : max sum from left
			index 1 : max sum from right
			index 2 : max sum
			index 3 : total sum
			totalSum = Left subarray's total sum + Right subarray's total sum
			leftMaxSum = max((Left subarray's total sum+ Right subarray's max sum from left ), (Left subarray's max sum from left))
			rightMaxSum = max((Right subarray's total sum + Left subarray's max sum from right), (Right subarray's max sum from right)) 
			maxSum =  max(left subarray's max sum, right subarray's max sum, (left subarray's right sum + right subarray's left sum))
			*/
			
			int totalSum = left[3] + right[3];
			int leftMaxSum = 0, rightMaxSum =0 , maxSum = 0 ;
			leftMaxSum = Math.max((left[3]+right[0]) , left[0]);
			rightMaxSum = Math.max((right[3]+left[1]) ,right[1]);
			maxSum = Math.max(left[2], Math.max(right[2], left[1]+right[0]));
		    int[] center = {leftMaxSum,rightMaxSum,maxSum,totalSum};
		    
		    System.out.println("\n\n**********");
		    for(int i=0; i<=3; i++){
				System.out.println(center[i]);
			}
			return center;
		}
		else{
				int tmp[] = {a[p],a[p],a[p],a[p]};
				  System.out.println("\n\n**********");
				    for(int i=0; i<=3; i++){
						System.out.println(tmp[i]);
					}
				return tmp;
			}
	}
	
	public static void main(String args[]){
		int[] a= {3,-6,-3,1,8,-3,-19};
		int[] tmp =  foo(a,0,6);
		System.out.println("\n\n**********");
		for(int i=0; i<=3; i++){
			System.out.println(tmp[i]);
		}

	}
}
