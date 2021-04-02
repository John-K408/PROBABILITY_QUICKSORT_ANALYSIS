import java.util.Arrays;

public class QuickSortRandom {
    public static  void main(String[] args) {
        String[] arrayTypes = new String[]{"generateRandomInput","generatePartiallySortedInput","generateMostlySortedInput"};

//        double average = computeAverage(20,100000,arrayTypes[0]);
        int maxArraySize = 1000000;

        for(int n = 1; n <= maxArraySize;n++){
            System.out.println("ArraySize "+ n + ": " + computeAverage(n,100,arrayTypes[0]));
        }

//        int[] array = new int[]{1,4,3,8,7,5,9,0};
//        System.out.println(QuickSort(array,0,array.length - 1,0));

        //Generate ArrayTypes = new Generate();




    }

    public static int QuickSort(int[] array,int low, int high,int count){
        if(high > low){
            //the randomIndex is the index of the number we will be using to group the array from low to high
            int randomIndex = low + (int)(Math.random()*(high - low + 1));
            //int randomIndex = high;
            //System.out.println(randomIndex);

            //partition method returns an array of size 02.
            //the first element will be the partitionIndex and the second will be the number of counts in the partition method.

            int[] partitionArray = partition(array,low,high,randomIndex);
            int partitionIndex = partitionArray[0];
            count = count + partitionArray[1];
            count = QuickSort(array,low,partitionIndex - 1,count);
            count = QuickSort(array,partitionIndex + 1,high,count);
        }
        return count;
    }

    public static int[] partition(int[]array, int low, int high, int randomIndex){
        swap(array,randomIndex,high);
        //upper bound defines the upper end of the unprocessed array
        int upperbound = high - 1;
        //lower bound defines the lower end of the unprocessed array
        int lowerbound = low;
        int toBeComparedWith = array[high];
        int counts = 0;

        while(lowerbound <= upperbound){
            counts++;
            if(array[lowerbound]<= toBeComparedWith){
                lowerbound++;
            }
            else{
                swap(array,lowerbound,upperbound);
                upperbound--;
            }
        }
        swap(array,high,upperbound + 1);
        return new int[]{upperbound + 1,counts};

    }

    public static void swap(int[]array, int i, int j){
        int temporary = array[i];
        array[i] = array[j];
        array[j] = temporary;
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
