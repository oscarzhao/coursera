# Inference for Categorical Data

## Chi-Squere

The way to calculate chi-square test statistic:

$$\frac{point\ esitimate - null\ value}{SE\ of\ point\ estimate}$$

But, the SE here is not calculated in normal ways, it's calculated as

$$SE = \sqrt{the count\ under\ the\ null}$$

(Check the examples in Page 288)

Thus, we write the formula of chi-square test statistic as:

$$\chi^2 = \sum_{i=1}^{group\ number}\frac{(observed\ count_i - null\ count_i)^2}{null\ count_i}$$

## What is p-value?

In hypothesis testing for one categorical variable, generate simulated samples based on the null hypothesis, and then calculate the number of samples that are at least as extreme as the observed data.

P-value is not the proportion of samples that are at least as extreme as the null, rather it is the proportion of sample that is “*at least as extreme as the observed data*”.

## Params used to Verify Success-Failure Conditions

For *confidence intervals* use p^ (observed sample proportion) when calculating the standard error and checking the success/failure condition. For hypothesis tests use p0 (null value) when calculating the standard error and checking the success/failure condition.

Use the observed number of successes and failures when calculating a confidence interval for a proportion, but not when doing a hypothesis test. In a hypothesis test for a proportion, you should use np0 and n(1 − p0) successes and failures; that is, the expected number based on the null proportion.