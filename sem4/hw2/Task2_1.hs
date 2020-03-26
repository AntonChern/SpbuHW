module Task2_1 where

import Prelude

reverse' :: [a] -> [a]
reverse' [] = []
reverse' x = last x : reverse' (init x)