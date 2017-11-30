#Christopher Holmes
#Lab 14 Problem 2

try:
    fin = open('News.html')
except:
    print("Something went wrong opening the file.")
    
lines = fin.readlines()
parsedLines = []
for line in lines:
    if "Story" in line or "Press Release" in line:
       parsedLines.append(line)
       
headlines = []
for line in parsedLines:
    startIndex = line.rfind("<h2>")+4
    endIndex = line.find("</h2>")
    headline = line[startIndex:endIndex]
    if "<div" not in headline and "</a>" not in headline and headline not in headlines:        
        headlines.append(headline)

print "*** CSU Breaking News! ***"    
for headline in headlines:
    print headline







