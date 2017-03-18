function [predictY,accuracy] = myKNN(k,trainX,trainY,testX,testY)
    % TrainX，TestX的行代表一个数据点
    % TrainY,TestY是列向量

%     D = pdist2(trainX,testX,'jaccard');%距离平方矩阵correlation cosine
%     seuclidean euclidean
    D = pdist2(trainX,testX,'euclidean');%距离平方矩阵    
    [~,Index] = sort(D);    
    [row_num, col_num] = size(D);
    r = 0:row_num:row_num*(col_num-1);
    Index_matrix = repmat(r,row_num,1) + Index; 
    
    TrainY = repmat(trainY,1,col_num);    
    Neighbours = reshape(TrainY(Index_matrix(1:k,:)),[k,col_num]);
    [vec, ~] = mode(Neighbours,1);%求每一列的众数,得到的vec是众数的行向量，~是众数对应的频数的行向量，~表示省略
    predictY = vec';
    accuracy = sum(testY == predictY)/col_num;    
end