module Task5_4 where

import Prelude

data BinTree a = Leaf a | Branch (BinTree a) a (BinTree a)
    deriving (Eq, Show)

show' :: BinTree String -> String
show' (Leaf a) = a
show' (Branch l a r) = "(" ++ show' l ++ a ++ show' r ++ ")"


derivative :: String -> String
derivative l = show' $ reduce $ diff $ toTree $ postfix l [] []

index :: (Eq a) => [a] -> (a -> Bool) -> Int
index [] _ = 1
index (x:xs) f = if f x then 1 else 1 + index xs f

indexEnd :: [String] -> String -> Int
indexEnd l o
    | o `elem` ["+","-"] = index l (== "(")
    | otherwise = minimum [index l (== "+"), index l (== "-"), index l (== "(")]

postfix :: String -> [String] -> [String] -> [String]
postfix [] l a = reverse a ++ l
postfix (x:xs) l a
    | x `elem` ['0'..'9'] = let k1 = index (x:xs) (not.(`elem` ['0'..'9'])) - 1 in postfix (drop k1 (x:xs)) l (take k1 (x:xs) : a)
    | x == 'x' = postfix xs l ([x]:a)
    | x == '(' = postfix xs ([x]:l) a
    | x == ')' = let k2 = index l (== "(") - 1 in postfix xs (drop (k2 + 1) l) (reverse (take k2 l) ++ a)
    | otherwise = let k3 = indexEnd l [x] - 1 in postfix xs ([x]:(drop k3 l)) (reverse (take k3 l) ++ a)

toTree :: [String] -> BinTree String
toTree l = toTree' l []
toTree' [] a = head a
toTree' (x:xs) a
    | x `elem` ["+","-","*","/"] = toTree' xs (Branch arg1 x arg2 : (drop 2 a))
    | otherwise = toTree' xs (Leaf x : a)
        where arg1 = head $ tail a
              arg2 = head a

diff :: BinTree String -> BinTree String
diff (Leaf "x") = Leaf "1"
diff (Leaf s) = Leaf "0"
diff (Branch l "*" r) = Branch (Branch (diff l) "*" r) "+" (Branch l "*" (diff r))
diff (Branch l "/" r) = Branch (Branch (Branch (diff l) "*" r) "-" (Branch l "*" (diff r))) "/" (Branch r "*" r)
diff (Branch l operand r) = Branch (diff l) operand (diff r)

reduce :: BinTree String -> BinTree String
reduce (Leaf m) = Leaf m
reduce (Branch (Leaf "1") "*" r) = reduce r
reduce (Branch l "*" (Leaf "1")) = reduce l
reduce (Branch (Leaf "0") "*" _) = Leaf "0"
reduce (Branch _ "*" (Leaf "0")) = Leaf "0"
reduce (Branch (Leaf "0") "+" r) = reduce r
reduce (Branch l "+" (Leaf "0")) = reduce lreduce (Branch (Leaf "0") "-" (Leaf m)) = Leaf ("-" ++ m)
reduce (Branch (Leaf "0") "-" (Branch l "-" r)) = reduce $ Branch r "-" l
reduce (Branch l "-" (Leaf "0")) = reduce l
reduce (Branch l "/" (Leaf "1")) = reduce l
reduce (Branch (Leaf "0") "/" _) = Leaf "0"
reduce (Branch l operand r) = if newL == l && newR == r then Branch newL operand newR
                                                        else reduce (Branch newL operand newR)
                              where newL = reduce l
                                    newR = reduce r