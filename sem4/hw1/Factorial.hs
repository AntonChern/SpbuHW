module Factorial where

import Prelude

factorial :: Integer -> Integer
factorial 0 = 1
factorial x = x * factorial (x - 1)