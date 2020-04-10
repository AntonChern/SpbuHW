module Test1_3 where

import Control.Monad
import Prelude

printRhombus :: Int -> IO ()
printRhombus n = forM_ ([n - 1,n - 2..0] ++ [1..n - 1]) (\k -> do forM_ [1..k] (\x -> putStr(" "))
                                                                  forM_ [1..2 * (n - k) - 1] (\x -> putStr("*"))
                                                                  forM_ [1..k] (\x -> putStr(" "))
                                                                  putStrLn(""))