weight = input("How much is your weight (in pound)?")
height = input("How tall are you (in inches)?")

BMI = (float(weight) * 703) / (float(height) ** 2)

print("BMI is: ", BMI)

if(BMI < 25 and 18.5 < BMI):
    print("NICE BOOODYY!!!")
elif(BMI > 25):
    print("Hmph, nothing against larger side of folks, but it's healthy get some exercise.")
elif(BMI < 18):
    print("Are you eating right?")
