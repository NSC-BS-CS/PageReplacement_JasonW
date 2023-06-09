# REPORT
### Findings
For the most part, the OPT algorithm performed the best. This is not surprising considering it's supposed to be
the ideal algorithm, meant for other algorithms to compare against. It has the advantage of looking ahead, so that it's
able to predict the most optimal page to replace. 

LRU didn't do bad either, coming in second place most of the time. Although the difference between LRU and FIFO was less
than I expected, with FIFO on a regular basis beating out LRU in some of the tests. Part of this I think is the small
size of the input we're working with. 