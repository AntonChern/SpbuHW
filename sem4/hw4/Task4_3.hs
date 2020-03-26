module Task4_3 where

import Prelude

data BinTree a = Empty | Leaf a | Branch (BinTree a) a (BinTree a)

instance Foldable BinTree where
    foldr f b Empty = b
    foldr f b (Leaf a) = f a b
    foldr f b (Branch l a r) = foldr f (f a (foldr f b r)) l

getList :: BinTree a -> [a]
getList = foldr (:) []