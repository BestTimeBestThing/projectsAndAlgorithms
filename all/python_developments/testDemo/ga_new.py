# coding:utf8
from __future__ import division
import numpy as np
from numpy.random import randint
from numpy.random import rand
from methods import *

# 初始化种群
# population = initial_population(num, len_gene, goal_dim)
# 种群适应度函数
# population_fit = population_fitness(train_features, train_labels, test_features, test_labels, population)
# 种群选择,单点交叉,产生新种群
# population_new = cross_over(train_features, train_labels, test_features, test_labels, population,pc)
# 变异，产生新种群
# population = mutation(population, pm)

if __name__ == '__main__':
    # 载入数据
    data = np.loadtxt('wine.data', delimiter=',')
    # 设置训练集合测试集的索引
    train_indexs = range(0, 30) + range(59, 95) + range(130, 153)
    test_indexs = range(30, 59) + range(95, 130) + range(153, 178)

    train_features = data[train_indexs, 1:]
    train_labels = data[train_indexs, 0]
    test_features = data[test_indexs, 1:]
    test_labels = data[test_indexs, 0]

    # 设置参数
    num_population = 20
    len_gene = 13
    goal_dim = 5
    T = 80

    # 初始化种群
    population = initial_population(num_population, len_gene, goal_dim)
    # print population
    # print '---------------------------------------'
    for i in range(T):
        # 种群选择,单点交叉,产生新种群
        pc = 0.6  # 交叉概率
        population_new = cross_over(train_features, train_labels, test_features, test_labels, population, pc)
        # print population_new
        # print '---------------------------------------'

        # 变异，产生新种群
        pm = 0.1  # 变异概率
        population = mutation(population_new, pm)
        # print population
        # print '---------------------------------------'
    accuracy = np.zeros(len(population))
    for i in range(len(population)):
        index = population[i]
        train_feature_exp = train_features[:, index == 1]
        test_features_exp = test_features[:, index == 1]
        # predictY, accuracy = myKNN(k, trainX, trainY, testX, testY)
        predictY, accuracy[i] = myKNN(1, train_feature_exp, train_labels, test_features_exp, test_labels)
    print accuracy
    print
    print population
