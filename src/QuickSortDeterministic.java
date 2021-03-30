public class QuickSortDeterministic {
    public static void main(String[] args) {
        /*Generate arr = new Generate();
        int[] arr1 = arr.generateMostlySortedInput(10);
        int[] arr2 = arr.generateMostlySortedInput(10);
        int[] arr3 = arr.generateMostlySortedInput(10);*/

        String[] arrayTypes = new String[]{"generateRandomInput","generatePartiallySortedInput","generateMostlySortedInput"};

//        double average = computeAverage(20,100000,arrayTypes[0]);

        int maxArraySize = 1000000;

        for(int n = 1; n <= maxArraySize;n++){
            System.out.println("ArraySize "+ n + ": " + computeAverage(n,100000,arrayTypes[0]));
        }


//        int[] A = {1,4,3,8,7,5,9,0};
//
//        System.out.println("----comparison count------");
//        System.out.println(QuickSort(A,0, A.length-1,0));
//
//        for (int j : A) {
//            System.out.println("----sorted elements------");
//            System.out.println(j);
//        }


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

    public static double computeAverage(int arraysize, int numTries, String arrayInputType){
        //if the user puts in the wrong arraysize to be generated, print error
        if(arraysize <1) System.err.println("The size of the array must be at least 1");

        //initialize array --- doesn't really do anything.
        int[] array = new int[1];
        Generate generator = new Generate();

        //since average is a sum divided by number of times, we will sum all the counts on the different inputs
        //of the same size and divide by the number of times we run quicksort.

        double totalCounter = 0;

        for(int i = 0; i < numTries;i++){

            if(arrayInputType.toLowerCase().equals("generaterandominput")){
                array = generator.generateRandomInput(arraysize);
            }
            else if(arrayInputType.toLowerCase().equals("generatepartiallysortedinput")){
                array = generator.generatePartiallySortedInput(arraysize);
            }

            else if(arrayInputType.toLowerCase().equals("generatemostlysortedinput")){
                array = generator.generateMostlySortedInput(arraysize);
            }
            else{
                System.err.println("Please input correct input type for the array");
            }

            //the count (number of comparisons) of quicksort for this current input
            int currCount = QuickSort(array,0,arraysize - 1,0);

//            System.out.println("-------Current count-----");
//            System.out.println(currCount);

            totalCounter += currCount;
        }

//        System.out.println("-----------Average-------------");
//        System.out.println(totalCounter/numTries);


        return totalCounter/numTries;

    }


}
