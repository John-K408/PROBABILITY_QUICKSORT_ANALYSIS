# PROBABILITY_QUICKSORT_ANALYSIS

## Introduction


In this program,we were analyzing the runtime of quicksort using both a deterministic and randomized version of the algorithm.


## HOW TO RUN PROGRAM



The program is divided into two parts - the deterministic algorithm and the randomized algorithm. To run any, a user can choose to go with
the default value of the maximum array size provided or select their own. This variable indicates the maximum size of the array the user
wants to analyze for all array types. The user can also use the default value for numTries or choose a preferred one. The 
numTries variable indicates the number of simulations of array of given size and type to run in order to compute the average count. Lastly,
a user can choose the default value for nForVariance or change to a preferred value - note that nForVariance cannot be more than maxArraySize.
The nForVariance Variable indicates the n value to select for variance analysis. 

### METHOD DESCRIPTIONS

**Main Method** : 

        The main method sets up the initial arrays such as the one that contains averages for each arraySize (averageForXInput)  and the one that contains multiple averages for a given n (averageRunsXInput). It also prints all needed summary data in the console after the program is done running so we can analyze.
        
        
**QuickSort** :

        This method performs the actual sorting with the help of the partition method. Depending on which class it's in, QuickSortRandom.java or
        QuickSortDeterministic.java, it chooses it's pivot deterministically or randomly. Since quickSort moves the inputs of an array in-place,we
        do not return the array but instead, we return the number of comparisons that was made while sorting.
        
        
        
**Partition**:

        The partition algorithm is responsible for moving all elements less than pivot to the left of it and all other elements to the right of it
        withing the provided range. To help us know the number of comparisons, the partition method returns an array containing both the count and
        the position of the partition.
        
        
 **Swap**: 
 
      The swap method takes in an array and two indices within the array then swaps the elements at both indices. Since it moves the element 
      in-place, it returns nothing as result.
      
      
  **computeAverage**:
  
  
      This method generates an array of a specified size and type and runs quicksort on it getting the count each time. The program then repeats                                                                            this "numTries" number of times adding the counts each time. It then divides the sum of all counts by the numTries to get the average count for that array size and type.
      

 **computeAverageOfAverages**:

      This method takes in an array containing multiple averages for a given n and returns the average of them to be used for variance and
      standard variance computation.
      
 **ComputeVariance**: 
    
      This method takes in an array of averages for a given array size and type, and the average value of these averages and uses that to compute
      variance according to the formula for variance.
      
      
 **Compute Standard Variance**:
 
 
    This program takes in the variance and averageOfAverages for a given array size and type, and divides the variance by the square of the    averageOfAveraes.
  
  
        
        
 
        



To run this program (we used IntelliJ - any java IDE should be fine), make sure all files are in the same location - preferrably the location the
IDE saves files and click run. Since the program is in two parts, a user must choose which class to run first. It may take a while to run depending
on the user's laptop speed - our maxArraySize value is huge. After running the program should print all needed data - average, variance, and
standardized variance for all arraytypes.
