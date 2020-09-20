function plotData(X, y)
%PLOTDATA Plots the data points X and y into a new figure 
%   PLOTDATA(x,y) plots the data points with + for the positive examples
%   and o for the negative examples. X is assumed to be a Mx2 matrix.

% Create New Figure
figure; hold on;

% ====================== YOUR CODE HERE ======================
% Instructions: Plot the positive and negative examples on a
%               2D plot, using the option 'k+' for the positive
%               examples and 'ko' for the negative examples.
%

m = length(y);
positiveSize = 0;
for i = 1:m 
    if y(i) == 1
        positiveSize = positiveSize + 1;
    end
end

X1 = zeros(positiveSize, 2);
X2 = zeros(m-positiveSize, 2);

j = 1; k = 1;
for i = 1:m
    if y(i) == 1
        X1(j,:) = X(i,:);
        j = j + 1;
    else
        X2(k,:) = X(i,:);
        k = k + 1;
    end
end

scatter(X1(:,1), X1(:,2), '+');
scatter(X2(:,1), X2(:,2), 'o');


% =========================================================================



hold off;

end
