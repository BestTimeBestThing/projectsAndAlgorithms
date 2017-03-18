function [predictY,accuracy] = myKNN(k,trainX,trainY,testX,testY)
    % TrainX��TestX���д���һ�����ݵ�
    % TrainY,TestY��������

%     D = pdist2(trainX,testX,'jaccard');%����ƽ������correlation cosine
%     seuclidean euclidean
    D = pdist2(trainX,testX,'euclidean');%����ƽ������    
    [~,Index] = sort(D);    
    [row_num, col_num] = size(D);
    r = 0:row_num:row_num*(col_num-1);
    Index_matrix = repmat(r,row_num,1) + Index; 
    
    TrainY = repmat(trainY,1,col_num);    
    Neighbours = reshape(TrainY(Index_matrix(1:k,:)),[k,col_num]);
    [vec, ~] = mode(Neighbours,1);%��ÿһ�е�����,�õ���vec����������������~��������Ӧ��Ƶ������������~��ʾʡ��
    predictY = vec';
    accuracy = sum(testY == predictY)/col_num;    
end