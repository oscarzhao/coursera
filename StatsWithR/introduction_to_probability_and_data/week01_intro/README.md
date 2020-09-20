# Install R and RStudio:

First, you will need to install R and RStudio. R is the name of the programming language itself and RStudio is a convenient interface.

    1. Install R: Go to https://cran.r-project.org/ and follow the link for your operating system.
    2. Install RStudio: Go to https://www.rstudio.com/products/rstudio/download/ and click on the installer link for your operating system.

See this video for step-by-step installation instructions if needed.

NOTE: If you already have R and RStudio installed, you should still visit these links to confirm that you have the most recent versions of these software. The most recent version of R can be found on The R Project for Statistical Computing page and the most recent version of RStudio can be found on the Download RStudio page. Please install the most recent versions before proceeding.

# Install R packages:
## Install and load devtools:

We will use the devtools package to install the statsr package associated with this course. We need to install and load this package. Launch RStudio, and enter the following commands in the *Console*:

```
install.packages("devtools")
library(devtools)
```

Install all other packages:

Now we can install the rest of the packages we will use in this lab. Type the following commands in the *Console* as well:

```
install.packages("dplyr")
install.packages("ggplot2")
install.packages("shiny")
install_github("StatsWithR/statsr")
```

The uses of these packages will be discussed in detail in the lab(s).

If install_github breaks on Windows with UNC network paths or sometimes when there are places in the path name, you may follow our mentor David Hood's (thanks, David) solution here.
Download and Knit the lab:

[Intro_to_R_Course.RMD](https://d3c33hcgiwev3.cloudfront.net/_ef0f4ba320a5d8bd7759404a4849a425_intro_to_r_Coursera.Rmd?Expires=1510185600&Signature=JEueaCheUKFCewLLDUI~Jtq9UBWYQOOZhFRhc-Rn1a1kbgm09k9copVXQKI7TFKgjbC46H3aK6iBd2dkzJd~JX3kezQ5I2-lbTIXDO2vtSEQ56vFOqPL6a4P~I-nL1tRNXWA10~8-5wQyNmA1grvUQ7FUfIgWfNREG5LLW2Z2ec_&Key-Pair-Id=APKAJLTNE6QMUY6HBC5A "Intro to R course")

Next, download the R Markdown file linked below, open it in RStudio, and click on Knit. This document contains the instructions for the lab. IMPORTANT: Use right-click, then save link as to save the below R Markdown file. If Chrome doesn't work, switch to Firefox or other browsers when downloading the file. You must download and Knit this file and be able to view the instructions in it to complete the Week 1 Lab, so make sure to complete these steps before starting the lab.
intro_to_r_Coursera.Rmd

As you go through the contents of the lab instructions document you will encounter multiple choice questions, make sure to submit your answers to those questions in the lab section (next item) to get credit. The ones marked with Exercise are for your own practice -- you don't need to submit your answers to them.