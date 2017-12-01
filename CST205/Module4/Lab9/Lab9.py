#Christopher Holmes
#Lab 9
#file = pickAFile()
#sound = makeSound(pickAFile())

def clip(source, start, end):
  smallClip = makeEmptySound(end-start, 11050)
  index = 0
  for x in range(start, end):
    sample = getSampleValueAt(source, x)
    setSampleValueAt(smallClip, index, sample)
    index = index + 1
  return smallClip
  
def copy(source, target, start):
  index = start
  for x in range(0, getLength(source)):
    sample = getSampleValueAt(source, x)
    setSampleValueAt(target, index, sample)
    index = index + 1
    
def soundCollage():
  sound1 = makeSound(pickAFile())
  sound2 = makeSound(pickAFile())
  sound3 = makeSound(pickAFile())
  sound4 = makeSound(pickAFile())
  sound5 = makeSound(pickAFile())
  
  sound1len = getLength(sound1)
  sound2len = getLength(sound2)
  sound3len = getLength(sound3)
  sound4len = getLength(sound4)
  sound5len = getLength(sound5)
  
  newSound = makeEmptySound(sound1len + sound2len + sound3len + sound4len + sound5len, 11050)
  index = 0
  
  for x in range(0, sound1len):
    sample = getSampleValueAt(sound1, x)
    setSampleValueAt(newSound, index, sample)
    index = index + 1
    
  for x in range(0, sound2len):
    sample = getSampleValueAt(sound2, x)
    setSampleValueAt(newSound, index, sample)
    index = index + 1
    
  for x in range(0, sound3len):
    sample = getSampleValueAt(sound3, x)
    setSampleValueAt(newSound, index, sample)
    index = index + 1
    
  for x in range(0, sound4len):
    sample = getSampleValueAt(sound4, x)
    setSampleValueAt(newSound, index, sample)
    index = index + 1
    
  for x in range(0, sound5len):
    sample = getSampleValueAt(sound5, x)
    setSampleValueAt(newSound, index, sample)
    index = index + 1
    
  return newSound
  
def increaseVolumeRange(sound):
   for sample in range(0, getLength(sound)):
      value = getSampleValueAt(sound, sample)
      setSampleValueAt(sound, sample, value * 2)
      
def reverse(sound):
  soundlen = getLength(sound)
  backwards = makeEmptySound(soundlen, 11050)
  index = soundlen-1
  for sample in range(0, soundlen):
    value = getSampleValueAt(sound, sample)
    setSampleValueAt(backwards, index, value)
    index = index -1
  return backwards
  