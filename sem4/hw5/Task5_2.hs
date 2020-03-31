module Task5_2 where

import Prelude

products :: Int -> [Int]
products n = [1..n] >>= \x -> [1..n] >>= \y -> return (x*y)