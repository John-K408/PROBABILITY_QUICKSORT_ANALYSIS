import java.io.PrintStream;
import java.util.Arrays;

public class QuickSortDeterministic {
    public static void main(String[] args) {

        String[] arrayTypes = new String[]{"generateRandomInput","generatePartiallySortedInput","generateMostlySortedInput"};

//        double average = computeAverage(20,100000,arrayTypes[0]);

        int maxArraySize = 10000;
        int numTries = 100;
        int nForVariance = 800;

        if(nForVariance > maxArraySize) System.err.println("nForVariance cannot exceed maximum array size");

        //These array will be filled with the individual counts for each instance of the array of given
        // size n,that will be generated in ComputeAverage
        //The array that we will use depends on the configuration we are looking for.
        int[] countsRandomInput = new int[numTries];
        int[]countsPartiallySortedInput = new int[numTries];
        int[]countsMostlySortedInput =new int[numTries];

        double[] averageForRandomInput = new double[maxArraySize];
        double[] averageForPartiallySortedInput = new double[maxArraySize];
        double[] averageForMostlySortedInput = new double[maxArraySize];


        Generate generate = new Generate();


        int[] arrShuffle = generate.generateMostlySortedInput(10);
        System.out.println("Array before Shuffle: "+Arrays.toString(arrShuffle));

        //fisherYatesShuffle(arrShuffle);

        System.out.println("Array after shuffle: " + Arrays.toString(arrShuffle));

        for(int n = 1; n <= maxArraySize;n++){

                averageForRandomInput[n] = computeAverage(n,countsRandomInput,numTries,arrayTypes[0]);

                averageForPartiallySortedInput[n] = computeAverage(n,countsPartiallySortedInput,numTries,arrayTypes[1]);

                averageForMostlySortedInput[n] = computeAverage(n,countsMostlySortedInput,numTries,arrayTypes[2]);

        }



        //NB: THE CODE BELOW IS USED TO WRITE TO AN OUTPUT FILE SO WE CAN PLOT GRAPHS

//        try {
//            PrintStream output = new PrintStream("generateRandomInput.txt");
//            double average;
//            for(int n = 1; n <= maxArraySize;n++) {
//                if(n == nForVariance){
//                    average = computeAverage(n,countsRandomInput, numTries, arrayTypes[0]);
//                    averageForRandomInput = average;
//                }
//                else{
//                    average = computeAverage(n,new int[numTries],numTries,arrayTypes[0]);
//                }
//                output.print(n + "," + average);
//                output.println();
//            }
//            output.close();
//        }
//        catch(Exception e) {
//            e.getStackTrace();
//        }
//
//
//        try {
//            PrintStream output = new PrintStream("generatePartiallySortedInput.txt");
//            double average;
//
//            for(int n = 1; n <= maxArraySize;n++) {
//                if(n == nForVariance){
//                    average = computeAverage(n,countsPartiallySortedInput, numTries, arrayTypes[1]);
//                    averageForPartiallySortedInput = average;
//                }
//                else{
//                    average = computeAverage(n,new int[numTries],numTries,arrayTypes[1]);
//                }
//                output.print(n + "," + average);
//                output.println();
//            }
//            output.close();
//        }
//        catch(Exception e) {
//            e.getStackTrace();
//        }
//
//
//
//        try {
//            PrintStream output = new PrintStream("generateMostlySortedInput.txt");
//            double average;
//            for(int n = 1; n <= maxArraySize;n++) {
//                if(n == nForVariance){
//                    average = computeAverage(n,countsMostlySortedInput, numTries, arrayTypes[2]);
//                    averageForMostlySortedInput = average;
//                }
//                else{
//                    average = computeAverage(n,new int[numTries],numTries,arrayTypes[2]);
//                }
//                output.print(n + "," + average);
//                output.println();
//            }
//            output.close();
//        }
//        catch(Exception e) {
//            e.getStackTrace();
//        }

        //VARIANCE FOR RANDOM INPUT

        //VARIANCE FOR RANDOM INPUT
        double varianceForRandomInput = computeVariance(countsRandomInput,averageForRandomInput[nForVariance],numTries);
        System.out.println( "Variance For  RandomInput: " + varianceForRandomInput );

        //VARIANCE FOR PARTIALLY SORTED INPUT
        double varianceForPartiallySortedInput = computeVariance( countsPartiallySortedInput,averageForPartiallySortedInput[nForVariance],numTries);
        System.out.println("Variance For PartiallySortedInput: " + varianceForPartiallySortedInput);

        //VARIANCE FOR MOSTLY SORTED INPUT
        double varianceForMostlySortedInput = computeVariance( countsPartiallySortedInput,averageForPartiallySortedInput[nForVariance],numTries);
        System.out.println("Variance For MostlySortedInput: "+varianceForMostlySortedInput);


        System.out.println("Average For RandomInput: " + averageForRandomInput);
        System.out.println("Average For PartiallySortedInput: " + averageForPartiallySortedInput);
        System.out.println("Average For MostlySortedInput: " + averageForMostlySortedInput);

        System.out.println("Standardized Variance RandomInput: " + computeStandardVariance(varianceForRandomInput,averageForRandomInput[nForVariance]));
        System.out.println("Standardized Variance PartiallySortedInput: " + computeStandardVariance(varianceForPartiallySortedInput,averageForPartiallySortedInput[nForVariance]));
        System.out.println("Standardized Variance MostlySortedInput: " + computeStandardVariance(varianceForMostlySortedInput,averageForMostlySortedInput[nForVariance]));




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

    public static void fisherYatesShuffle(int[]arr){
        for(int i = 0; i < arr.length;i ++){
            int randomIndex = (int)(Math.random()* (arr.length - i));
            swap(arr,arr.length - i - 1,randomIndex);
        }
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
                Arrays.toString(array);
//                fisherYatesShuffle(array);
                Arrays.toString(array);
            }
            else if(arrayInputType.equalsIgnoreCase("generatepartiallysortedinput")){
                array = generator.generatePartiallySortedInput(arraysize);


//                fisherYatesShuffle(array);

            }

            else if(arrayInputType.equalsIgnoreCase("generatemostlysortedinput")){
                array = generator.generateMostlySortedInput(arraysize);


//                fisherYatesShuffle(array);

            }
            else{
                System.err.println("Please input correct input type for the array");
            }

            //the count (number of comparisons) of quicksort for this current input
            int currCount = QuickSort(array,0,arraysize - 1,0);
            countsArray[i] = currCount;



            totalCounter += currCount;
        }




        return totalCounter/numTries;

    }

    public static double computeVariance(int[]array,double mean,int numTries){

        double sqDiffSum = 0;

        for(int i = 0; i < array.length;i++){
            sqDiffSum += Math.pow((array[i] - mean),2);
        }


        return sqDiffSum/(numTries - 1);
    }

    public static double computeStandardVariance(double variance,double mean){
        return variance/Math.pow(mean,2);
    }


}
