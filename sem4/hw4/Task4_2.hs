module Task4_2 where

import Prelude
import System.IO

main = do
    hSetBuffering stdin LineBuffering
    doLoop []

doLoop :: [Int] -> IO ()
doLoop list = do
    putStrLn "Enter command"
    command <- readLn
    case command of
        0 -> return ()
        1 -> do
                putStrLn "Enter added value:"
                value <- readLn
                newList <- doAdd list value
                doLoop newList
        2 -> do
                putStrLn "Enter removable value:"
                value <- readLn
                newList <- doRemove list value
                doLoop newList
        3 -> do
                putStr "["
                doPrint list
                putStrLn "]"
                doLoop list
        _ -> doLoop list

doAdd :: [Int] -> Int -> IO [Int]
doAdd list element
    | list == [] || element <= cur = return (element:list)
    | otherwise = do newRest <- doAdd rest element
                     return (cur:newRest)
    where cur = head list
          rest = tail list

doRemove :: [Int] -> Int -> IO [Int]
doRemove list element
    | list == [] || element < cur = fail "No such element"
    | element == cur = return rest
    | otherwise = do newRest <- doRemove rest element
                     return (cur:newRest)
    where cur = head list
          rest = tail list

doPrint :: [Int] -> IO ()
doPrint list
    | list == [] = return ()
    | length list == 1 = putStr (show cur)
    | otherwise = do putStr (show cur ++ ",")
                     doPrint rest
    where cur = head list
          rest = tail list