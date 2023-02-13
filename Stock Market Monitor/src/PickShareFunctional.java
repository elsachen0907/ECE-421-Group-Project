/*You should create a class named PickShareFunctional in which you will define one
method called findHighPrices.
To help you, this is how you should call findHighPrices method:
findHighPriced(Shares.symbols.stream());
As you can see, converting symbols from List to a Stream of symbols will allow you to
use the JDK specialized functional-style methods (i.e., map, reduce (fold), and filter).
Hint: In this method, you should do the following steps:
1- Create a list of ShareInfo filled with the price for each of the symbols in Shares
2- Trim down this list to a list of shares whose prices under $500
3- Return the highest-priced share. */
import java.util.List;
import java.util.stream.Stream;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class PickShareFunctional {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        findHighPriced(Shares.symbols.stream());
        long endTime = System.currentTimeMillis();

        System.out.println("The execution time of findHighPriced method is:" + (endTime-startTime) + " milliseconds");
    }
    public static ShareInfo findHighPriced(Stream<String> symbols) {
        List<ShareInfo> shares = symbols
           .map(symbol -> new ShareInfo(symbol, APIFinance.getPrice(symbol)))
           .filter(share -> share.price.compareTo(BigDecimal.valueOf(500)) < 0)
           .sorted((s1, s2) -> s2.price.compareTo(s1.price))
           .limit(1)
           .collect(Collectors.toList());
  
        if (shares.isEmpty()) {
           throw new NoSuchElementException("No high priced shares found");
        } else {
           return shares.get(0);
        }
    }
}