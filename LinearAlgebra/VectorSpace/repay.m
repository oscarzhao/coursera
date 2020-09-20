% replay a loan
% 4.8 Exercise 23
function [left_loans] = repay(loan, rate, monthly)
    left_loans = zeros(10, 1);
    i = 1;
    left_loans(i) = loan;
    while loan > 0
        loan = (rate + 1) * loan - monthly;
        if loan < 0
            loan = 0;
        end
        i = i+1;
        left_loans(i) = loan;
    end
end

% left_loans = repay(10000, 0.01, 450)
% plotxy(1:1:length(left_loans), left_loans)
