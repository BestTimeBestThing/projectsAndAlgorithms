clear;
clc;

wine = dlmread('wine.data',',');
wine_labels = wine(:,1); % 分离数据中的labels
wine_features = wine(:,2:end); % 分离数据中的features

% Label为“1”的序号是：1~59
% Label为“2”的序号是：60~130
% Label为“3”的序号是：131~178

% 将第一类的1-30,第二类的60-95,第三类的131-153做为训练集
train_labels = [wine_labels(1:30,:);wine_labels(60:95,:);wine_labels(131:153,:)];
train_features = [wine_features(1:30,:);wine_features(60:95,:);wine_features(131:153,:)];
% 将第一类的31-59,第二类的96-130,第三类的154-178做为测试集
test_labels = [wine_labels(31:59,:);wine_labels(96:130,:);wine_labels(154:178,:)];
test_features = [wine_features(31:59,:);wine_features(96:130,:);wine_features(154:178,:)];



