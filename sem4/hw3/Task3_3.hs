module Task3_3 where

import Prelude

diff :: [Int] -> Int
maxi :: [Int] -> Int
diff = maxi.diff'
diff' [] = []
diff' (element:[]) = []
diff' (frst:scnd:rest) = (frst + scnd) : diff' (scnd:rest)
maxi [] = error "Not enough elements"
maxi series = maxi' series 1 (1, head series)
maxi' (last:[]) indexCurr (indexMax, maxElem) = if last > maxElem
                                                then indexCurr + 1
                                                else indexMax
maxi' (curr:rest) indexCurr (indexMax, maxElem) = maxi' rest (indexCurr + 1) (if head rest > maxElem
                                                                              then (indexCurr + 1, head rest)
                                                                              else (indexMax, maxElem))