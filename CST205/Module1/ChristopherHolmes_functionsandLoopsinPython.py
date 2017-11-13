def getPic():
  return makePicture(pickAFile())
  
def noBlue():
  pic = getPic()
  pixels = getPixels(pic)
  for p in pixels:
    b = getBlue(p)
    setBlue(p, 0)
  repaint(pic)
  
def lessRed(percent):
  pic = getPic()
  pixels = getPixels(pic)
  for p in pixels:
    r = getRed(p)
    setRed(p, r * (percent/100))
  repaint(pic)

def halfRed():
  lessRed(50)
  
def moreRed(percent):
  pic = getPic()
  pixels = getPixels(pic)
  for p in pixels:
    r = getRed(p)
    setRed(p, r * (1 + (percent/100)))
  repaint(pic)
  
def roseColoredGlasses():
  pic = getPic()
  pixels = getPixels(pic)
  for p in pixels:
    r = getRed(p)
    setRed(p, r * 1.25)
    b = getBlue(p)
    setBlue(p, b * 0.5)
    g = getGreen(p)
    setGreen(p, g * 0.5)
  file = r"/Users/chris/Documents/GitHub/CSUMB/CST205/Module3/portfolio/roseGlasses.jpg"
  writePictureTo(pic, file)
  
def lightenUp():
  pic = getPic()
  pixels = getPixels(pic)
  for p in pixels:
    newColor = makeLighter(getColor(p))
    setColor(p, newColor)
  repaint(pic)
  
def makeNegative():
  pic = getPic()
  pixels = getPixels(pic)
  for p in pixels:
    r = getRed(p)
    g = getGreen(p)
    b = getBlue(p)
    negR = 255 - r
    negG = 255 - g
    negB = 255 - b
    setRed(p, negR)
    setGreen(p, negG)
    setBlue(p, negB)
  file = r"/Users/chris/Documents/GitHub/CSUMB/CST205/Module3/portfolio/makeNegative.jpg"
  writePictureTo(pic, file)
  repaint(pic)

def BnW():
  pic = getPic()
  pixels = getPixels(pic)
  for p in pixels:
    r = getRed(p)
    g = getGreen(p)
    b = getBlue(p)
    avg = (r + g + b)/3
    setRed(p, avg)
    setGreen(p, avg)
    setBlue(p, avg)
  repaint(pic)
  
def betterBnW():
  pic = getPic()
  pixels = getPixels(pic)
  for p in pixels:
    r = getRed(p)
    g = getGreen(p)
    b = getBlue(p)
    avg = r*0.299 + g*0.587 + b*0.114
    setRed(p, avg)
    setGreen(p, avg)
    setBlue(p, avg)
  file = r"/Users/chris/Documents/GitHub/CSUMB/CST205/Module3/portfolio/betterBnW.jpg"
  writePictureTo(pic, file)
  repaint(pic)