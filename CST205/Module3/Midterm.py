def pyCopyBlack(source, target, targetX, targetY):
  sWidth = getWidth(source)
  sHeight = getHeight(source)
  tWidth = getWidth(target)
  tHeight = getHeight(target)
  for x in range(0, sWidth):
    for y in range(0, sHeight):
      pix = getPixel(source, x, y)
      color = getColor(pix)
      if color.getRed() == 0 and color.getGreen() == 0 and color.getBlue() == 0:
        setColor(getPixel(target, (targetX + x), (targetY + y)), color)
  return target
  
def chromakey():
  pic =  makePicture(pickAFile())
  picBG =  makePicture(pickAFile())
  for x in range(0, getWidth(pic)):
    for y in range(0, getHeight(pic)):
      px = getPixel(pic, x, y)
      pxBG = getPixel(picBG, x, y)
      color = getColor(px)
      colorBG = getColor(pxBG)
      if distance(color, white) < 200.0:
        setColor(px, colorBG) 
  show(pic)
  file = r"/Users/chris/Documents/GitHub/CSUMB/CST205/Module3/portfolio/mergedColorArtify.jpg"
  writePictureTo(pic, file)
  
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
  show(pic)
