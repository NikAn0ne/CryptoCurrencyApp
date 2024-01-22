CryptoCurrencyApp - a project that will store up-to-date information on the exchange rate of modern cryptocurrencies. The user can view a particular cryptocurrency and add it to favorites.

--------------------------------------------------------

Structure:

1. MainFragment - a fragment with list of various currencies. There are two tabs "top gainers" and "top losers". Each item is clickable. Clicking a currency navigates to the details of that currency. Where you can see detailed statistics of the selected currency.

2. Details Fragment - contains details of choosen currency such as information chart, full statistic of currency and currency changes per day/week/mounth/year.

3. MarketFragment - a fragment with list of all currencies and search bar. You can add any currency to your favorite.

4. FavoriteFragment - a fragment with list of your favorite currencies. There are some short information about currency such as name, changes and chart.

--------------------------------------------------------

API being used:

1. coingecko 
2. coinmarketcap
3. coinAPI

--------------------------------------------------------

Libraries being used:

Retrofit,ROOM,LiveData,DataBinding?,Lottie?,Glide?.


