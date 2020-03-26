module Task2_5 where

import Prelude

sum :: [Integer] -> [Integer] -> [Integer] -> [Integer]
sum [] [] [] = []
sum [x] [] [] = [x]
sum [] [y] [] = [y]
sum [] [] [z] = [z]
sum [] (y:ys) (z:zs) = (y + z) : sum [] ys zs
sum (x:xs) [] (z:zs) = (x + z) : sum xs [] zs
sum (x:xs) (y:ys) [] = (x + y) : sum xs ys []
sum (x:xs) (y:ys) (z:zs) = (x + y + z) : sum xs ys zs