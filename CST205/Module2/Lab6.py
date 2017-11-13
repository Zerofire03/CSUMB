#
#
#
# warm up
def remove(x1,x2,y1,y2):
  for x in range(x1,x2):
    for y in range(y1,y2):
       px = getPixel(pic,x,y)
       color = getColor(px)
       if distance(color, red) < 200.0 :
         setColor(px,makeColor((getGreen(px)+getBlue(px))/2))
  file = r"/Users/chris/Documents/GitHub/CSUMB/CST205/Module3/portfolio/removeRedEye.jpg"
  writePictureTo(pic, file)
       
#remove(58,78,81,100)#Hard Code cordinates for Eye 1
#remove(208,229,81,100)#Hard Code cordinates for Eye 2    


# Problem 1 sepia

def betterBnW(pic):
  #pic = get_pic()
  pixels = getPixels(pic)
  
  for p in pixels:
    newColor = makeColor((getRed(p)*0.299 + getGreen(p)*0.587 + getBlue(p)*0.144))
    setColor(p, newColor)
    
  #repaint(pic)
  return pic
  
def sepia():
  pic =  makePicture(pickAFile())
  
  pic = betterBnW(pic)
  
  for px in getPixels(pic):
    R = getRed(px) 
    B = getBlue(px)
    if R < 63:
      setRed(px,R * 1.1)
      setBlue(px,B*0.9)
    elif R >= 63 and R <192:
      setRed(px,R*1.15)
      setBlue(px,B*0.85)
    else:
      if R*1.08 > 255:
        setRed(px,255)
      else:
        setRed(px,R*1.08)
      setBlue(px,B*0.93)
      
  repaint(pic)
  show(pic)
  
#Problem 2
def artify():
  # Current color range	New value for color
  #color < 64	31
  #63 < color < 128	95
  #127 < color < 192	159
  #191 < color < 256	223
  pic =  makePicture(pickAFile())
  
  for px in getPixels(pic):
    R = getRed(px) 
    B = getBlue(px)
    G = getGreen(px)
    
    # red
    if R < 64:
      setRed(px, 31)
    elif R >= 64 and R < 128:
      setRed(px, 95)
    elif R >= 128 and R < 192:
      setRed(px, 159)
    elif R >= 192 and R < 256:
      setRed(px, 223)

    # blue
    if B < 64:
      setBlue(px, 31)
    elif B >= 64 and B < 128:
      setBlue(px, 95)
    elif B >= 128 and B < 192:
      setBlue(px, 159)
    elif B >= 192 and B < 256:
      setBlue(px, 223)
    
    # green
    if G < 64:
      setGreen(px, 31)
    elif G >= 64 and G < 128:
      setGreen(px, 95)
    elif G >= 128 and G < 192:
      setGreen(px, 159)
    elif G >= 192 and G < 256:
      setGreen(px, 223)
  #file = r"/Users/chris/Documents/GitHub/CSUMB/CST205/Module3/portfolio/artify.jpg"
  #writePictureTo(pic, file)
  show(pic)


#Problem 3
def chromakey():
  pic =  makePicture(pickAFile())
  picBG =  makePicture(pickAFile())
  for x in range(0, getWidth(pic)):
    for y in range(0, getHeight(pic)):
      px = getPixel(pic, x, y)
      pxBG = getPixel(picBG, x, y)
      color = getColor(px)
      colorBG = getColor(pxBG)
      if distance(color, green) < 200.0:
        setColor(px, colorBG) 
  file = r"/Users/chris/Documents/GitHub/CSUMB/CST205/Module3/portfolio/chromaKey.jpg"
  writePictureTo(pic, file)
  show(pic)