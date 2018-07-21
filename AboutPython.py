a = input("First value please: ")
b = input("Second value please: ")

def hcfnaive(a,b):
    if(b==0):
        return a
    else:
        return hcfnaive(b,a%b)

someVal = hcfnaive(int(a), int(b))
print(str(someVal), "\n")


numberOfOccur = 0
str = input("String please: ")
listOfVowels = ['a', 'e', 'i', 'o', 'u']
for chr in str:
    if chr in listOfVowels:
        numberOfOccur  = numberOfOccur + 1
print (numberOfOccur)
