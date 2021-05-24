# Dobrodružná hra
V hre je jeden hráč, ktorému je umožnené sa pohybovať po hracej ploche, ktorá je dvojrozmerné pole o veľkosti 11. 
Je mu umožnené navštevovať rôzne zaujímavé destinácie. Každá z nich má pri tom nejaké unikátne úlohy, ktoré tu hráč môže 
vykonávať. Tie mu následne môžu zvýšiť alebo znížiť jeho HP (health points) a zvýšiť alebo znížiť jeho skóre. Pri spustení 
hry je hráč umiestený do stredu plochy, v základnej destinácií Domček, od ktorej sa môže pohybovať vo všetkých smeroch (hore, dole, doľava, doprava).
Je možné si pozrieť podrobný popis danej destinácie a to tak, že užívateľ na ňu klikne myšou.
![](https://github.com/absolutty/javaApps/blob/main/DobrodruznaHra/gui.PNG)

Destinácia **Domček** umožňuje hráčovi úkony:
- doplniť HP výmenou za jeho skóre (doplňované HP = pocet_skore / 10)
- kúpiť si lístok do lotérie s ktorým je možné vyhrať AŽ 1000 skóre
Destinácia **Hrad** umožňuje pokladá hrá z hráčom otázkové hry
- pri jednom navštívení sú mu položené 3 otázky
- môže si zvoliť či chce odpovedať na áno/nie otázky
  - ak hráč uhádne otázku, pripočíta mu k skóre +20
  - ak hráč neuhádne otázku, odpočíta mu od skóre -20
- môže si zvoliť odpovedanie na hádanky
  - ak hráč uhádne hádanku, pripočíta mu k skóre +30
  - ak hráč neuhádne hádanku, odpočíta mu od skóre -30

Destinácia **Park** umožňuje dáva hráčovi príklady na ktoré zadáva výsledky
- pri jednom navštívení sú mu dané 3 príklady
- ak hráč trafí výsledok príkladu, pripočíta mu k skóre +25
- ak hráč netrafí výsledok príkladu, odpočíta mu od skóre -25

Destinácia **RandomEvent** hráčovi môže uškodiť alebo mu môže pomôcť
- je zložená s niekoľkých náhodných udalostí (tie majú rôzne pravdepodobnosti), kt. môžu nastať
- lepší prípad: pripočítajú mu HP + pripočítajú mu skóre
- horší prípad: odpočítajú mu HP + odpočítajú mu skóre 

Trieda **PosunException** je výnimka, kt. nastane ak sa hráč pokúša posunúť mimo hracej 
plochy
- obsahuje atribúty x a y, kt. sú potom použité na opätovné nastavenie pozície hráča

Trieda **Menu** slúži na vykreslovanie pravej strany grafického zobrazenia. (Vykresluje tzv. menu.)

Trieda **RandSuradnice** je určená pre vygenerovanie random súradnic v rozsahu hracej plochy.

Trieda **Suradnice** aktuálne súradnice na ktorých sa nachádza hráč.

Trieda **DobrodruznaHra** slúži na vykreslenie a spustienie hry. Je návrhového typu Singleton.

Trieda **Hrac** obsahuje základne informácie o hráčovi kt. sú dynamicky menené počas priebehu hry.

Trieda **Policko** trieda extenduje Button, čo znamená že každému poličku je možné nastaviť onClickListener{} (vypíše info). Políčko je vykreslované na plochu. 
