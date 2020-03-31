module Task5_2 where

import Prelude

products :: Int -> [Int]
products n = sequence [[1..n],[1..n]] >>= \(x:y:[]) -> return (x*y)