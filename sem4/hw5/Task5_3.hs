module Task5_3 where

import Control.Monad
import Prelude

fstMax :: (Ord a) => [a] -> Maybe a
fstMax (x:tail@(y:z:rest))
    | rest == [] = condition
    | otherwise = mplus condition $ fstMax tail
    where condition = if (y > x) && (y > z) then Just y else Nothing

test1 :: Bool
test1 = fstMax [9,1,4,3,5,2] == Just 4

test2 :: Bool
test2 = fstMax [5,4,3] == Nothing