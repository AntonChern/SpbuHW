module Task2_1 where

import Prelude

reverse' :: [t] -> [t]
reverse' = foldl (\x y -> y:x) []