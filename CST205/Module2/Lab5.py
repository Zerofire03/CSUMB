from array import array

def getPic():
  return makePicture(pickAFile())

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
  show(target)
  return target
  
def shrink(picture, wRatio, hRatio):
  width = getWidth(picture)
  height = getHeight(picture)
  newPic = makeEmptyPicture(width/wRatio, height/hRatio)
  for x, newX in zip(range (0, width, wRatio), range(0, width/wRatio)):
    for y, newY in zip(range (0, height, hRatio), range(0, height/hRatio)):
      pix = getPixel(picture, x, y)
      color = getColor(pix)
      setColor(getPixel(newPic, newX, newY), color)
  #show(newPic)
  return newPic

  
def makeCollage(picList = []):
  collage = makeEmptyPicture(2550, 3300)
  for x in picList:
    #print type(x)
    width = getWidth(x)
    height = getHeight(x)
    if(width > 1275 and height > 825):
      newWidth = width / 1275
      newHeight = height / 825
      temp = shrink(x, newWidth, newHeight)
    elif(width > 1275 and height < 825):
      newWidth = width / 825
      temp = shrink(x, newWidth, 1)
    elif(width < 1275 and height > 825):
      newHeight = height / 825
      temp = shrink(x, 1, newHeight)
    else:
      temp = x
    index = picList.index(x)
    if (index == 0):
      pyCopy(temp, collage, 0, 0)
    elif(index == 1):
      pyCopy(temp, collage, 0, 825)
    elif(index == 2):
      pyCopy(temp, collage, 0, 1650)
    elif(index == 3):
      pyCopy(temp, collage, 0, 2475)
    elif(index == 4):
      pyCopy(temp, collage, 1275, 0)
    elif(index == 5):
      pyCopy(temp, collage, 1275, 825)
    elif(index == 6):
      pyCopy(temp, collage, 1275, 1650)
    elif(index == 7):
      pyCopy(temp, collage, 1275, 2475)
  show(collage)
    

      
      
      