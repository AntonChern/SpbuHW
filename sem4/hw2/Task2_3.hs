module Task2_3 where

import Prelude

sumOfNum :: Integer -> Integer
sumOfNum 0 = 0
sumOfNum x = mod x 10 + sumOfNum (div x 10)