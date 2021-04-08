import java.io.PrintStream;
import java.util.Arrays;

public class QuickSortRandom {
    public static  void main(String[] args) {
        String[] arrayTypes = new String[]{"generateRandomInput","generatePartiallySortedInput","generateMostlySortedInput"};

//        double average = computeAverage(20,100000,arrayTypes[0]);
        int maxArraySize = 1000;
        int numTries = 100;
        int nForVariance = 800;


        //These array will be filled with the individual counts for each instance of the array of given
        // size n,that will be generated in ComputeAverage
        //The array that we will use depends on the configuration we are looking for.
        int[] countsRandomInput = new int[numTries];
        int[]countsPartiallySortedInput = new int[numTries];
        int[]countsMostlySortedInput =new int[numTries];

        double averageForRandomInput = 0;
        double averageForPartiallySortedInput = 0;
        double averageForMostlySortedInput = 0;


        double average = -1;

        for(int n = 1; n <= maxArraySize;n++){
            if(n == nForVariance){
                average = computeAverage(n,countsRandomInput,numTries,arrayTypes[0]);
                averageForRandomInput  = average;
                average = computeAverage(n,countsPartiallySortedInput,numTries,arrayTypes[1]);
                averageForPartiallySortedInput = average;
                average = computeAverage(n,countsMostlySortedInput,numTries,arrayTypes[2]);
                averageForMostlySortedInput  = average;
            }
            else{
               computeAverage(n, new int[numTries],numTries,arrayTypes[0]);
                computeAverage(n, new int[numTries],numTries,arrayTypes[1]);
                computeAverage(n, new int[numTries],numTries,arrayTypes[2]);



            }

        }

        //NB: THE CODE BELOW IS USED TO WRITE TO AN OUTPUT FILE SO WE CAN PLOT GRAPHS


//        try {
//            PrintStream output = new PrintStream("generateRandomInput1.txt");
//            double average;
//            for(int n = 1; n <= maxArraySize;n++) {
//                if(n == nForVariance){
//                    average = computeAverage(n,countsRandomInput, numTries, arrayTypes[0]);
//                    averageForRandomInput = average;
//                }
//                else{
//                    average = computeAverage(n,new int[numTries],numTries,arrayTypes[0]);
//                }
//                output.printf(n + "," + average);
//                output.println();
//            }
//            output.close();
//        }
//        catch(Exception e) {
//            e.getStackTrace();
//        }
//
//        try {
//            PrintStream output = new PrintStream("generatePartiallySortedInput1.txt");
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
//                output.printf(n + "," + average);
//                output.println();
//            }
//            output.close();
//        }
//        catch(Exception e) {
//            e.getStackTrace();
//        }
//
//        try {
//            PrintStream output = new PrintStream("generateMostlySortedInput1.txt");
//            double average;
//            for(int n = 1; n <= maxArraySize;n++) {
//                if(n == nForVariance){
//                    average = computeAverage(n,countsMostlySortedInput, numTries, arrayTypes[2]);
//                    averageForMostlySortedInput = average;
//                }
//                else{
//                    average = computeAverage(n,new int[numTries],numTries,arrayTypes[2]);
//                }
//                output.printf(n + "," + average);
//                output.println();
//            }
//            output.close();
//        }
//        catch(Exception e) {
//            e.getStackTrace();
//        }

        //VARIANCE FOR RANDOM INPUT
        System.out.println( "Variance For  RandomInput: " + computeVariance(countsRandomInput,averageForRandomInput,numTries));

        //VARIANCE FOR PARTIALLY SORTED INPUT
        System.out.println("Variance For PartiallySortedInput: " +computeVariance( countsPartiallySortedInput,averageForPartiallySortedInput,numTries));

        //VARIANCE FOR MOSTLY SORTED INPUT

        System.out.println("Variance For MostlySortedInput: "+computeVariance(countsMostlySortedInput,averageForMostlySortedInput,numTries));


        System.out.println("Average For RandomInput: " + averageForRandomInput);
        System.out.println("Average For PartiallySortedInput: " + averageForPartiallySortedInput);
        System.out.println("Average For MostlySortedInput: " + averageForMostlySortedInput);

        System.out.println("Standardized Variance RandomInput: " + computeVariance(countsRandomInput,averageForRandomInput,numTries)/Math.pow(averageForRandomInput,2));
        System.out.println("Standardized Variance PSInput: " + computeVariance(countsPartiallySortedInput,averageForPartiallySortedInput,numTries)/Math.pow(averageForPartiallySortedInput,2));
        System.out.println("Standardized Variance MSInput: " + computeVariance(countsMostlySortedInput,averageForMostlySortedInput,numTries)/Math.pow(averageForMostlySortedInput,2));







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

    public static double computeAverage(int arraysize,int[]countsArray, int numTries, String arrayInputType){
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
            countsArray[i] = currCount;

//            System.out.println("-------Current count-----");
//            System.out.println(currCount);

            totalCounter += currCount;
        }
//        System.out.println("-----------Average-------------");
//        System.out.println(totalCounter/numTries);


        return totalCounter/numTries;

    }

    public static double computeVariance(int[]array,double mean,int numTries){

        double sqDiffSum = 0;

        for(int i = 0; i < array.length;i++){
            sqDiffSum += Math.pow((array[i] - mean),2);
        }

        return sqDiffSum/numTries;
    }
}
