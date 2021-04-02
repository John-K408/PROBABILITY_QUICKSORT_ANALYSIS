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

x = np.linspace(0, 50, 800)
y = 2 * x * np.log(np.exp(x))


# "generateRandomInput","generatePartiallySortedInput","generateMostlySortedInput"
# "generateRandomInput","generatePartiallySortedInput","generateMostlySortedInput"

with open('generateRandomInput1.txt', 'r') as datafile:
    graph1 = csv.reader(datafile, delimiter=',')

    for ROWS in graph1:
        X1.append(float(ROWS[0]))
        Y1.append(float(ROWS[1]))

    # plt.plot(X, Y, color='red')
# plt.show()

with open('generatePartiallySortedInput1.txt', 'r') as datafile:
    graph2 = csv.reader(datafile, delimiter=',')

    for ROWS in graph2:
        X2.append(float(ROWS[0]))
        Y2.append(float(ROWS[1]))

    # plt.plot(X, Y,color='green')
# plt.show()

with open('generateMostlySortedInput1.txt', 'r') as datafile:
    graph3 = csv.reader(datafile, delimiter=',')

    for ROWS in graph3:
        X3.append(float(ROWS[0]))
        Y3.append(float(ROWS[1]))

    # plt.plot(X, Y,color='blue')
# plt.show()


plt.subplot(1, 3, 1)
plt.plot(x, y, color="black")
plt.plot(X1, Y1, color='tab:blue')
plt.title('Graph of the Random Algorithm')
plt.xlabel('Array SIze')
plt.ylabel('Execution Runtime')
plt.legend(["2nln(n)", "generateRandomInput"])

plt.subplot(1, 3, 2)
plt.plot(x, y, color="black")
plt.plot(X2, Y2, color='tab:green')
plt.xlabel('Array SIze')
plt.legend(["2nln(n)", "generatePartiallySortedInput"])

plt.subplot(1, 3, 3)
plt.plot(x, y, color="black")
plt.plot(X3, Y3, color='tab:red')
plt.xlabel('Array SIze')
plt.legend(["2nln(n)", "generateMostlySortedInput"])




plt.show()

