# -*- coding: utf-8 -*-

from bs4 import BeautifulSoup
import requests
import json

def get_attractions(url, data=None):
    web_data = requests.get(url)
    # web_data.text 将web_data转换为文本格式
    soup = BeautifulSoup(web_data.text, 'lxml')
    # print soup
    titles = soup.select('div.property_title > a[target="_blank"]')
    imgs = soup.select('img[width="160"]')
    categories = soup.select('div.p13n_reasoning_v2')
    grades = soup.select('div > span.rate > img.sprite-ratings')
    if data == None:
        for title, img, category, grade in zip(titles, imgs, categories, grades):
            tmp = list(category.stripped_strings)
            data = {
                'title': title.get_text(),
                'img': img.get('src'),
                'category': list(category.stripped_strings),
                'grade': grade.get('alt')
            }
            print json.dumps(data, encoding='utf8', ensure_ascii=False)  # 注意python2 里面的中文编码问题


def get_favors(url, headers = None, data=None):
    web_data = requests.get(url, headers=headers)
    soup = BeautifulSoup(web_data.text, 'lxml')
    categories = soup.select('div.title-content > span > span.mine-title-text')
    for category in categories:
        print category.get_text().encode('utf-8')

def get_images(url, data=None, headers = None):
    web_data = requests.get(url, headers = headers)
    soup = BeautifulSoup(web_data.text,'lxml')
    images = soup.select('div > div > img')
    for i in images:
        print i.get('src')

# 测试1
# url = 'http://www.tripadvisor.cn/Attractions-g60763-Activities-New_York_City_New_York.html'
# get_attractions(url)

# 测试2
# url = 'https://www.baidu.com/'
# headers = {
#     'User-Agent':'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2979.2 Safari/537.36',
#     'Cookie':'BAIDUID=7C2E97D71FC2F9191011393282736658:FG=1; BIDUPSID=7C2E97D71FC2F9191011393282736658; PSTM=1484460202; MCITY=-338%3A; sug=3; sugstore=0; ORIGIN=0; bdime=0; BDUSS=NJU0F6aUhHVk54flhRWEQyc1pHcmUwdlBubno0VkkwYzUyQ2VzQXlZbVE1dFJZSVFBQUFBJCQAAAAAAAAAAAEAAABkVG-kdHhscngwcDIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAJBZrViQWa1Ybn; ispeed_lsm=2; BDRCVFR[dG2JNJb_ajR]=mk3SLVN4HKm; userFrom=www.baidu.com; BDRCVFR[feWj1Vr5u3D]=mk3SLVN4HKm; BD_CK_SAM=1; PSINO=7; H_PS_645EC=576cEzE8JnqhknSnghFzcVQS4VGc9x%2BKDEtn2WOqTpuUsEgf%2Bl1qB4BNSNoreWp4nbpB; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; BD_HOME=1; H_PS_PSSID=22086_1440_21116_17001_22035_20927; BD_UPN=12314353'
# }
# get_favors(url, headers = headers)

# 测试3
headers = {
    'User-Agent':'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2979.2 Mobile Safari/537.36'
}
url = 'http://www.tripadvisor.cn/Attractions-g60763-Activities-New_York_City_New_York.html'
get_images(url, headers = headers)