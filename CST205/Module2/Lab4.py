def getPic():
  return makePicture(pickAFile())

def vertMirror():
  file = pickAFile()
  pic = makePicture(file)
  width = getWidth(pic)
  height = getHeight(pic)
  
  for x in range(0, width/2):
    for y in range(0, height):
      pix = getPixel(pic, x, y)
      color = getColor(pix)
      setColor(getPixel(pic, (width - x - 1), y), color)
  repaint(pic)
  
def horTopMirror():
  file = pickAFile()
  pic = makePicture(file)
  width = getWidth(pic)
  height = getHeight(pic)
  
  for y in range(0, height/2):
    for x in range(0, width):
      pix = getPixel(pic, x, y)
      color = getColor(pix)
      setColor(getPixel(pic, x, (height - y - 1)), color)
  repaint(pic)

def horBotMirror():
  file = pickAFile()
  pic = makePicture(file)
  width = getWidth(pic)
  height = getHeight(pic)
  
  for y in range(height/2, height):
    for x in range(0, width):
      pix = getPixel(pic, x, y)
      color = getColor(pix)
      setColor(getPixel(pic, x, (height - y - 1)), color)
  file = r"/Users/chris/Documents/GitHub/CSUMB/CST205/Module3/portfolio/bottomToTopMirror.jpg"
  writePictureTo(pic, file)
  repaint(pic)
  
def quadMirror():
  file = pickAFile()
  pic = makePicture(file)
  width = getWidth(pic)
  height = getHeight(pic)
  
  for x in range(0, width/2):
    for y in range(0, height):
      pix = getPixel(pic, x, y)
      color = getColor(pix)
      setColor(getPixel(pic, (width - x - 1),  y), color)
  
  for y in range(0, height/2):
    for x in range(0, width):
      pix = getPixel(pic, x, y)
      color = getColor(pix)
      setColor(getPixel(pic, x, (height - y - 1)), color)
  repaint(pic)
  
def simplePic():
  mypic = makeEmptyPicture(100, 100)
  for x in range (0, getWidth(mypic)):
    for y in range (0, getHeight(mypic)):
      setColor(getPixel(mypic, x, y), blue)
  show(mypic)
  return mypic
  
def simpleCopy(picture):
  width = getWidth(picture)
  height = getHeight(picture)
  newPic = makeEmptyPicture(width, height)
  for x in range (0, width):
    for y in range (0, height):
      pix = getPixel(picture, x, y)
      color = getColor(pix)
      setColor(getPixel(newPic, x, y), color)
  show(newPic)
  return newPic
  
def rotatePic(picture):
  width = getWidth(picture)
  height = getHeight(picture)
  newPic = makeEmptyPicture(height, width)
  for x in range (0, width):
    for y in range (0, height):
      pix = getPixel(picture, x, y)
      color = getColor(pix)
      setColor(getPixel(newPic, y, x), color)
  show(newPic)
  return newPic
  
  
def shrink():
  file = pickAFile()
  pic = makePicture(file)
  width = getWidth(pic)
  height = getHeight(pic)
  newPic = makeEmptyPicture(width/2, height/2)
  for x, newX in zip(range (0, width, 2), range(0, width/2)):
    for y, newY in zip(range (0, height, 2), range(0, height/2)):
      pix = getPixel(pic, x, y)
      color = getColor(pix)
      setColor(getPixel(newPic, newX, newY), color)
  file = r"/Users/chris/Documents/GitHub/CSUMB/CST205/Module3/portfolio/shrinkPicture.jpg"
  writePictureTo(newPic, file)
  show(newPic)
  