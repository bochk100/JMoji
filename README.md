JMoji is an esoteric, transcompiled programming language composed entirely of emojis. It is based in java and compiles .jmj files. It is currently a work in progress and was originally created as a class project.

Created by Sofya Bochkareva(@bochk100), Gabby Llanillo(@gabs820), and Shae Trimmer(@trimm103).

JMoji incorportates emoji-java (https://github.com/vdurmont/emoji-java), so special thanks to those guys.

For a sample output of the code, please check out test.jmj.

***DOCUMENTATION***
1. JMoji Basics.
2. Using the language.
3. List of tokens.

******

1. JMoji Basics

With the exception of tokens, any emoji can be used as an identifier. Currently, JMoji is limited to the emoji library provided by emoji-java (for a full list, please visit the github linked above), but we are working to encompass all emoji.

JMoji works by converting emojis into java code, which can then be compiled by a JVM. 

Because of the extensive use of emojis, a text editor with emoji functionality must be used. As such, JMoji is compatible with IntelliJ due to its compatibility.

2. Using the Language

Overall, the basic rules of Java apply.
The basic declaration of a main method is as follows:<br>
  🕳<br>
  🌜<br>
    //your code here.<br>
  🌛<br>

Loop declarations are as follows:<br>
  🔁🍬0:10:1<br>
  🌜<br>
    //your code here.<br>
  🌛<br>
(Which translates to for(int candy = 0; candy < 10; candy = candy +1) )<br>

3. List of Tokens
*primitives*<br>
bool: ☯️<br>
int: 🔢<br>
<br>
*declarations*<br>
equals(=): 🌈<br>
<br>
*methods*<br>
void = 🕳<br>
open bracket ("{"): 🌜<br>
closed bracket ("}"): 🌛<br>
<br>
*loops*<br>
for loop: 🔁<br>
<br>
*other*<br>
print to console: 🖨<br>
