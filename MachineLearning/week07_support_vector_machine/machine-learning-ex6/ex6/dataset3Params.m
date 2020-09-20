function [C, sigma] = dataset3Params(X, y, Xval, yval)
%DATASET3PARAMS returns your choice of C and sigma for Part 3 of the exercise
%where you select the optimal (C, sigma) learning parameters to use for SVM
%with RBF kernel
%   [C, sigma] = DATASET3PARAMS(X, y, Xval, yval) returns your choice of C and 
%   sigma. You should complete this function to return the optimal C and 
%   sigma based on a cross-validation set.
%

% You need to return the following variables correctly.
C = 1;
sigma = 0.01;

% ====================== YOUR CODE HERE ======================
% Instructions: Fill in this function to return the optimal C and sigma
%               learning parameters found using the cross validation set.
%               You can use svmPredict to predict the labels on the cross
%               validation set. For example, 
%                   predictions = svmPredict(model, Xval);
%               will return the predictions on the cross validation set.
%
%  Note: You can compute the prediction error using 
%        mean(double(predictions ~= yval))
%

% fuck, cannot use *2 
% must follow the doc, use *3
cand_c= [0.1; 0.3; 1; 3; 10; 30; 100; 300];
cand_sigma = [0.01; 0.03; 0.1; 0.3; 1; 3; 10; 30];
best_predict = 1.00;

for i = 1:size(cand_c, 1)
    for j = 1:size(cand_sigma,1)
        model= svmTrain(X, y, cand_c(i), @(x1, x2) gaussianKernel(x1, x2, cand_sigma(j)));
        predictions = svmPredict(model, Xval);
        this_predict = mean(double(predictions ~= yval));
        if best_predict > this_predict
            best_predict = this_predict;
            C = cand_c(i);
            sigma = cand_sigma(j);
        end
    end
end


% =========================================================================

end
