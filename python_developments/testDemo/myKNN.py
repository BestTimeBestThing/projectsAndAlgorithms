# coding:utf-8
from __future__ import division
import numpy as np
import operator

# KNN分类器
def myKNN(k, trainX, trainY, testX, testY):
    # predictY, accuracy = myKNN(k, trainX, trainY, testX, testY)
    # trainX，testX的行代表一个数据点
    # predictY,trainY, testY是向量
    # accuracy标量
    """
    下面的求距离过程就是按照欧氏距离的公式计算的。
    即 根号(x^2+y^2)
    """
    # tile属于numpy模块下边的函数
    # tile（A, reps）返回一个shape=reps的矩阵，矩阵的每个元素是A
    # 比如 A=[0,1,2] 那么，tile(A, 2)= [0, 1, 2, 0, 1, 2]
    # tile(A,(2,2)) = [[0, 1, 2, 0, 1, 2],
    #                  [0, 1, 2, 0, 1, 2]]
    # tile(A,(2,1,2)) = [[[0, 1, 2, 0, 1, 2]],
    #                    [[0, 1, 2, 0, 1, 2]]]
    trainSize = len(trainX)
    testSize = len(testX)
    predictY = np.zeros(testSize)
    for i in range(testSize):
        A_matrix = np.tile(testX[i, :], (trainSize, 1))
        diffMat = A_matrix - trainX
        sqDiffMat = diffMat ** 2
        sqDistance = sqDiffMat.sum(axis=1)
        distance = sqDistance ** 0.5
        sortedDistIndicies = distance.argsort()

        # 存放最终的分类结果及相应的结果投票数
        classCount = {}
        for j in range(k):
            # index = sortedDistIndicies[j]是第j个最相近的样本下标
            # voteIlabel = labels[index]是样本index对应的分类结果
            voteIlabel = trainY[sortedDistIndicies[j]]
            # classCount.get(voteIlabel, 0)返回voteIlabel的值，如果不存在，则返回0
            # 然后将票数增1
            classCount[voteIlabel] = classCount.get(voteIlabel, 0) + 1
            # 把分类结果进行排序，然后返回得票数最多的分类结果
        sortedClassCount = sorted(classCount.iteritems(), key=operator.itemgetter(1), reverse=True)
        predictY[i] = sortedClassCount[0][0]
    accuracy = sum(predictY == testY) / testSize
    return predictY, accuracy


if __name__ == '__main__':
    trainX = np.array([[1.0, 1.1],
                       [1.0, 1.0],
                       [0., 0.],
                       [0., 0.1]])
    trainY = [1, 1, 2, 2]
    testX = np.array([[0.1, 0.1], [0.3, -0.1]])
    testY = [2, 1]
    k = 2
    predictY, accuracy = myKNN(k, trainX, trainY, testX, testY)
    print predictY, accuracy
