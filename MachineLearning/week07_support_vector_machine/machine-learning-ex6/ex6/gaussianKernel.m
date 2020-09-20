function sim = gaussianKernel(x1, x2, sigma)
%RBFKERNEL returns a radial basis function kernel between x1 and x2
%   sim = gaussianKernel(x1, x2) returns a gaussian kernel between x1 and x2
%   and returns the value in sim

% Ensure that x1 and x2 are column vectors
x1 = x1(:); x2 = x2(:);

% You need to return the following variables correctly.
% sim = 0;

% ====================== YOUR CODE HERE ======================
% Instructions: Fill in this function to return the similarity between x1
%               and x2 computed using a Gaussian kernel with bandwidth
%               sigma
%
%

% WHY do not do feature normalize???
% Why
% x1_norm = featureNormalize(x1);
% x2_norm = featureNormalize(x2);

% diff_norm = x1_norm - x2_norm;
diff = x1-x2;

sim = exp(-diff'*diff/(2*sigma^2));


% =============================================================
    
end
