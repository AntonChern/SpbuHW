module Task3_3 where

import Prelude

imax :: (Num a, Ord a) => [a] -> Int
imax l = imax' $ zipWith (+) l $ tail l
    where imax' :: (Num a, Ord a) => [a] -> Int
          imax' [] = error "Not enough elements"
          imax' l@(x:xs) = if x == maximum l then 1 else 1 + (imax' xs)

test :: Bool
test = imax [4,1,8,3,2,5,6] == 3