import java.io.PrintStream;
import java.util.Arrays;

public class QuickSortRandom {
    public static  void main(String[] args) {
        String[] arrayTypes = new String[]{"generateRandomInput","generatePartiallySortedInput","generateMostlySortedInput"};

//       changeable variables
        int maxArraySize = 1000;
        int numTries = 100;
        int nForVariance = 800;

        if(nForVariance > maxArraySize) System.err.println("nForVariance cannot exceed maximum array size");


        //These array will be filled with the individual counts for each instance of the array of given
        // size n,that will be generated in ComputeAverage
        //The array that we will use depends on the configuration we are looking for.
        double[] averageRunsRandomInput = new double[numTries];
        double []averageRunsPartiallySortedInput = new double[numTries];
        double[] averageRunsMostlySortedInput =new double[numTries];

        double[] averageForRandomInput = new double[maxArraySize + 1];
        double [] averageForPartiallySortedInput = new double[maxArraySize + 1];
        double [] averageForMostlySortedInput = new double[maxArraySize + 1];




        //stored averages for line plot
        for(int n = 1; n <= maxArraySize;n++){

                averageForRandomInput[n] = computeAverage(n,numTries,arrayTypes[0]);

                averageForPartiallySortedInput[n] = computeAverage(n,numTries,arrayTypes[1]);

                averageForMostlySortedInput[n] = computeAverage(n,numTries,arrayTypes[2]);

        }

        //run average on each configuration for numTries number of times and store the values to be used for histogram.
        for(int i = 0; i < numTries;i++){
            averageRunsRandomInput[i] = computeAverage(nForVariance,numTries,arrayTypes[0]);
            averageRunsPartiallySortedInput[i] = computeAverage(nForVariance,numTries,arrayTypes[1]);
            averageRunsMostlySortedInput[i] = computeAverage(nForVariance,numTries,arrayTypes[2]);
        }



        //NB: THE CODE BELOW IS USED TO WRITE TO AN OUTPUT FILE SO WE CAN PLOT GRAPHS

        //get the average of all the values in the histogram to be used for empirical variance and standard variance computation



        double averageOfAveragesRandomInput = computeAverageOfAverages(averageRunsRandomInput);
        double averageOfAveragesPartiallySortedInput = computeAverageOfAverages(averageRunsPartiallySortedInput);
        double averageOfAveragesMostlySortedInput = computeAverageOfAverages(averageRunsMostlySortedInput);



        System.out.println("Average For RandomInput: " + averageForRandomInput[nForVariance]);
        System.out.println("Average For PartiallySortedInput: " + averageForPartiallySortedInput[nForVariance]);
        System.out.println("Average For MostlySortedInput: " + averageForMostlySortedInput[nForVariance]);


        //VARIANCE FOR RANDOM INPUT
        double varianceForRandomInput = computeVariance(averageRunsRandomInput,averageOfAveragesRandomInput);
        System.out.println( "Variance For  RandomInput: " + varianceForRandomInput );

        //VARIANCE FOR PARTIALLY SORTED INPUT
        double varianceForPartiallySortedInput = computeVariance( averageRunsPartiallySortedInput,averageOfAveragesPartiallySortedInput);
        System.out.println("Variance For PartiallySortedInput: " + varianceForPartiallySortedInput);

        //VARIANCE FOR MOSTLY SORTED INPUT
        double varianceForMostlySortedInput = computeVariance(averageRunsMostlySortedInput,averageOfAveragesMostlySortedInput);
        System.out.println("Variance For MostlySortedInput: "+varianceForMostlySortedInput);




        System.out.println("Standardized Variance RandomInput: " + computeStandardVariance(varianceForRandomInput,averageOfAveragesRandomInput));
        System.out.println("Standardized Variance PartiallySortedInput: " + computeStandardVariance(varianceForPartiallySortedInput,averageOfAveragesPartiallySortedInput));
        System.out.println("Standardized Variance MostlySortedInput: " + computeStandardVariance(varianceForMostlySortedInput,averageOfAveragesMostlySortedInput));







    }



    public static int QuickSort(int[] array,int low, int high,int count){
        if(high > low){
            //the randomIndex is the index of the number we will be using to group the array from low to high
            int randomIndex = low + (int)(Math.random()*(high - low + 1));

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



            totalCounter += currCount;
        }

        return totalCounter/numTries;

    }

    public static double computeAverageOfAverages(double[]array){

        double avSum = 0;

        for(double a: array){
            avSum += a;
        }
       return  avSum/array.length;
    }

    public static double computeVariance(double[]array,double mean){


        double sqDiffSum = 0;

        for(int i = 0; i < array.length;i++){
            sqDiffSum += Math.pow((array[i] - mean),2);
        }

        return sqDiffSum/(array.length - 1);
    }

    public static double computeStandardVariance(double variance, double mean){
        return variance/Math.pow(mean,2);
    }
}
