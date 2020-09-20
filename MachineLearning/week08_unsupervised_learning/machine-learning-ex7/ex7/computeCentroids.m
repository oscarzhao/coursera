function centroids = computeCentroids(X, idx, K)
%COMPUTECENTROIDS returns the new centroids by computing the means of the 
%data points assigned to each centroid.
%   centroids = COMPUTECENTROIDS(X, idx, K) returns the new centroids by 
%   computing the means of the data points assigned to each centroid. It is
%   given a dataset X where each row is a single data point, a vector
%   idx of centroid assignments (i.e. each entry in range [1..K]) for each
%   example, and K, the number of centroids. You should return a matrix
%   centroids, where each row of centroids is the mean of the data points
%   assigned to it.
%

% Useful variables
[m n] = size(X);

% You need to return the following variables correctly.
% centroids = zeros(K, n);


% ====================== YOUR CODE HERE ======================
% Instructions: Go over every centroid and compute mean of all points that
%               belong to it. Concretely, the row vector centroids(i, :)
%               should contain the mean of the data points assigned to
%               centroid i.
%
% Note: You can use a for-loop over the centroids to compute this.
%
% splitapply is a better choice

% counts = accumarray(idx, ones(m, 1));
% for i = 1:n
%     centroids(:,i) = accumarray(idx, X(:,i));
% end
% % 
% % for i = 1:m
% %     index = idx(i);
% %     counts(index) = counts(index) + 1;
% %     centroids(index,:) = centroids(index,:) + X(i,:);
% % end
% % 
% 
% 
% 
% for j = 1:K
%     centroids(j,:) = centroids(j,:) / counts(j);
% end

tmpM = ones(K, m) .* (1:K)';
tmpM = tmpM == idx';
counts = tmpM * ones(m,1);
centroids = tmpM * X;
centroids = centroids ./ counts;

% =============================================================


end
