% Generated by roxygen2: do not edit by hand
% Please edit documentation in R/regression.R, R/flink-ddf.R, R/spark-ddf.R
\docType{methods}
\name{ml.logistic.regression}
\alias{ml.logistic.regression}
\alias{ml.logistic.regression,FlinkDDF-method}
\alias{ml.logistic.regression,SparkDDF-method}
\title{ML Logistic Regression}
\usage{
ml.logistic.regression(x, ...)

\S4method{ml.logistic.regression}{FlinkDDF}(x, numIterations = 10L,
  stepSize = 1, miniBatchFraction)

\S4method{ml.logistic.regression}{SparkDDF}(x, numIterations = 10L,
  stepSize = 1, miniBatchFraction)
}
\arguments{
\item{x}{a Distributed Data Frame.}

\item{maxIters}{max times of iterations.}
}
\value{
a LogisticRegression object
}
\description{
Train a Logistic Regression algorithm

Logistic Regression using Flink ML's Logistic Regression

Logistic Regression using Spark mllib's Logistic Regression
}

