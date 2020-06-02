module Task6_1 where

import Prelude
import Control.Monad
import Control.Monad.State
import System.Random

data BST a = Empty | Branch (BST a) a (BST a)
    deriving (Show, Eq)

add :: (Ord a) => BST a -> a -> BST a
add Empty a = Branch Empty a Empty
add (Branch l a r) b = if b > a then Branch l a (add r b) else Branch (add l b) a r

remove :: (Ord a) => BST a -> a -> BST a
remove Empty _ = Empty
remove (Branch l a r) b
    | a > b = Branch (remove l b) a r
    | a < b = Branch l a (remove r b)
    | otherwise = putBranch l r
        where putBranch :: BST a -> BST a -> BST a
              putBranch Empty Empty = Empty
              putBranch Empty tree@(Branch l a r) = tree
              putBranch tree@(Branch l a r) Empty = tree
              putBranch tree@(Branch l1 a1 r1) (Branch l2 a2 r2) = Branch (putBranch tree l2) a2 r2

exists :: (Ord a) => BST a -> a -> Bool
exists Empty _ = False
exists (Branch l a r) b
    | a > b = exists l b
    | a < b = exists r b
    | otherwise = True

size :: BST a -> Int
size Empty = 0
size (Branch l _ r) = 1 + size l + size r

height :: BST a -> Int
height Empty = 0
height (Branch l _ r) = 1 + max (height l) (height r)

getAny :: (Random a) => State StdGen a
getAny = do g <- get
            (x, g') <- return $ random g
            put g'
            return x

getRandomTree :: (Random a) => BST a -> State StdGen (BST a)
getRandomTree Empty = return Empty
getRandomTree (Branch l _ r) = do l' <- getRandomTree l
                                  a' <- getAny
                                  r' <- getRandomTree r
                                  return $ Branch l' a' r'

randomize :: (Random a) => BST a -> StdGen -> BST a
randomize tree = evalState (getRandomTree tree)


tree = (Branch (Branch Empty 1 Empty) 2 (Branch (Branch Empty 4 Empty) 3 (Branch Empty 5 Empty))) :: BST Int

testAdd = add tree 6 == (Branch (Branch Empty 1 Empty) 2 (Branch (Branch Empty 4 Empty) 3 (Branch Empty 5 (Branch Empty 6 Empty))))
testRemove1 = remove tree 3 == (Branch (Branch Empty 1 Empty) 2 (Branch (Branch Empty 4 Empty) 5 Empty))
testRemove2 = remove tree 6 == tree
testExists1 = exists tree 1 == True
testExists2 = exists tree 6 == False
testSize = size tree == 5
testHeight = height tree == 3
testRandomize = randomize tree (mkStdGen 1)