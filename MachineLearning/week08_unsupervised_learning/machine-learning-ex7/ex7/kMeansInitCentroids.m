function centroids = kMeansInitCentroids(X, K)
%KMEANSINITCENTROIDS This function initializes K centroids that are to be 
%used in K-Means on the dataset X
%   centroids = KMEANSINITCENTROIDS(X, K) returns K initial centroids to be
%   used with the K-Means on the dataset X
%

% You should return this values correctly
centroids = zeros(K, size(X, 2));

% ====================== YOUR CODE HERE ======================
% Instructions: You should set centroids to randomly chosen examples from
%               the dataset X
%

m = size(X, 1);
J = 2*m; % maximum cannot be bigger than 2*m
max_iters = 10;

for iter = 1:max_iters
    initial_centroids = zeros(K, size(X, 2));
    centroid_idx = randsample(m, K, true);
    for j = 1:K
        initial_centroids(j,:) = X(centroid_idx(j),:);
    end
    [this_centroids, idx] = runkMeans(X, initial_centroids, max_iters);
%     idx = findClosestCentroids(X, initial_centroids);
%     this_centroids = computeCentroids(X, idx, K);
    thisJ = 0;
    for i = 1:m
        diff = X(i,:) - this_centroids(idx(i),:);
        thisJ =  thisJ + diff*diff';
    end
    if thisJ < J 
        J = thisJ;
        centroids = this_centroids;
    end
end

% =============================================================

end

