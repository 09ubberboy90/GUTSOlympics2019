import random

def main():
    number = input("Please enter number : ")
    str = []
    count = 0
    operator = ["*","+","-","/"]
    for o in number:
        if o.isdigit():
            count +=1
    for l in range(count-1):
        if number[l] == "-":
            str.append(-float(number[l+1]))
            number = number.replace("-","",1)
        else:
            str.append(float(number[l]))
        str.append(operator[random.randint(0,3)])
    str.append(number[-1])
    print(str)
    while True:
        if "*" in str and "/" in str:
            mul = str.index("*")
            div = str.index("/")
            if mul<div:
                toto = 1
            else:
                toto = 2
        elif "*"in str and "/" not in str:
            toto = 1
        elif "/"in str and "*" not in str:
            toto = 2

        if toto == 1:
            ind = str.index("*")
            str[ind-1] = int(str[ind-1]) * int(str[ind+1])
            del(str[ind+1])
            del(str[ind])
        if toto == 2:
            ind = str.index("/")
            str[ind-1] = int(str[ind-1]) / int(str[ind+1])
            del(str[ind+1])
            del(str[ind])
        if "*" not in str and "/" not in str:
            break
    
    while len(str) >1:
        if str[1] == "-":
            str[0] = int(str[0]) - int(str[2])
        if str[1] == "+":
            str[0] = int(str[0]) + int(str[2])
        del(str[2])
        del(str[1])
    print(str[0])
main()