# --*-- coding: utf-8 --*--

import matplotlib.pyplot as plt

# define text and arrow
decisionNode = dict(boxstyle="sawtooth", fc="0.8")
leafNode = dict(boxstyle="round4", fc="0.8")
arrow_args = dict(arrowstyle="<-")

# plot with annotations
def plotNode(nodeTxt, centerPt, parentPt, nodeType):
    createPlot.ax1.annotate(nodeTxt, 
        xy=parentPt, 
        xycoords='axes fraction', 
        xytext=centerPt, 
        textcoords='axes fraction', 
        va='center',
        ha='center',
        bbox=nodeType,
        arrowprops=arrow_args)


def createSamplePlot():
    fig = plt.figure(1, facecolor='white')
    fig.clf()
    createPlot.ax1 = plt.subplot(111, frameon=False)
    plotNode('decision node', (0.5, 0.1), (0.1, 0.5), decisionNode)
    plotNode('leaf node', (0.8, 0.1), (0.3, 0.8), leafNode)
    plt.show()

# calculate the total number of leafs
def getNumLeafs(myTree):
    numLeafs = 0
    firstStr = list(myTree.keys())[0]
    secondDict = myTree[firstStr]
    for key in secondDict.keys():
        if type(secondDict[key]).__name__ == 'dict':
            numLeafs += getNumLeafs(secondDict[key])
        else:
            numLeafs += 1
    return numLeafs

def getTreeDepth(myTree):
    firstStr = list(myTree.keys())[0]
    secondDict = myTree[firstStr]
    maxDepth = 0
    for key in secondDict.keys():
        if type(secondDict[key]).__name__ == 'dict':
            theDepth = 1 + getTreeDepth(secondDict[key])
        else:
            theDepth = 1
        if theDepth > maxDepth: maxDepth = theDepth
    return maxDepth

# predefined trees
def retrieveTree(i):
    listOfTrees = [{
        'no surfacing': {
            0: 'no',
            1: {
                'flippers': {
                    0: 'no', 
                    1: 'yes'
                }
            }
        }
    }, 
    {
        'no surfacing': {
            0: 'no',
            1: {
                'flippers': {
                    0: {
                        'head': {
                            0: 'no', 
                            1: 'yes'
                        }
                    }, 
                    1: 'no'
                }
            }
        }
    }]
    return listOfTrees[i]

def plotMidText(cntrPt, parentPt, txtstring):
    xMid = (parentPt[0] - cntrPt[0]) / 2.0 + cntrPt[0]
    yMid = (parentPt[1] - cntrPt[1]) / 2.0 + cntrPt[1]
    createPlot.ax1.text(xMid, yMid, txtstring, va="center", ha="center", rotation=30)

def plotTree(myTree, parentPt, nodeTxt):
    # calculate width and height
    numLeafs = getNumLeafs(myTree)
    depth = getTreeDepth(myTree)
    firstStr = list(myTree.keys())[0]
    xAxis = plotTree.xOff + (1.0 + float(numLeafs)) / 2.0 / plotTree.totalW
    cntrPt = (xAxis, plotTree.yOff)
    print("cntrPt:%f" % xAxis)
    # mark the properties of sub node
    plotMidText(cntrPt, parentPt, nodeTxt)
    plotNode(firstStr, cntrPt, parentPt, decisionNode)
    print("first str=%s" % firstStr)
    secondDict = myTree[firstStr]

    # reduce y error
    plotTree.yOff = plotTree.yOff - 1.0 / plotTree.totalD
    for key in secondDict.keys():
        if type(secondDict[key]).__name__ == 'dict':
            plotTree(secondDict[key], cntrPt, str(key))
            # print("do nothing")
        else:
            plotTree.xOff = plotTree.xOff + 1.0/plotTree.totalW
            plotNode(secondDict[key], (plotTree.xOff, plotTree.yOff), cntrPt, leafNode)
            plotMidText((plotTree.xOff, plotTree.yOff), cntrPt, str(key))
    plotTree.yOff = plotTree.yOff + 1.0/plotTree.totalD

def createPlot(inTree):
    fig = plt.figure(1, facecolor='white')
    fig.clf()
    axprops = dict(xticks=[], yticks=[])
    createPlot.ax1 = plt.subplot(111, frameon=False, **axprops)
    plotTree.totalW = float(getNumLeafs(inTree))
    plotTree.totalD = float(getTreeDepth(inTree))
    plotTree.xOff = -0.5/plotTree.totalW
    plotTree.yOff = 1.0
    plotTree(inTree, (0.5, 1.0), '')
    plt.show()
