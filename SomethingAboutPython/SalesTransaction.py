try:
    price = input("Please, enter the price of the item of your choice: ")
    name = input("Please, enter the name of your item: ")
except ValueError as valerr:
    print("Did you put invalid input for one the prompt?")
    raise ValueError
stateSalesTax = 0.04
countySalesTax = 0.02

print("The total amount is: ", int(price) * stateSalesTax + int(price) * countySalesTax)
print("The state sales tax is: ", stateSalesTax)
print("The country sales tax is: ", countySalesTax)
print("The name of your product is: ", name)
