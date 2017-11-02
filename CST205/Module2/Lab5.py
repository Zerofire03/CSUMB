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