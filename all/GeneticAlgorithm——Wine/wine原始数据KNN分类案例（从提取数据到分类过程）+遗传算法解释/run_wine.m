clear; 
clc;

load('wine.mat');
% ���õ�������
% wine_labels, 
% test_features, 
% test_labels,
% train_features, 
% train_labels, 
% wine, 
% wine_features

% function [predictY,accuracy] = myKNN(k,trainX,trainY,testX,testY)
    % TrainX��TestX���д���һ�����ݵ�
    % TrainY,TestY��������
    
% ����ʹ��1NN������Ԥ��
[predictY,accuracy] = myKNN(1,train_features,train_labels,test_features,test_labels);
fprintf('Ԥ�⾫ȷ��Ϊ��%f\n', accuracy);
