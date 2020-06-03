module Task4_3 where

import Prelude

data BinTree a = Empty | Branch (BinTree a) a (BinTree a)

instance Foldable BinTree where
    foldr f b Empty = b
    foldr f b (Branch l a r) = foldr f (f a (foldr f b r)) l

getList :: BinTree a -> [a]
getList = foldr (:) []

test :: Bool
test = getList (Branch (Branch Empty 9 Empty) 3 (Branch (Branch Empty 5 Empty) 7 (Branch Empty 4 Empty))) == [9,3,5,7,4]