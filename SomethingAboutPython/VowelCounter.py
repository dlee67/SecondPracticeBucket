userInput = input("Please, type in a string token.")
counter = 0

for char in userInput:
    for vowel in "aeiou":
        if(char == vowel):
            counter = counter + 1

print("Amount of vowels in counter: " + str(counter))
