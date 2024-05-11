package Lproject;

import java.util.ArrayList;

//Class that will merge sort an array list with multithreading
public class MyMergeSort {
	//Set the maximum threads
	private static final int MAX_THREAD_COUNT = 4;
	
	//Inner class for threads
	private static class ThreadSort extends Thread {
		ThreadSort(ArrayList<Book> list, int startIndex, int endIndex) {
			super(()->{
				mergeSort(list, startIndex, endIndex);
			});
			this.start();
		}
		
	}
	
	//multithreaded merge sort
	public static ArrayList<Book> threadedMergeSort(ArrayList<Book> list) {
		//setting size
		final int size = list.size();
		
		//Setting the workload for each thread
		//divides the work equally if the number of elements is equal to the max thread count,
		//otherwise it will assign the work to 1 remaining thread.
		boolean equalWork = size % MAX_THREAD_COUNT == 0;
		int limit = equalWork? size / MAX_THREAD_COUNT: size / (MAX_THREAD_COUNT -1);
		
		//Assigns everything to one thread if the workload isn't enough to warrant more threads
		limit = limit < MAX_THREAD_COUNT? MAX_THREAD_COUNT : limit;
		
		//Array list that will help track threads
		final ArrayList<ThreadSort> threads = new ArrayList<>();
		
		//Assign each thread its index range for workload
		for (int i = 0; i < size; i += limit) {
			int begin = i;
			int remaining = size - i;
			int end = remaining < limit? i+(remaining - 1): i+(limit - 1);
			final ThreadSort a = new ThreadSort(list, begin, end);
			
			threads.add(a);
		}
		
		//wait until all threads are sorted
		for (Thread t: threads) {
			try {
				t.join();
			} catch(InterruptedException e) {
			}
		}
		
		//Merge everything back into one array list
		for (int i = 0; i < size; i += limit)  {
			int mid = i == 0? 0 : i - 1;
			int remaining = size - i;
			int end = remaining < limit? i+(remaining - 1): i+(limit-1);
			
			merge(list, 0, mid, end);
		}
		
		return list;
	}
	
	//merge sort class
	public static void mergeSort(ArrayList<Book> list, int startIndex, int endIndex) {
		//Check if the startIndex is less than the endIndex, and if the Array List has at least 1 object
		if (startIndex < endIndex && (endIndex - startIndex) >= 1) {
			
			//separate the halves
			int midPoint = (endIndex + startIndex) / 2;

			mergeSort(list, startIndex, midPoint); 		//sort first half
			mergeSort(list, midPoint + 1, endIndex); 	//sort second half
				
			//merge the indexes into one
			merge(list, startIndex, midPoint, endIndex);
		}
	}
	
	//puts the arraylist back together
	public static void merge(ArrayList<Book> list, int startIndex, int midPoint, int endIndex) {
		ArrayList<Book> mergeSortedArray = new ArrayList<Book>();
			
		int leftIndex = startIndex;
		int rightIndex = midPoint + 1;
			
		while(leftIndex <= midPoint && rightIndex <= endIndex) {
			//check if first letter of leftIndex title is higher in alphabet than rightIndex title
			if (list.get(leftIndex).getTitle().charAt(0) < list.get(rightIndex).getTitle().charAt(0)) {
				mergeSortedArray.add(list.get(leftIndex));
				leftIndex++;
			}
			//if same letter then check second letter
			else if (list.get(leftIndex).getTitle().charAt(0) == list.get(rightIndex).getTitle().charAt(0)) {
				//check if second letter of leftIndex title is higher in alphabet than rightIndex title
				if (list.get(leftIndex).getTitle().charAt(1) < list.get(rightIndex).getTitle().charAt(1)) {
					mergeSortedArray.add(list.get(leftIndex));
					leftIndex++;
				}
				//if same letter then check third letter
				else if (list.get(leftIndex).getTitle().charAt(1) == list.get(rightIndex).getTitle().charAt(1)) {
					//check if third letter of leftIndex title is higher in alphabet than rightIndex title
					if (list.get(leftIndex).getTitle().charAt(2) < list.get(rightIndex).getTitle().charAt(2)) {
						mergeSortedArray.add(list.get(leftIndex));
						leftIndex++;
					}
					//give up after three letters and use default order
					else {
						mergeSortedArray.add(list.get(rightIndex));
						rightIndex++;
					}
				}
				//rightIndex title second letter is higher in the alphabet than leftIndex
				else {
					mergeSortedArray.add(list.get(rightIndex));
					rightIndex++;
				}
			}
			//rightIndex title first letter is higher in the alphabet than leftIndex
			else {
				mergeSortedArray.add(list.get(rightIndex));
				rightIndex++;
			}
		}
		
		//add the leftIndex to the array if leftIndex less than or equal to midPoint
		while(leftIndex <= midPoint) {
			mergeSortedArray.add(list.get(leftIndex));
			leftIndex++;
		}
		
		//add the rightIndex to the array if rightIndex less than or equal to midPoint
		while(rightIndex <= endIndex) {
			mergeSortedArray.add(list.get(rightIndex));
			rightIndex++;
		}

		int i = 0;
		int k = startIndex;
		while (i < mergeSortedArray.size()) {
			list.set(k, (Book) mergeSortedArray.get(i++));
			k++;
		}
	}
        //Comments on merge sort efficiency
	/*
	 The way that merge sort works is by taking on a divide and conquer type approach. It recursively breaks down a problem into two or more 
	 sub-problems. After the two halves are sorted, the algorithm then merges them. 
	 
	 The space complexity for merge sort is O(n) but shouldn't be too relevant becuase users will not have large enough library 
	 for this to be relevant.
	 
	 The time complexity is the O(nLogn) in all cases.As the user libraries grow larger the complexity will 
	 not increase and will remain consistent. Eventually the program will slow down as libraries get larges but for small library will be ok.
        If a users library were to be extremely large, merge sort would be more efficient than; for example quick sort. This is because 
	 the worst case for merge sort is O(nLogn), whereas quick sorts is O(n).
	 */
	
}
