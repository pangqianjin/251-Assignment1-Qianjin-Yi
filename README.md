## Members of the Group
>Qianjin Pang 19029853
>
>Yi Gao 19029702

## How to run and folders contained
### how to run
There is a main method in the Main.java, 
just run it. Or because it is a Maven project, 
you can cd to the top level of the project and 
input <mvn exec:java "-Dexec.mainClass=Main"> in the 
command line and Enter.
### folders contained
>
    .
    ├── README.md
    ├── keywords.css
    ├── pom.xml
    ├── src
    │   ├── main
    │   │   ├── java
    │   │   │   ├── FileOperation.java
    │   │   │   ├── Main.java
    │   │   │   ├── Notepad.java
    │   │   │   └── Utils.java
    │   │   └── resources
    │   │       ├── notepad.fxml
    │   │       └── notepad.png
    │   └── test
    │       └── java
    │           └── NotepadTest.java
    └── tree.txt

6 directories, 11 files
### files description
*.java files is the source code, notepad.fxml is the configuration 
file for the FX Controller, and notepad.png is the 
icon of the Application. keywords.css is the style 
for the color of the keywords to be highlighted.
NotepadTest.java is a test file to test open, save and 
search.

## a couple of the most significant git commit IDs
>
    commit c33b7bb6752c7bbdcd5b9dd0c25a982ac1973bf1
    Merge: d000072 1f86ec1
    Author: gaoyi <577981827@qq.com>
    Date:   Tue Oct 6 18:29:09 2020 +0800
    
        Merge branch 'develop' of https://github.com/pangqianjin/251-Assignment1-Qianjin-Yi into develop
    
    commit d00007243d4703deaeb8cb47ff6918b58df5ab81
    Author: gaoyi <577981827@qq.com>
    Date:   Tue Oct 6 18:24:12 2020 +0800
    
        add export & printer func
    
    commit 1f86ec1e0597dbd2bf9dce531d7e92adafc90346
    Author: qianjin <2594622441@qq.com>
    Date:   Tue Oct 6 18:16:48 2020 +0800
    
        add save function, exit function and so on.
    
    commit 26f9b1febbab3cc664dd9b2c7dff488eaabee21a
    Author: qianjin <2594622441@qq.com>
    Date:   Tue Oct 6 18:04:01 2020 +0800
    
        add features to delete the tmp files generated after calling syntax
    
    commit efa315c9d9c52f339757128b758fbb6c9db4eb21
    Author: qianjin <2594622441@qq.com>
    Date:   Tue Oct 6 17:59:45 2020 +0800
    
        add the open function for notepad to open txt,c,cpp,java,odt files, add a sytax function in View menubar which highlight the current text area in a new page
    
    commit c5db8d9db2225f9e9eeecac0729092292cd24dd0
    Author: qianjin <2594622441@qq.com>
    Date:   Tue Oct 6 16:22:51 2020 +0800
    
        add a new function and cut,paste and copy for text area
    
    commit 421797e572d8902944900a3ee4db08c1b57599b0
    Author: gaoyi <577981827@qq.com>
    Date:   Tue Oct 6 16:11:26 2020 +0800
    
        add search func & optimize impl of time thread to stop
    
    commit 228be23951ce9143c043daef1b99fb336f7eef62
    Author: qianjin <2594622441@qq.com>
    Date:   Tue Oct 6 15:42:32 2020 +0800
    
        add time and date function
    
    commit 7f89d3d64ce78df539d7c4fadc9b9c144e0600e3
    Author: gaoyi <577981827@qq.com>
    Date:   Tue Oct 6 14:43:35 2020 +0800
    
        rm .idea
    
    commit 6e37df5e1043e28bbecde60dbd52a1b59d223e5b
    Author: gaoyi <577981827@qq.com>
    Date:   Tue Oct 6 14:37:50 2020 +0800
    
        add scpc & optimize search
    
    commit 2b5c28ab8be98e86b6d168d5f606b2d735b06295
    Author: qianjin <2594622441@qq.com>
    Date:   Tue Oct 6 11:37:37 2020 +0800
    
        add .gitignore and a NotepadTest frame and a main GUI frame
    
    commit 7d2122c836ba168d70e4900317ae91a32e4dc120
    Author: qianjin <2594622441@qq.com>
    Date:   Tue Sep 29 16:30:45 2020 +0800
    
        initial commit
