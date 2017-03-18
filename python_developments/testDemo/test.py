# coding:utf-8
from __future__ import division
import string

path = 'D:\pycharm_workspace\demo1\Walden.txt'
with open(path,'r') as text:
    words = [raw_word.strip(string.punctuation).lower() for raw_word in text.read().split()]
    words_index = set(words)
    counts_dict = {index:words.count(index) for index in words_index}
    for word in sorted(counts_dict,key=lambda x: counts_dict[x],reverse=True):
        print('{} -- {} times'.format(word,counts_dict[word]))

# with open(path,'r') as text:
#     words = [raw_word.strip(string.punctuation).lower() for raw_word in text.read().split()]
#     words_index = set(words)
#     counts_dict = {index:words.count(index) for index in words_index}
#     for word in sorted(counts_dict.iteritems(),key=lambda x: x[1],reverse=True):
#         print('{} -- {} times'.format(word[0],word[1]))




