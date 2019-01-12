entry = len(input("Word : "))
print(entry)
diclen = {}
for l in range(50):
    diclen[l] = []

with open("Words/word.txt") as f:
    for line in f:
        diclen[len(line)].append(line)

if entry>17:
    print("No words bigger than that")
else :
    print("Your word is : " + diclen[entry+2][0])
