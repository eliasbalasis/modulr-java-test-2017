# modulr-java-test-2017

Assumptions made
================
No limit for ATM notes capacity

No thread safety concerns for ATM, isolated use, single access at any point in time

No write access concurrency for accounts repository, isolated use, single access at any point in time

Interpretation of statement "*Allow withdrawals between 20 and 250 inclusive, in multiples of 5*" as "*Allow withdrawal range between 20 and 250 trying to give (if possible) 5 notes of each respective denomination*"
