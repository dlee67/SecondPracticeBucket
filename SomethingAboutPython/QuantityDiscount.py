total = null
p = .99
amountOfPurchase = input("How many packages did you buy?")

if(amtOfPur < 19 and amtOfPur >= 10):
   total = (amtOfPur * p) * 0.2
   if(amtOfPur =< 49 and amtOfPur >= 20):
      total = (amtOfPur * p) * 0.3
      if(amtOfPur <= 99 and amtOfPur >= 50):
         total = (amtOfPur * p) * 0.4
         if(amtOfPur >= 100):
            total = (amtOfPur * p) * 0.3
else:
   print("Unable to calculate, must be an invalid input.")
