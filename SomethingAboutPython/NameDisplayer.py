firstName = input("Please, enter your first name: ")
lastName = input("Please, enter your last name: ")

print("Full name: " + firstName + " " + lastName)
print("Length of your full name without space: " + str(len(firstName + lastName)))
print("Length of your full name with space: " + str(len(firstName + " " + lastName)))
