<h1>Formula 1 Elo Engine</h1>
<p>Uses historical data from every Formula 1 race since 1950 provided by the <a href="https://ergast.com/mrd/">Ergast API</a> to calculate each driver's elo ranking.</p>
<hr>
<h3>Basic Information</h3>
<p>Each driver starts with a ranking of 1500, and as the server works through each race, that ranking changes in accordance to algorithms losely based off of Chess.com's ranking system.</p>
<p>The highest elo ever achieved was 1910.582 by Fernando Alonso after the 2014 Hungarian Grand Prix.</p>
<p>A driver can gain or lose elo points based off of if they 'beat' their teammate(s). For instance, if Max Verstappen were to beat Sergio Pérez in a particular race, Verstappen would gain a certain amount of elo points and Pérez would lose a certain amount. The algorithm excludes Mechanical DNF's and disqualifications.</p>
<hr>
<h3>The Elo Rating System</h3>
<p>The elo model was originally created for chess, but in this case, it was adapted for Formula 1. The difference in ratings between two teammates determines the expected outcome and the total points won or lost for that particular matchup. If the higher-rated driver 'wins' (relative to their teammate), each driver's ranking will change little. However, if the lower-rated player wins, there will be a big change in rankings.</p>
<br>
<p>The driver who 'beats' their teammate gets a score of 1, while the loser gets a score of 0. If two teammates had equal ratings, their expected ratings would be 0.5 each. The formula to determine the expected scores can be seen below.</p>

<p align="center"><image src="https://miro.medium.com/v2/resize:fit:630/1*XIMUAMhRgbS983WfEHRQQQ.png"></image></p>
<br>
<p>The new rating for a player is calculated by a formula shown in the image below. Notably, _K_, the K-factor, is set to 32 in this elo engine.</p>
<p align="center"><image src="https://miro.medium.com/v2/resize:fit:1400/1*-zLVvrHp5qTD0nhgPRy-Mw.png"></image></p>



