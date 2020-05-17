module Task6_1 where

import Prelude

data Polynomial a = Empty | a :.: Polynomial a

instance (Show a, Num a, Ord a) => Show (Polynomial a) where
    show Empty = ""
    show p = if head result == '+' then tail result else result
        where result = show' p 0
              show' :: (Show a, Num a, Ord a) => Polynomial a -> Int -> String
              show' Empty _ = ""
              show' (a:.:rest) 0
                | a == fromInteger 0 = show' rest 1
                | otherwise = show a ++ show' rest 1
              show' (a:.:rest) 1
                | a == fromInteger 0 = show' rest 2
                | a < fromInteger 0 = (if a == fromInteger (-1) then "-" else show a ++ "*") ++ "x" ++ show' rest 2
                | otherwise = "+" ++ (if a == fromInteger 1 then "" else show a ++ "*") ++ "x" ++ show' rest 2
              show' (a:.:rest) n
                | a == fromInteger 0 = show' rest (n + 1)
                | a < fromInteger 0 = (if a == fromInteger (-1) then "-" else show a ++ "*") ++ "x^" ++ show n ++ show' rest (n + 1)
                | otherwise = "+" ++ (if a == fromInteger 1 then "" else show a ++ "*") ++ "x^" ++ show n ++ show' rest 2

instance (Num a) => Num (Polynomial a) where
    a + Empty = a
    Empty + b = b
    (x:.:xr) + (y:.:yr) = (x + y):.:(xr + yr)
    a * Empty = Empty
    Empty * b = Empty
    (x:.:xr) * (y:.:yr) = (x * y):.:((x:.:xr) * yr + xr * (y:.:Empty))