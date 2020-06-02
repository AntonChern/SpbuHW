module Task6_3 where

import Prelude
import Control.Monad.State

data Graph v e = Graph [(Int,v)] [(Int, Int, e)]

findWay :: (Num e, Ord e) => Graph v e -> Int -> [(e, [Int])]
findWay (Graph vl el) from = evalState (getWays el) ([], [from])
    where getWays :: (Num e, Ord e) => [(Int, Int, e)] -> State ([(e, [Int])], [Int]) [(e, [Int])]
          getWays list = do (result, neighbours) <- get
                            if neighbours == []
                            then return result
                            else do put (result, [])
                                    forM (toEdges neighbours list)
                                       (\(from, to, length) -> do (r, n) <- get
                                                                  put (addWays (fst (find from r) + length, snd (find from r) ++ [to]) r,  addWithoutRepeats to n))
                                    getWays list
          toEdges :: [Int] -> [(Int, Int, e)] -> [(Int, Int, e)]
          toEdges neighbours list = concatMap (\x -> filter (\(f, _, _) -> f == x) list) neighbours
          find :: (Num e, Ord e) => Int -> [(e, [Int])] -> (e, [Int])
          find a [] = (fromInteger 0, [a])
          find a (e@(_, xs):rest)
              | a == last xs = e
              | otherwise = find a rest            
          addWays :: (Ord e) => (e, [Int]) -> [(e, [Int])] -> [(e, [Int])]
          addWays element [] = [element]
          addWays element@(a, listA) result@((b, listB):rest)
              | last listA == last listB = if a >= b then result else element:rest
              | otherwise = (b, listB) : addWays element rest
          addWithoutRepeats :: (Eq e) => e -> [e] -> [e]
          addWithoutRepeats a [] = [a]
          addWithoutRepeats a (x:xs)
              | a == x = (x:xs)
              | otherwise = x : addWithoutRepeats a xs

test0 :: Bool
test0 = findWay (Graph [(1,"one"),(2,"two")] [(2,1,5)]) 2 == [(5,[2,1])]

test1 :: Bool
test1 = findWay (Graph [(1,1),(2,2),(3,3),(4,4)] [(1,2,7),(3,2,3),(1,3,2),(2,4,2),(3,4,8)]) 4 == []

test2 :: Bool
test2 = findWay (Graph [(1,'1'),(2,'2'),(3,'3'),(4,'4'),(5,'5'),(6,'6')] [(2,3,10),(3,6,2),(1,2,7),(2,4,15),(6,5,9),(3,4,11),(1,3,9),(4,5,6),(1,6,14)]) 1 == [(7,[1,2]),(9,[1,3]),(11,[1,3,6]),(20,[1,3,4]),(20,[1,3,6,5])]