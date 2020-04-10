module Test1_4 where

import Prelude

superMap :: [a] -> (a -> [b]) -> [b]
superMap list f = list >>= (\x -> f x)