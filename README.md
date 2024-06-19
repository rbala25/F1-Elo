<h1>Formula 1 Elo Engine</h1>
<p>Uses historical data for every Formula 1 race provided by the <a href="https://ergast.com/mrd/">Ergast API</a> to calculate each driver's highest elo ranking.</p>

<p>Each driver starts with a ranking of 1500, and as the server goes through each race, that elo changes in accordance to formulas losely based off of Chess.com's ranking system.</p>
<p>The highest elo ever hit was 1910.582 by Fernando Alonso after the 2014 Hungarian Grand Prix.</p>
<p>A driver can gain or lose elo points based off of if they 'beat' their teammate(s), meaning that they were classified higher. The algorithm excludes Mechanical DNF's and disqualifications.</p>
