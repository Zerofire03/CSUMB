#Christopher Holmes

import os
from urllib import *

def makePage():
    path = os.path.join(os.getcwd(), 'pythonWebPage.html')
    #print("File path: %s")%path
    file = open(path, "wt")
    file.write("""<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 
  Transition//EN" "http://www.w3.org/TR/html4/loose.dtd">
  
  <html>
  <head><title>I made this page with Python!</title>
  </head>
  <body>
  <h1>MY PYTHON PAGE!!!</h1>
  </body>
  </html>""")
    file.close()

#makePage()

html = urlopen("https://www.cnn.com").readline()
path = os.path.join(os.getcwd(), 'source.html')
file = open(path, "wt")
file.write(html)

fin = open('source.html')
lines = fin.readlines()
parsedLines = []
for line in lines:
    if "cd__headline-text" in line:
        print("Line: %s")%line
        parsedLines.append(line)
        
headlines = []
for line in parsedLines:
    startIndex = line.rfind("<span class=\"cd__headline-text\">")
    
    endIndex = line.find("</span>")
    headline = line[startIndex:endIndex]
    headlines.append(headline)
    
for headline in headlines:
    print headline