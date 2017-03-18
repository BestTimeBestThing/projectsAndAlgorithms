# coding:utf8

import re
 # DMDR
file_object = open('DMDR.txt')
try:
    str = file_object.read( )
finally:
    file_object.close( )
result = re.findall("mu:0.010000---lambda:(.*)---accuracy:(.*)\n",str)
# mu:0.001000---lambda:0.001000---accuracy:76.610169
f = open("DMDR2.txt","w")
for line in result:
# print result
    f.write("%s,%s\n" % (line[0],line[1]))
f.close()

# =====================
 # SDMDR
file_object = open('SDMDR.txt')
try:
    str = file_object.read( )
finally:
    file_object.close( )
result = re.findall("mu:0.010000---lambda:(.*)---accuracy:(.*)\n",str)
# mu:0.100000---lambda:0.001000---accuracy:77.288136
f = open("SDMDR2.txt","w")
# print result
for line in result:
    f.write("%s,%s\n" % (line[0],line[1]))
f.close()

# =====================
 # TCA
file_object = open('TCA.txt')
try:
    str = file_object.read( )
finally:
    file_object.close( )
result = re.findall("mu:(.*)---accuracy:(.*)\n",str)
# mu:100.000000---accuracy:82.033898
f = open("TCA2.txt","w")
# print result
for line in result:
    f.write("%s,%s\n" % (line[0],line[1]))
f.close()

# =====================
 # SSTCA
file_object = open('SSTCA.txt')
try:
    str = file_object.read( )
finally:
    file_object.close( )
result = re.findall("mu:1.000000---lambda:(.*)---accuracy:(.*)\n",str)
# mu:1000.000000---lambda:0.001000---accuracy:75.593220
f = open("SSTCA2.txt","w")
# print result
for line in result:
    f.write("%s,%s\n" % (line[0],line[1]))
f.close()

# =====================
 # IGLDA
file_object = open('IGLDA.txt')
try:
    str = file_object.read( )
finally:
    file_object.close( )
result = re.findall("mu:10.000000---lambda:(.*)---accuracy:(.*)\n",str)
# mu:0.001000---lambda:0.200000---accuracy:82.033898
f = open("IGLDA2.txt","w")
# print result
for line in result:
    f.write("%s,%s\n" % (line[0],line[1]))
f.close()
