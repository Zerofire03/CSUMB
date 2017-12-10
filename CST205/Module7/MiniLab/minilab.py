#Christopher Holmes
#Mini Lab

import os

try:
    fin = open('eggs.txt')
except:
    print("Something went wrong opening the file.")
    
def returnCSS(count):
    if count > 0 and count <= 10:
        return 'style="color:#CCFF66; font-size:10px; font-weight:normal"'
    elif count > 10 and count <= 20:
        return 'style="color:#CCFF00; font-size:20px; font-weight:normal"'
    elif count > 20 and count <= 30:
        return 'style="color:#99FF00; font-size:30px; font-weight:normal"'
    elif count > 30 and count <= 40:
        return 'style="color:#66FF00; font-size:40px; font-weight:bold"'
    elif count > 40 and count <= 50:
        return 'style="color:#33FF00; font-size:50px; font-weight:bold"'
    elif count > 50 and count <= 60:
        return 'style="color:#00CC00; font-size:60px; font-weight:bold"'
    elif count > 60 and count <= 70:
        return 'style="color:#33CC33; font-size:70px; font-weight:bolder"'
    elif count > 70 and count <= 80:
        return 'style="color:#006600; font-size:80px; font-weight:bolder"'
    elif count > 80 and count <= 90:
        return 'style="color:#003300; font-size:90px; font-weight:bolder"'
    
def makePage(words, count):
    #Path is the path to where the python script is being run from, with the file name at the end of it.
    path = os.path.join(os.getcwd(), 'greeneggs.html')
    #Open the file with write permission
    file = open(path, "wt")
    #Begin the writting of the HTML file.
    file.write("""<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 
  Transition//EN" "http://www.w3.org/TR/html4/loose.dtd">
  
  <html>
  <head><title>I made this page with Python!</title>
  <style>
  body {
      background-color: #CCCCCC
      }
  </style>
  </head>
  <body>
  <h1>Chris Holmes Green Eggs!!!</h1>""")
    #Inject our specific headlines that we have that are passed in from the list
    for i, word in enumerate(words):
        css = returnCSS(count[i])
        string = "<p %s>%s: %s</p>"%(css, word, count[i])
        file.write(string)
    file.write("""</body>
  </html>""")
    file.close() 
    
numWords = 0
individualWords = []
countWords = []
for line in fin:
    words = line.split()
    numWords += len(words)
    for word in words:
        if word.lower() not in individualWords:
            individualWords.append(word.lower())
            countWords.append(1)
        else:
            index = individualWords.index(word.lower())
            countWords[index] += 1

mostUsed = 0
mostUsedIndex = 0
for i, individualWord in enumerate(individualWords):
    if countWords[i] > mostUsed:
        mostUsed = countWords[i]
        mostUsedIndex = i

makePage(individualWords, countWords)        
