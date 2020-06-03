module Task3_2 where

import Prelude

series = zipWith (+) (concatMap ((replicate 3).(10*)) (0:series)) (concatMap (const [1,7,9]) [1..])