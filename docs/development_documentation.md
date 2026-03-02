**Table of Conetents**

-

# Introduction

# Build Guide

To build the application, run the following in the `/src` directory:

```bash
javac org/jsoundboard/main/JSoundBoard.java -d ../classes
```

Next, go to the `/classes` directory, make sure a `manifest.txt` file is there, and run the following:

```bash
jar -cvmf ../manifest.txt ../jsoundboard.jar org/
```

*Note:* You don't have to use the path above for the final destination for the jar file. You can put it where ever you feel is good.

To run the application, go to where the jar file is and run:

```bash
java -jar jsoundboard.jar
```
