module Task2_2 where

import Prelude

degreesTwo :: Integer -> [Integer]
degreesTwo 0 = []
degreesTwo n = 2 : map (*2) (degreesTwo (n - 1))