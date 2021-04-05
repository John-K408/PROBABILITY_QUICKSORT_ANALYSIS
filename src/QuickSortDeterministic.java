import java.io.PrintStream;

public class QuickSortDeterministic {
    public static void main(String[] args) {
        /*Generate arr = new Generate();
        int[] arr1 = arr.generateMostlySortedInput(10);
        int[] arr2 = arr.generateMostlySortedInput(10);
        int[] arr3 = arr.generateMostlySortedInput(10);*/

        String[] arrayTypes = new String[]{"generateRandomInput","generatePartiallySortedInput","generateMostlySortedInput"};

//        double average = computeAverage(20,100000,arrayTypes[0]);

        int maxArraySize = 10000;
        int numTries = 100;
        int nForVariance = 400;

        //These array will be filled with the individual counts for each instance of the array of given
        // size n,that will be generated in ComputeAverage
        //The array that we will use depends on the configuration we are looking for.
        int[] countsRandomInput = new int[numTries];
        int[]countsPartiallySortedInput = new int[numTries];
        int[]countsMostlySortedInput =new int[numTries];

        double averageForRandomInput;
        double averageForPartiallySortedInput;
        double averageForMostlySortedInput;






//        averageCaller(maxArraySize,new int[maxArraySize],numTries,arrayTypes[0]);
//        averageCaller(maxArraySize,new int[maxArraySize],numTries,arrayTypes[1]);
//        averageCaller(maxArraySize,new int[maxArraySize],numTries,arrayTypes[2]);



        try {
            PrintStream output = new PrintStream("generateRandomInput.txt");
            double average;
            for(int n = 1; n <= maxArraySize;n++) {
                if(n == nForVariance){
                    average = computeAverage(n,countsRandomInput, numTries, arrayTypes[0]);
                    averageForRandomInput = average;
                }
                else{
                    average = computeAverage(n,new int[numTries],numTries,arrayTypes[0]);
                }
                output.print(n + "," + average);
                output.println();
            }
            output.close();
        }
        catch(Exception e) {
            e.getStackTrace();
        }


        try {
            PrintStream output = new PrintStream("generatePartiallySortedInput.txt");
            double average;

            for(int n = 1; n <= maxArraySize;n++) {
                if(n == nForVariance){
                    average = computeAverage(n,countsPartiallySortedInput, numTries, arrayTypes[1]);
                    averageForPartiallySortedInput = average;
                }
                else{
                    average = computeAverage(n,new int[numTries],numTries,arrayTypes[1]);
                }
                output.print(n + "," + average);
                output.println();
            }
            output.close();
        }
        catch(Exception e) {
            e.getStackTrace();
        }



        try {
            PrintStream output = new PrintStream("generateMostlySortedInput.txt");
            double average;
            for(int n = 1; n <= maxArraySize;n++) {
                if(n == nForVariance){
                    average = computeAverage(n,countsMostlySortedInput, numTries, arrayTypes[2]);
                    averageForMostlySortedInput = average;
                }
                else{
                    average = computeAverage(n,new int[numTries],numTries,arrayTypes[2]);
                }
                output.print(n + "," + average);
                output.println();
            }
            output.close();
        }
        catch(Exception e) {
            e.getStackTrace();
        }







        //System.out.println("ArraySize "+ n + ": " + computeAverage(n,100000,arrayTypes[0]));


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

//    public static void averageCaller(int maxArraySize,int[] countsArray,int numTries, String arrayType){
//        if(arrayType.toLowerCase().equals("generaterandominput")){
//            try {
//                PrintStream output = new PrintStream("generateRandomInput.txt");
//
//                for(int n = 1; n <= maxArraySize;n++) {
//                    output.printf(n + "," + computeAverage(n,countsArray, 100, arrayType));
//                    output.println();
//                }
//                output.close();
//            }
//            catch(Exception e) {
//                e.getStackTrace();
//            }
//        }
//        if(arrayType.toLowerCase().equals("generatepartiallysortedinput")){
//            try {
//                PrintStream output = new PrintStream("generatePartiallySortedInput.txt");
//
//                for(int n = 1; n <= maxArraySize;n++) {
//                    output.printf(n + "," + computeAverage(n,countsArray, 100, arrayType));
//                    output.println();
//                }
//                output.close();
//            }
//            catch(Exception e) {
//                e.getStackTrace();
//            }
//        }
//
//        if(arrayType.toLowerCase().equals("generatemostlysortedinput")){
//            try {
//                PrintStream output = new PrintStream("generateMostlySortedInput.txt");
//
//                for(int n = 1; n <= maxArraySize;n++) {
//                    output.printf(n + "," + computeAverage(n,countsArray, 100, arrayType));
//                    output.println();
//                }
//                output.close();
//            }
//            catch(Exception e) {
//                e.getStackTrace();
//            }
//        }
//
//
//
//
//
//    }

    public static int QuickSort (int[] arr, int lo, int hi, int counter) {
        if (hi>lo){
            int p = partition(arr,lo,hi)[0];
            counter += partition(arr,lo,hi)[1];
            counter = QuickSort(arr, lo, p-1,counter);
            counter = QuickSort(arr, p+1,hi,counter);
        }
        return counter;
    }

    public static int[] partition(int[]array, int low, int high){
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
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static double computeAverage(int arraysize,int []countsArray, int numTries, String arrayInputType){
        //if the user puts in the wrong arraysize to be generated, print error
        if(arraysize <1) System.err.println("The size of the array must be at least 1");

        //initialize array --- doesn't really do anything.
        int[] array = new int[1];
        Generate generator = new Generate();

        //since average is a sum divided by number of times, we will sum all the counts on the different inputs
        //of the same size and divide by the number of times we run quicksort.

        double totalCounter = 0;

        for(int i = 0; i < numTries;i++){

            if(arrayInputType.equalsIgnoreCase("generaterandominput")){
                array = generator.generateRandomInput(arraysize);
            }
            else if(arrayInputType.equalsIgnoreCase("generatepartiallysortedinput")){
                array = generator.generatePartiallySortedInput(arraysize);
            }

            else if(arrayInputType.equalsIgnoreCase("generatemostlysortedinput")){
                array = generator.generateMostlySortedInput(arraysize);
            }
            else{
                System.err.println("Please input correct input type for the array");
            }

            //the count (number of comparisons) of quicksort for this current input
            int currCount = QuickSort(array,0,arraysize - 1,0);
            countsArray[i] = currCount;

//            System.out.println("-------Current count-----");
//            System.out.println(currCount);

            totalCounter += currCount;
        }

//        System.out.println("-----------Average-------------");
//        System.out.println(totalCounter/numTries);


        return totalCounter/numTries;

    }

//    public static int computeVariance(int arraySize,int numTries,String arrayInput){
//
//
//        return -1;
//    }


}
