% Put money in bank, and let it increase
% 4.8 Exercise 24
function [total] = accumulate_money(balance, monthlyIn, month)
    total = zeros(month, 1);
    total(1) = balance;
    i = 1;
    while i < month
        total(i+1) = 1.005 * total(i) + monthlyIn;
        i = i + 1;
    end
end

% money = accumulate_money(1000, 200, 60)
% plotxy(1:1:length(money), money)

