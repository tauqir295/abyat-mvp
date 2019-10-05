Tools : Intellij Community edition
Java : 8
-----------------------------------------------------------------------
Inputs samples : sample_data folder contains text files for basketball and handball

-----------------------------------------------------------------------
Solution:

There is EBasketballPosition and EHandballPosition classes with properties position that is a position in current game and positions that are the positions that player took in all games of a tournament.

The Most Valuable Player calculation algorithm:

For each input statistics game file : readMatchStats from input file where data is parsed for Handball and Basketball at the moment

calculatePlayersPoints applying correct Position.calculatePoints algorithm

getMVPNickName of the tournament based on ratings aggregate value

The solution implements efficient sorting algorithm and can be easily extended by:

adding new Games in Main class and their respective Model class which will inherit iGame class
add enum class for position of new game which will implement IPosition
add MatchStatistics class for respective new game and implement it with AbstractPlayerMatchStatistics

The rest of the application should work without changes.

-----------------------------------------------------------------------
Output : 
Most valuable player nick3