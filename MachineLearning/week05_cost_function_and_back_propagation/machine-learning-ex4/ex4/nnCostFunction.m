function [J, grad] = nnCostFunction(nn_params, ...
                                   input_layer_size, ...
                                   hidden_layer_size, ...
                                   num_labels, ...
                                   X, y, lambda)
%NNCOSTFUNCTION Implements the neural network cost function for a two layer
%neural network which performs classification
%   [J grad] = NNCOSTFUNCTON(nn_params, hidden_layer_size, num_labels, ...
%   X, y, lambda) computes the cost and gradient of the neural network. The
%   parameters for the neural network are "unrolled" into the vector
%   nn_params and need to be converted back into the weight matrices. 
% 
%   The returned parameter grad should be a "unrolled" vector of the
%   partial derivatives of the neural network.
%

% Reshape nn_params back into the parameters Theta1 and Theta2, the weight matrices
% for our 2 layer neural network
Theta1 = reshape(nn_params(1:hidden_layer_size * (input_layer_size + 1)), ...
                 hidden_layer_size, (input_layer_size + 1));

Theta2 = reshape(nn_params((1 + (hidden_layer_size * (input_layer_size + 1))):end), ...
                 num_labels, (hidden_layer_size + 1));

% Setup some useful variables
m = size(X, 1);
         
% You need to return the following variables correctly 
% J = 0;
Theta1_grad = zeros(size(Theta1));
Theta2_grad = zeros(size(Theta2));

% ====================== YOUR CODE HERE ======================
% Instructions: You should complete the code by working through the
%               following parts.
%
% Part 1: Feedforward the neural network and return the cost in the
%         variable J. After implementing Part 1, you can verify that your
%         cost function computation is correct by verifying the cost
%         computed in ex4.m
%
% Part 2: Implement the backpropagation algorithm to compute the gradients
%         Theta1_grad and Theta2_grad. You should return the partial derivatives of
%         the cost function with respect to Theta1 and Theta2 in Theta1_grad and
%         Theta2_grad, respectively. After implementing Part 2, you can check
%         that your implementation is correct by running checkNNGradients
%
%         Note: The vector y passed into the function is a vector of labels
%               containing values from 1..K. You need to map this vector into a 
%               binary vector of 1's and 0's to be used with the neural network
%               cost function.
%
%         Hint: We recommend implementing backpropagation using a for-loop
%               over the training examples if you are implementing it for the 
%               first time.
%
% Part 3: Implement regularization with the cost function and gradients.
%
%         Hint: You can implement this around the code for
%               backpropagation. That is, you can compute the gradients for
%               the regularization separately and then add them to Theta1_grad
%               and Theta2_grad from Part 2.
%

% format y
yM = repelem(1:num_labels, m, 1);  % m * num_labels
for r = 1:m
    yM(r,:) = yM(r,:) == y(r);
end

% calculate J
A1 = [ones(m,1), X];  % m*(1 + input_layer_size)
A2 = sigmoid(A1 * Theta1');  % hidden layer m*hidden_layer_size
A2 = [ones(m,1), A2]; % hidden layer m*(1 + hidden_layer_size)
Out = sigmoid(A2 * Theta2'); % output layer m*num_labels

% fprintf('size of yM: %g*%g, sizeOf Out: %g*%g\n', size(yM, 1), size(yM, 2), size(Out, 1), size(Out, 2))

% J is the sum
jMat = yM .* log(Out) + (1-yM) .* log(1-Out);
J = -sum(sum(jMat))/m;

% regulate J
J = J + lambda/2/m * (sum(sum(Theta1(:,2:input_layer_size+1) .^ 2)) + sum(sum(Theta2(:, 2:hidden_layer_size+1) .^ 2)));

% Calculate gradient
eposilon3 = Out - yM;  % m * num_labels
eposilon2 = eposilon3 * Theta2; % m * (hidden_layer_size+1)
eposilon2 = eposilon2 .* A2 .* (1-A2);  % g' = A2 * (1-A2)
eposilon2 = eposilon2(:,2:hidden_layer_size+1);  % delete the zero index, convert to m*hidden_layer_size

% equal to 
% Theta1_grad = (A1' * eposilon2)/m;
% Theta2_grad = (A2' * eposilon3)/m;
for i = 1:m
    Theta1_grad = Theta1_grad + eposilon2(i,:)' * A1(i,:);
    Theta2_grad = Theta2_grad + eposilon3(i,:)' * A2(i,:);
end

Theta1_grad = Theta1_grad /m;
Theta2_grad = Theta2_grad /m;

% regulate gradient
Theta1_reg = lambda/m * Theta1;
Theta1_reg(:,1) = 0;
Theta2_reg = lambda/m * Theta2;
Theta2_reg(:,1) = 0;

Theta1_grad = Theta1_grad + Theta1_reg;
Theta2_grad = Theta2_grad + Theta2_reg;


% -------------------------------------------------------------

% =========================================================================

% Unroll gradients
grad = [Theta1_grad(:) ; Theta2_grad(:)];


end
