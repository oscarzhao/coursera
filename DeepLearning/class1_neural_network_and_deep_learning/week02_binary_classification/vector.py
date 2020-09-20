#! -*- coding: utf-8 -*-

import numpy as np

def image2vector(x):
  """
  Argument:
  image -- a numpy array of shape (length, height, depth)
  
  Returns:
  v -- a vector of shape (length*height*depth, 1)
  """
  v = x.reshape((x.shape[0]*x.shape[1]*x.shape[2], 1))
  return v

def normalizeRows(x):
  """
  Implement a function that normalizes each row of the matrix x (to have unit length).
  
  Argument:
  x -- A numpy matrix of shape (n, m)
  
  Returns:
  x -- The normalized (by row) numpy matrix. You are allowed to modify x.
  """
  x_norm = np.linalg.norm(x, axis=1, keepdims=True)
  print("x norm=" + str(x_norm))
  x = np.divide(x, x_norm)
  return x

if __name__ == '__main__':
  image = np.array([[[ 0.67826139,  0.29380381],
    [ 0.90714982,  0.52835647],
    [ 0.4215251 ,  0.45017551]],

    [[ 0.92814219,  0.96677647],
    [ 0.85304703,  0.52351845],
    [ 0.19981397,  0.27417313]],

    [[ 0.60659855,  0.00533165],
    [ 0.10820313,  0.49978937],
    [ 0.34144279,  0.94630077]]])
  print ("image2vector(image) = " + str(image2vector(image)))

  x = np.array([
    [0, 3, 4],
    [1, 6, 4]])
  print("normalizeRows(x) = " + str(normalizeRows(x)))
