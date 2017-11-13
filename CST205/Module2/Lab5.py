from array import array

def loadArray():
  picList = []
  for i in range(9):
    picList.append(makePicture(pickAFile()))
  return picList
  

def pyCopy(source, target, targetX, targetY):
  sWidth = getWidth(source)
  sHeight = getHeight(source)
  tWidth = getWidth(target)
  tHeight = getHeight(target)
  for x in range(0, sWidth):
    for y in range(0, sHeight):
      pix = getPixel(source, x, y)
      color = getColor(pix)
      setColor(getPixel(target, (targetX + x), (targetY + y)), color)
  return target
  
def vertMirror(pic):
  width = getWidth(pic)
  height = getHeight(pic)
  
  for x in range(0, width/2):
    for y in range(0, height):
      pix = getPixel(pic, x, y)
      color = getColor(pix)
      setColor(getPixel(pic, (width - x - 1), y), color)
  return pic
  
def horTopMirror(pic):
  width = getWidth(pic)
  height = getHeight(pic)
  
  for y in range(0, height/2):
    for x in range(0, width):
      pix = getPixel(pic, x, y)
      color = getColor(pix)
      setColor(getPixel(pic, x, (height - y - 1)), color)
  return pic

def horBotMirror(pic):
  width = getWidth(pic)
  height = getHeight(pic)
  
  for y in range(height/2, height):
    for x in range(0, width):
      pix = getPixel(pic, x, y)
      color = getColor(pix)
      setColor(getPixel(pic, x, (height - y - 1)), color)
  return pic
  
def quadMirror(pic):
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
  return pic 
  
  
def rotatePic(picture):
  width = getWidth(picture)
  height = getHeight(picture)
  newPic = makeEmptyPicture(height, width)
  for x in range (0, width):
    for y in range (0, height):
      pix = getPixel(picture, x, y)
      color = getColor(pix)
      setColor(getPixel(newPic, y, x), color)
  return newPic


  
def makeCollage(picList = []):
  collage = makeEmptyPicture(2550, 3300)
  for x in picList:
    index = picList.index(x)
    if (index == 0):
      pyCopy(vertMirror(x), collage, 0, 0)
    elif(index == 1):
      pyCopy(horTopMirror(x), collage, 0, 264)
    elif(index == 2):
      pyCopy(horBotMirror(x), collage, 0, 528)
    elif(index == 3):
      pyCopy(quadMirror(x), collage, 203, 0)
    elif(index == 4):
      pyCopy(rotatePic(x), collage, 203, 264)
    elif(index == 5):
      pyCopy(x, collage, 203, 528)
    elif(index == 6):
      pyCopy(x, collage, 406, 0)
    elif(index == 7):
      pyCopy(x, collage, 406, 264)
    elif(index == 8):
      pyCopy(x, collage, 406, 528)
  show(collage)
    

      
      
      