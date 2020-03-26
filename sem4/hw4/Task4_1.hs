module Task4_1 where

import Prelude

evenNum1 :: [Int] -> Int
evenNum1 = sum.map (\x -> (x + 1) `mod` 2)

evenNum2 :: [Int] -> Int
evenNum2 = length.filter (\x -> x `mod` 2 == 0)

evenNum3 :: [Int] -> Int
evenNum3 = foldr (\x y -> (x + 1) `mod` 2 + y) 0