age = input("How old are you?")
months = input("How many months would like our service?")

if int(age) <= 18:
    print("You will be charged for: ", 15 * int(months), "dollars")
elif int(age) > 19 and age <= 64:
    print("You will be charged for: ", 25 * int(months), "dollars")
elif int(age) >= 65:
    print("You will be charged for: ", 20 * int(months), "dollars")
