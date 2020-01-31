# Equal Experts Coding challenge (v  e5ce2b40e077ee165c0a6b7598317089354c8922)

This project is a regular maven java project, so use 
```bash
mvn clean test 
```
It has no extra dependencies except for:
* junit
* hamcrest
* lombok

If one wants to know how the code was created (the way of thinking) please look at git history.  

## (Known) Limitations
This is a toy project and in a real implementation i would:
* have a number of questions to PO regarding validation rules
* probably parameterize some of tests
* i see a little too much of primitive obsession:
    * tax rate could be modelled as object (value object) as the way how it works is pretty bad - client does not know if 12.5 means 125 % or 12.5 %
    * returning (money) values as BigDecimal is not particular friendly - I was thinking of creating Money object (or using one from Java) but the challenge description says nothing on currency...
* build more test cases for rounding rules - this is where things can get tricky...    

