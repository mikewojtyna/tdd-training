# Shopping discount kata

There's a  need to use multiple discount policies when calculating the total cost of the shopping cart. The rules are as follows:

1. If sum of all items in the shopping cart is greater than > $500, then 2% discount is applied
2. If client is new (no purchases made previously), 1% discount is applied. For the needs of this policy, we distinguish clients by shipping addresses (cummulative with 1).
3. If client is regular (paid more than $2000 in the last year), then extra 5% discount applied (doesn't combine with 1).

We can assume that there will be more supported policies in the future, so try to write possibly the best code.