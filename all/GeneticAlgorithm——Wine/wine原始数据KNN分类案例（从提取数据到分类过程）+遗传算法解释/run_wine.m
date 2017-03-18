clear; 
clc;

load('wine.mat');
% 将得到变量：
% wine_labels, 
% test_features, 
% test_labels,
% train_features, 
% train_labels, 
% wine, 
% wine_features

% function [predictY,accuracy] = myKNN(k,trainX,trainY,testX,testY)
    % TrainX，TestX的行代表一个数据点
    % TrainY,TestY是列向量
    
% 下面使用1NN分类器预测
[predictY,accuracy] = myKNN(1,train_features,train_labels,test_features,test_labels);
fprintf('预测精确度为：%f\n', accuracy);
