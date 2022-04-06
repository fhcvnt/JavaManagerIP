package sort;

import java.util.Arrays;

public class QuickSort2 {
	public static void main(String[] args) {
		int[] arr = { 4, 5, 1, 2, 3, 3, 4, 5, 1, 8, 7, 9 };
		quickSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
		
		quickSortDecrease(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
	}

	public static void quickSort(int[] arr, int start, int end) {

		int partition = partition(arr, start, end);

		if (partition - 1 > start) {
			quickSort(arr, start, partition - 1);
		}
		if (partition + 1 < end) {
			quickSort(arr, partition + 1, end);
		}
	}

	public static int partition(int[] arr, int start, int end) {
		int pivot = arr[end];

		for (int i = start; i < end; i++) {
			if (arr[i] < pivot) {
				int temp = arr[start];
				arr[start] = arr[i];
				arr[i] = temp;
				start++;
			}
		}

		int temp = arr[start];
		arr[start] = pivot;
		arr[end] = temp;

		return start;
	}

	public static void quickSortDecrease(int[] arr, int start, int end) {

		int partition = partitionDecrease(arr, start, end);

		if (partition - 1 > start) {
			quickSortDecrease(arr, start, partition - 1);
		}
		if (partition + 1 < end) {
			quickSortDecrease(arr, partition + 1, end);
		}
	}

	public static int partitionDecrease(int[] arr, int start, int end) {
		int pivot = arr[end];

		for (int i = start; i < end; i++) {
			if (arr[i] > pivot) {
				int temp = arr[start];
				arr[start] = arr[i];
				arr[i] = temp;
				start++;
			}
		}

		int temp = arr[start];
		arr[start] = pivot;
		arr[end] = temp;

		return start;
	}
}
