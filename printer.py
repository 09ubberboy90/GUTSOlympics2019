print("this is a program that output its own code")
str = ""
with open("printer.py") as f:
    str += f.read()
print(str)