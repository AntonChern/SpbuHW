module Test1_5 where

import Prelude

check :: [a] -> (a -> Bool) -> Bool
check [] _ = True
check (x:xs) f = f x && check xs f