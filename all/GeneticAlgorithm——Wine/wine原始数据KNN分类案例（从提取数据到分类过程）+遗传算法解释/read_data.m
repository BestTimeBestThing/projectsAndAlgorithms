clear;
clc;

wine = dlmread('wine.data',',');
wine_labels = wine(:,1); % ���������е�labels
wine_features = wine(:,2:end); % ���������е�features

% LabelΪ��1��������ǣ�1~59
% LabelΪ��2��������ǣ�60~130
% LabelΪ��3��������ǣ�131~178

% ����һ���1-30,�ڶ����60-95,�������131-153��Ϊѵ����
train_labels = [wine_labels(1:30,:);wine_labels(60:95,:);wine_labels(131:153,:)];
train_features = [wine_features(1:30,:);wine_features(60:95,:);wine_features(131:153,:)];
% ����һ���31-59,�ڶ����96-130,�������154-178��Ϊ���Լ�
test_labels = [wine_labels(31:59,:);wine_labels(96:130,:);wine_labels(154:178,:)];
test_features = [wine_features(31:59,:);wine_features(96:130,:);wine_features(154:178,:)];



