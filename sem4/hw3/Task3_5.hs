module Task3_5 where

import Prelude

fib :: Int -> Int
fib' :: Int -> Int -> Int -> Int
fib n = fib' n 0 1
fib' 0 result _ = result
fib' n old prev = if n > 0
                  then fib' (n - 1) prev (old + prev)
                  else fib' (n + 1) (prev - old) old