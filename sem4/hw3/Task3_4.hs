module Task3_4 where

import Prelude

corrBrackets :: [Char] -> [Char]
corrBrackets' :: [Char] -> [Int] -> [Char]
corrBrackets str = corrBrackets' str []
corrBrackets' [] [] = "Correct"
corrBrackets' [] _ = "Incorrect"
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