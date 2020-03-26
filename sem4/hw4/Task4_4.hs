module Task4_4 where

import Prelude
import System.IO
import Control.Exception

main = do
    hSetBuffering stdin LineBuffering
    doLoop []

doLoop :: [(String, Int)] -> IO ()
doLoop list = do
    putStrLn "Enter command"
    command <- readLn
    case command of
        0 -> return ()
        1 -> do
                putStrLn "Enter name:"
                name <- getLine
                putStrLn "Enter number:"
                number <- readLn
                putStrLn "Record added"
                doLoop ((name, number):list)
        2 -> do
                putStrLn "Enter name:"
                name <- getLine
                number <- doFindNumber list name
                putStrLn ("Number: " ++ show number)
                doLoop list
        3 -> do
                putStrLn "Enter number:"
                number <- readLn
                name <- doFindName list number
                putStrLn ("Name: " ++ show name)
                doLoop list
        4 -> do
                putStrLn "Enter file name:"
                fileName <- getLine
                doWrite fileName list
                putStrLn "Data recorded"
                doLoop list
        5 -> do
                putStrLn "Enter file name:"
                fileName <- getLine
                newList <- doRead fileName list
                putStrLn "Data read"
                doLoop (list ++ newList)
        _ -> doLoop list

doFindNumber :: [(String, Int)] -> String -> IO Int
doFindNumber list name
    | list == [] = fail "No such name"
    | fst cur == name = return (snd cur)
    | otherwise = doFindNumber rest name
    where cur = head list
          rest = tail list

doFindName :: [(String, Int)] -> Int -> IO String
doFindName list number
    | list == [] = fail "No such number"
    | snd cur == number = return (fst cur)
    | otherwise = doFindName rest number
    where cur = head list
          rest = tail list

doWrite :: String -> [(String, Int)] -> IO ()
putList :: [(String, Int)] -> Handle -> IO ()
putList [] h = hPutStr h ""
putList ((name, number):rest) h = do hPutStrLn h name
                                     hPutStrLn h (show number)
                                     putList rest h
doWrite fileName list = bracket (openFile fileName WriteMode) hClose (putList list)

doRead :: String -> [(String, Int)] -> IO [(String, Int)]
getList :: Handle -> IO [(String, Int)]
getList h = do isEnd <- hIsEOF h
               if isEnd
               then return []
               else do name <- hGetLine h
                       number <- hGetLine h
                       rest <- getList h
                       return ((name, read number :: Int):rest)
doRead fileName list = bracket (openFile fileName ReadMode) hClose getList