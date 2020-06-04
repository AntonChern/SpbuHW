module Task2_5 where

import Prelude

sum3 :: [Int] -> [Int] -> [Int] -> [Int]
sum3 l1 l2 l3 = zipWith3 (\x y z -> x + y + z) (expand l1) (expand l2) (expand l3)
    where expand list = list ++ replicate (mlen - length list) 0
          mlen = maximum [length l1, length l2, length l3]

test :: Bool
test = sum3 [1,2,3] [4,5] [6] == [11,7,3]