-Using TreeSet for Containing Users because its efficient at searching O(log(n)), plus it doesn't allow duplicates.
-Using TreeSet for Containing Trails because it has fast searching O(log(n)) and doesn't allow duplicates and doesn't need a key,
though I am filtering using streams which is O(n) which is why I decided against using any Hash.
-Using LinkedList for Containing Reviews, Comments, and Hiking History because, it has an O(1) for insertion and deletion,
plus it stays in order of insertion. I also don't need to search for a specific review, comment, or hiking history. Also,
it does allow duplicates.
-Using TreeSet for Blocked User, Followers and Following because it does not allow duplicates, and it doesn't need a key,
since I am just using a String to represent the username a wouldn't be able to use a key anyway.