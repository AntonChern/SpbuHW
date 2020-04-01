module Task5_3 where

import Control.Monad
import Prelude

fstMax :: [Int] -> Int
fstMax = middleFst.discardOdd.toTriples

middleFst :: [(Int, Int, Int)] -> Int
middleFst [] = error "No such elements"
middleFst list = (\(x, y, z) -> y).head $ list

discardOdd :: [(Int, Int, Int)] -> [(Int, Int, Int)]
discardOdd = mfilter (\(x, y, z) -> (y > x) && (y > z))

toTriples :: [Int] -> [(Int, Int, Int)]
toTriples list = join $ zipWithM (\x (y, z) -> [(x, y, z)]) list $ toPairs $ tail list

toPairs :: [Int] -> [(Int, Int)]
toPairs list = join $ zipWithM (\x y -> [(x, y)]) list $ tail list