module Test1_1 where

import Prelude

series = 1 : map negate series

getList :: [Int]
getList = zipWith (*) [1..] series