# --*-- coding: utf-8 --*--

from numpy import *
from math import log

# convert word table to vectors
def loadDataSet():
    postingList = [
        ['my', 'dog', 'has', 'flea', 'problem', 'help', 'please'],
        ['maybe', 'not', 'take', 'him', 'to', 'dog', 'park', 'stupid'],
        ['my', 'dalmation', 'is', 'so', 'cute', 'I', 'love', 'him'],
        ['stop', 'posting', 'stupid', 'worthless', 'garbage'],
        ['mr', 'licks', 'ate', 'my', 'steak', 'how', 'to', 'stop', 'him'],
        ['quit', 'buying', 'worthless', 'dog', 'food', 'stupid']
    ]

    # 1 means humiliation words, 0 means normal words
    classVec = [0, 1, 0, 1, 0, 1]

    return postingList, classVec


# create a vocabulary list
def createVocabList(dataSet):
    vocabSet = set([])
    for document in dataSet:
        vocabSet = vocabSet | set(document)  # union the two set
    return list(vocabSet)

# convert words into vector (exist or not)
def setOfWords2Vec(vocabList, inputSet):
    returnVec = [0] * len(vocabList)
    for word in inputSet:
        if word in vocabList:
            returnVec[vocabList.index(word)] = 1
        else:
            print("the word: %s is not in my vocabulary!" % word)
    return returnVec

# convert words to vector (count)
def bagOfWords2VecMN(vocabList, inputSet):
    returnVec = [0]*len(vocabList)
    for word in inputSet:
        if word in vocabList:
            returnVec[vocabList.index(word)] += 1
    return returnVec

# train the model
# get the probabilities of every vector
def trainNB0(trainMatrix, trainCategory):
    numTrainDocs = len(trainMatrix)
    numWords = len(trainMatrix[0])
    pAbusive = sum(trainCategory) / float(numTrainDocs)

    p0Num = ones(numWords); p1Num = ones(numWords)
    p0Denom = 2.0; p1Denom = 2.0
    for i in range(numTrainDocs):
        if trainCategory[i] == 1:
            # add vector
            p1Num += trainMatrix[i]
            p1Denom += sum(trainMatrix[i])
        else:
            p0Num += trainMatrix[i]
            p0Denom += sum(trainMatrix[i])

    p1Vect = p1Num/p1Denom # change to log()
    p0Vect = p0Num/p0Denom # change to log()
    return p0Vect, p1Vect, pAbusive

def classifyNB(vec2Classify, p0Vec, p1Vec, pClass1):
    p1 = sum(vec2Classify * p1Vec) + log(pClass1)
    p0 = sum(vec2Classify * p0Vec) + log(1.0 - pClass1)

    return p1 > p0

def testNB():
    listOPosts, listClasses = loadDataSet()
    myVocabList = createVocabList(listOPosts)
    trainMat = []
    for p in listOPosts:
        trainMat.append(setOfWords2Vec(myVocabList, p))
    p0V, p1V, pAb = trainNB0(array(trainMat), array(listClasses))

    testEntry = ['love', 'my', 'dalmation']
    thisDoc = array(setOfWords2Vec(myVocabList, testEntry))
    print('%s classified as: %s' % (testEntry, classifyNB(thisDoc, p0V, p1V, pAb)))

    testEntry = ['stupid', 'garbage']
    thisDoc = array(setOfWords2Vec(myVocabList, testEntry))
    print('%s classified as: %s' %(testEntry, classifyNB(thisDoc, p0V, p1V, pAb)))

def textParse(bigString):
    import re
    listOfTokens = re.split(r'\W*', bigString)
    return [tok.lower() for tok in listOfTokens if len(tok) >2]

def spamTest():
    docList = []; classList=[]; fullText = []
    for i in range(1, 26):
        wordList = textParse(open('email/spam/%d.txt' % i, encoding='ISO-8859-1').read())
        print("%d: %s" % (i, wordList))
        docList.append(wordList)
        fullText.extend(wordList)
        classList.append(1)

        wordList = textParse(open('email/ham/%d.txt' % i, encoding='ISO-8859-1').read())
        docList.append(wordList)
        fullText.extend(wordList)
        classList.append(0)

    vocabList = createVocabList(docList)
    trainingSet = list(range(50)); testSet =[]

    # random select training set
    for i in range(10):
        randIdx = int(random.uniform(0, len(trainingSet)))
        testSet.append(trainingSet[randIdx])
        del(trainingSet[randIdx])
    trainMat = []; trainClasses = []
    for docIdx in trainingSet:
        trainMat.append(setOfWords2Vec(vocabList, docList[docIdx]))
        trainClasses.append(classList[docIdx])
    p0V, p1V, pSpam = trainNB0(array(trainMat), array(trainClasses))
    
    errorCount = 0

    # classify the test set
    for docIdx in testSet:
        wordVector = setOfWords2Vec(vocabList, docList[docIdx])
        if classifyNB(array(wordVector), p0V, p1V, pSpam) != classList[docIdx]:
            errorCount += 1
    print('the error rate is: %.2f' % (float(errorCount)/len(testSet)))
