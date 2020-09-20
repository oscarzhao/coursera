# --*-- coding: utf-8 --*--

import bayes
import random
import numpy

def calcMostFreq(vocabList, fullText):
  import operator
  freqDict = {}
  for token in vocabList:
    freqDict[token] = fullText.count(token)
  sortedFreq = sorted(freqDict.items(), key=operator.itemgetter(1), reverse=True)
  return sortedFreq[:30]

def localWords(feed1, feed0):
  import feedparser
  docList = []; classList= []; fullText=[]
  minLen = min(len(feed1['entries']), len(feed0['entries']))
  # print('minlen=%d'%minLen)
  for i in range(minLen):
    # visit one rss source every time
    wordList = bayes.textParse(feed1['entries'][i]['summary'])
    docList.append(wordList)
    fullText.extend(wordList)
    classList.append(1)
    
    # visit rss 0
    wordList = bayes.textParse(feed0['entries'][i]['summary'])
    docList.append(wordList)
    fullText.extend(wordList)
    classList.append(0)

    # remove most frequent words
    vocabList = bayes.createVocabList(docList)
    top30words = calcMostFreq(vocabList, fullText)
    for pairW in top30words:
      if pairW[0] in vocabList: 
        vocabList.remove(pairW[0])
    trainingSet = list(range(2*minLen)); testSet = []
    for i in range(20):
      randIdx = int(random.uniform(0, len(trainingSet)))
      testSet.append(trainingSet[randIdx])
      del(trainingSet[randIdx])
  
  trainMat = []; trainClasses = []
  for docIdx in trainingSet:
    # print("doc idx:%d, len=%d" %( docIdx, len(docList)))
    trainMat.append(bayes.bagOfWords2VecMN(vocabList, docList[docIdx]))
    trainClasses.append(classList[docIdx])
  
  p0V, p1V, pSpam = bayes.trainNB0(numpy.array(trainMat), numpy.array(trainClasses))

  errorCount = 0
  for docIdx in testSet:
    wordVector = bayes.bagOfWords2VecMN(vocabList, docList[docIdx])
    if bayes.classifyNB(numpy.array(wordVector), p0V, p1V, pSpam) != classList[docIdx]:
      errorCount += 1
  print('the error rate is: %.2f' %(float(errorCount)/len(testSet)))

  return vocabList, p0V, p1V

# 
def getTopWords(ny, sf):
  import operator
  vocabList, p0V, p1V = localWords(ny, sf)
  topNY = []; topSF = []
  for i in range(len(p0V)):
    if p0V[i] > -6.0:
      topSF.append((vocabList[i], p0V[i]))
    if p1V[i] > -6.0:
      topNY.append((vocabList[i], p1V[i]))
  
  sortedSF = sorted(topSF, key=lambda pair:pair[1], reverse=True)
  print("SF**SF**SF**SF**SF**SF**SF**SF**SF**SF**")
  for item in sortedSF:
    print(item[0])
  
  sortedNY = sorted(topNY, key=lambda pair:pair[1], reverse=True)
  print("NY**NY**NY**NY**NY**NY**NY**NY**NY**NY**")
  for item in sortedNY:
    print(item[0])


if __name__ == '__main__':
  ny = feedparser.parse('http://newyork.craigslist.org/stp/index.rss')
  sf = feedparser.parse('http://sfbay.craigslist.org/stp/index.rss')
  rss.getTopWords(ny, sf)
