import math

import matplotlib.pyplot as plt
import csv

import numpy as np

X1 = []
Y1 = []

X2 = []
Y2 = []

X3 = []
Y3 = []

x = np.linspace(0, 10000, 10000)
y = 2 * x * np.log(x)

# "generateRandomInput","generatePartiallySortedInput","generateMostlySortedInput"
# "generateRandomInput","generatePartiallySortedInput","generateMostlySortedInput"

with open('/Applications/IntelliJ IDEA CE.app/Contents/bin/PROBABILITY_QUICKSORT_ANALYSIS/src/generateRandomInput1.txt', 'r') as datafile:
    graph1 = csv.reader(datafile, delimiter=',')

    for ROWS in graph1:
        X1.append(float(ROWS[0]))
        Y1.append(float(ROWS[1]))

    # plt.plot(X, Y, color='red')
# plt.show()

with open('/Applications/IntelliJ IDEA CE.app/Contents/bin/PROBABILITY_QUICKSORT_ANALYSIS/src/generatePartiallySortedInput1.txt', 'r') as datafile:
    graph2 = csv.reader(datafile, delimiter=',')

    for ROWS in graph2:
        X2.append(float(ROWS[0]))
        Y2.append(float(ROWS[1]))

    # plt.plot(X, Y,color='green')
# plt.show()

with open('/Applications/IntelliJ IDEA CE.app/Contents/bin/PROBABILITY_QUICKSORT_ANALYSIS/src/generateMostlySortedInput1.txt', 'r') as datafile:
    graph3 = csv.reader(datafile, delimiter=',')

    for ROWS in graph3:
        X3.append(float(ROWS[0]))
        Y3.append(float(ROWS[1]))

    # plt.plot(X, Y,color='blue')
# plt.show()


plt.subplot(1, 3, 1)
plt.plot(x, y, color="black")
plt.plot(X1, Y1, color='blue')
plt.title('Graph of the Deterministic Algorithm')
plt.xlabel('Array SIze')
plt.ylabel('Execution Runtime')
plt.legend(["2nln(n)", "generateRandomInput"])

plt.subplot(1, 3, 2)
plt.plot(x, y, color="black")
plt.plot(X2, Y2, color='green')
plt.xlabel('Array SIze')
plt.legend(["2nln(n)", "generatePartiallySortedInput"])

plt.subplot(1, 3, 3)
plt.plot(x, y, color="black")
plt.plot(X3, Y3, color='red')
plt.xlabel('Array SIze')
plt.legend(["2nln(n)", "generateMostlySortedInput"])




plt.show()

