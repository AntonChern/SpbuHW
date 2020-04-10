module Task5_3 where

import Control.Monad
import Prelude

fstMax :: (Ord a) => [a] -> Maybe a
fstMax (x:tail@(y:z:rest))
    | rest == [] = condition
    | otherwise = mplus condition $ fstMax tail
    where condition = if (y > x) && (y > z) then Just y else Nothing