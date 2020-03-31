module Task5_1 where

import Control.Monad
import Prelude

decompose :: Int -> IO ()
decompose n = forM_ (decompose' n) (putStrLn.show')

show' :: [Int] -> String
show' (e:es)
    | es == [] = show e
    | otherwise = show e ++ "+" ++ show' es

decompose' :: Int -> [[Int]]
decompose' n = do i <- [1..n]
                  decomposeOn i 1 n

decomposeOn :: Int -> Int -> Int -> [[Int]]
decomposeOn elem origin n
    | elem == 1 = [[n]]
    | otherwise = do j <- [origin..(n `div` elem)]
                     map (j:) $ decomposeOn (elem - 1) j (n - j)