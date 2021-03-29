public class QuickSortDeterministic {
    public static void main(String[] args) {
        /*Generate arr = new Generate();
        int[] arr1 = arr.generateMostlySortedInput(10);
        int[] arr2 = arr.generateMostlySortedInput(10);
        int[] arr3 = arr.generateMostlySortedInput(10);*/

        int[] A = {1,0,5};

        System.out.println("----comparison count------");
        System.out.println(QuickSort(A,0, A.length-1,0));

        for (int j : A) {
            System.out.println("----sorted elements------");
            System.out.println(j);
        }


    }

    public static int QuickSort (int[] arr, int lo, int hi, int counter) {
        if (hi>lo){
            int p = partition(arr,lo,hi)[0];
            counter += partition(arr,lo,hi)[1];
            counter = QuickSort(arr, lo, p-1,counter);
            counter = QuickSort(arr, p+1,hi,counter);
        }
        return counter;
    }

    public static int[] partition(int[] arr,int lo, int hi){
        int pivot = arr[hi];
        int i = (lo-1);

        int counter = 0;

        for (int j = lo; j < hi; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr,i,j);
            }
            counter++;
        }

        //System.out.println("--counting--");
        //System.out.println(counter);

        swap(arr,hi,i+1);
        return new int[]{i + 1, counter};
    }

    public static void swap(int[]array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
