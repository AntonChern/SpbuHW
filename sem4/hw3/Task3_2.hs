module Task3_2 where

import Prelude

series = 1 : 7 : 9 : concat [(10 * x + (series !! 0)) : (10 * x + (series !! 1)) : (10 * x + (series !! 2)) : [] | x <- series ]