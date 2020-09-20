# Cost Function and Back Propagation

## How to build a neural network

First, pick a network architecture; choose the layout of your neural network, including how many hidden units in each layer and how many layers in total you want to have.

1. Number of input units = dimension of features x(i)
2. Number of output units = number of classes
3. Number of hidden units per layer = usually more the better (must balance with cost of computation as it increases with more hidden units)
4. Defaults: 1 hidden layer. If you have more than 1 hidden layer, then it is recommended that you have the same number of units in every hidden layer.

## Training a Neural Network

1. Randomly initialize the weights
2. Implement forward propagation to get hΘ(x(i)) for any x(i)
3. Implement the cost function
4. Implement backpropagation to compute partial derivatives
5. Use gradient checking to confirm that your backpropagation works. Then disable gradient checking.
6. Use gradient descent or a built-in optimization function to minimize the cost function with the weights in theta.