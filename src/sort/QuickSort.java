package sort;

public class QuickSort {
	public static void main(String[] args) {
		int i;
		int[] arr = { 90, 23, 101, 45, 65, 23, 67, 89, 34, 23 };
		String chuoi = "";
		for (int j = 0; j < arr.length; j++) {
			chuoi = chuoi + arr[j] + " ";
		}
		System.out.println(chuoi);
		quickSort(arr, 0, 9);
		System.out.println("\n The sorted array is: \n");
		for (i = 0; i < 10; i++) {
			System.out.println(arr[i]);
		}

//		String datetime = "20/12/2020 18:02:56";
//		System.out.println(datetime);
//		System.out.println(datetime.substring(0, 2));
//		System.out.println(datetime.substring(3, 5));
//		System.out.println(datetime.substring(6, 10));
//		System.out.println(datetime.substring(11, 13));
//		System.out.println(datetime.substring(14, 16));
//		System.out.println(datetime.substring(17, 19));
	}

	public static int partition(int a[], int beg, int end) {

		int left, right, temp, loc, flag;
		loc = left = beg;
		right = end;
		flag = 0;
		while (flag != 1) {
			while ((a[loc] <= a[right]) && (loc != right))
				right--;
			if (loc == right)
				flag = 1;
			else if (a[loc] > a[right]) {
				temp = a[loc];
				a[loc] = a[right];
				a[right] = temp;
				loc = right;
			}
			if (flag != 1) {
				while ((a[loc] >= a[left]) && (loc != left))
					left++;
				if (loc == left)
					flag = 1;
				else if (a[loc] < a[left]) {
					temp = a[loc];
					a[loc] = a[left];
					a[left] = temp;
					loc = left;
				}
			}
		}
		return loc;
	}

	static void quickSort(int a[], int beg, int end) {

		int loc;
		if (beg < end) {
			loc = partition(a, beg, end);
			quickSort(a, beg, loc - 1);
			quickSort(a, loc + 1, end);
		}
	}
}