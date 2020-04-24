module Task6_1 where

import Prelude

data Polynomial a = Empty | a :.: Polynomial a

instance (Show a) => Show (Polynomial a) where
    show Empty = ""
    show (a:.:Empty) = show a
    show (a:.:rest) = show a ++ "+x*(" ++ show rest ++ ")"

instance (Num a) => Num (Polynomial a) where
    a + Empty = a
    Empty + b = b
    (x:.:xr) + (y:.:yr) = (x + y):.:(xr + yr)
    a * Empty = Empty
    Empty * b = Empty
    (x:.:xr) * (y:.:yr) = (x * y):.:((x:.:xr) * yr + xr * (y:.:Empty))