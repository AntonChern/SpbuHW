module Task3_4 where

import Prelude

corrBrackets :: [Char] -> Bool
corrBrackets' :: [Char] -> [Int] -> Bool
corrBrackets str = corrBrackets' str []
corrBrackets' [] [] = True
corrBrackets' [] _ = False
corrBrackets' (curr:str) result = corrBrackets' str (if elem curr "()[]{}"
                                                     then if  prev > 0 && ibracket + prev == 0
                                                          then tail result
                                                          else (ibracket:result)
                                                     else result)
                                  where prev = if result == [] then 0 else head result
                                        ibracket = case curr of
                                                       '(' -> 1
                                                       ')' -> -1
                                                       '[' -> 2
                                                       ']' -> -2
                                                       '{' -> 3
                                                       '}' -> -3

test1 :: Bool
test1 = corrBrackets "({}[])" == True

test2 :: Bool
test2 = corrBrackets "]({)(})[" == False