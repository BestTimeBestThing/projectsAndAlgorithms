# -*- coding:utf8 -*-
import requests
from bs4 import BeautifulSoup
import pandas as pd

url = 'http://localhost:8888/notebooks/crawl_and_parse.ipynb#解析Beautiful-Soup结构体'
# 用requests访问获取内容
r = requests.get(url)

# 用BeautifulSoup解析一下
soup = BeautifulSoup(r.text,'html.parser')
# print soup

first_name = []
last_name = []
age = []
preTestScore = []
postTestScore = []

table = soup.find(class_='dataframe')
print table