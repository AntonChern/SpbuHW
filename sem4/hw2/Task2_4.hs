module Task2_4 where

import Prelude

firstIndex :: Integer -> [Integer] -> Integer
firstIndex a (x:xs) = if a == x then 1 else 1 + firstIndex a xs