# coding:utf8
from __future__ import division
import numpy as np
from numpy.random import randint
from numpy.random import rand
from myKNN import myKNN

# 初始化种群
# population = initial_population(num, len_gene, goal_dim)
def initial_population(num, len_gene, goal_dim):
    # num表示种群大小，len_gen表示染色体长度,goal_dim表示目标维数
    population = np.zeros([num, len_gene])
    for i in range(0, num):
        for j in range(len_gene):
            if rand() < goal_dim / len_gene:
                population[i, j] = 1
            else:
                population[i, j] = 0
    return population

# 种群适应度函数
# population_fit = population_fitness(train_features, train_labels, test_features, test_labels, population)
def population_fitness(train_features, train_labels, test_features, test_labels, population):
    # 计算种群每个个体的自适应度值
    population_fit = np.zeros(len(population))
    for i in range(len(population)):
        index = population[i]
        train_feature_exp = train_features[:, index == 1]
        test_features_exp = test_features[:, index == 1]
        # predictY, accuracy = myKNN(k, trainX, trainY, testX, testY)
        predictY, accuracy = myKNN(1, train_feature_exp, train_labels, test_features_exp, test_labels)
        population_fit[i] = accuracy
    return population_fit

# 种群选择,单点交叉,产生新种群
# population_new = cross_over(train_features, train_labels, test_features, test_labels, population,pc)
def cross_over(train_features, train_labels, test_features, test_labels, population, pc):
    num_population, len_gene = population.shape
    # 计算种群各个个体的自适应度值
    population_fit = population_fitness(train_features, train_labels,
                                        test_features, test_labels, population)
    # 计算选择概率
    population_fit_probability = population_fit / np.sum(population_fit)
    population_fit_pro_accumulate = np.add.accumulate(population_fit_probability)

    # 初始化待选择的父染色体,初始值为全0矩阵
    population_parents = np.zeros(population.shape)

    # 选择父染色体
    number_array = rand(num_population)
    for i in range(num_population):
        number = number_array[i]
        for j in range(num_population):
            if j == 0:
                if number < population_fit_pro_accumulate[0]:
                    population_parents[j] = population[0]
                    break
            else:
                if population_fit_pro_accumulate[j - 1] <= number < population_fit_pro_accumulate[j]:
                    population_parents[j] = population[j]
                    break
    # 染色体交叉，交叉概率 pc = 0.6
    for i in range(0, num_population, 2):
        number = rand()  # 如果number < pc，两个染色体交叉
        if number < pc:
            # 首先随机选择交叉点
            number = randint(0, len_gene)
            tmp = population_parents[i, number:].copy()
            population_parents[i, number:] = population_parents[i + 1, number:]
            population_parents[i + 1, number:] = tmp
    return population_parents

# 变异，产生新种群
# population = mutation(population, pm)
def mutation(population, pm):
    # 变异概率 pm = 0.1
    num_population, len_gene = population.shape
    number_array = rand(num_population)
    for i in range(num_population):
        if number_array[i] < pm:
            number_gene = randint(0,len_gene)
            if population[i,number_gene] ==1:
                population[i, number_gene] = 0
            elif population[i, number_gene] == 0:
                population[i, number_gene] = 1
    return population