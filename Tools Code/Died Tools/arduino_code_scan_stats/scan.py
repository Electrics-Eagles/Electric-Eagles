#!/usr/bin/env python3
  
def count_substr(text, substrs):
    text = text.casefold()
    return sum(s in text for s in set(map(str.casefold, substrs)))
  

# -> 3
