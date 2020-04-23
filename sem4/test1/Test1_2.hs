module Test1_2 where

import Prelude

f :: Int -> [[Int]]
f n = map (\k -> replicate k k ++ [k + 1..n]) [1..n]