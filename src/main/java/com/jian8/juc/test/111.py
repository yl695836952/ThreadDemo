def trim(s):
    for i in range(len(s)):
        if s[0] == '-':
            s = s[1:]
    for k in range(len(s)):
        if s[-1] == '-':
            s = s[:-1]
    return s
a = trim('----老王高啊！----')
print(a)