## the URL of our repo
>https://github.com/pangqianjin/251-Assignment1-Qianjin-Yi.git

## Members of the Group
>Qianjin Pang 19029853
>
>Yi Gao 19029702

## How to run and folders contained
### how to run
Required Java version 1.8 at least.
For higher version, need install JavaFX dependently to run.

There is a main method in the Main.java, 
just run it. Or because it is a Maven project, 
you can cd to the top level of the project and 
input <mvn exec:java "-Dexec.mainClass=Main"> in the 
command line and Enter.

### folders contained
>
    .
    ├── README.md
    ├── gitlog.txt
    ├── pom.xml
    ├── reports
    │   ├── metrics
    │   │   ├── CodeComplexity.csv
    │   │   └── CodeSize.csv
    │   └── pmd
    │       └── report.html
    ├── src
    │   ├── main
    │   │   ├── java
    │   │   │   ├── Configuration.java
    │   │   │   ├── FileOperation.java
    │   │   │   ├── KeyWordsMap.java
    │   │   │   ├── Main.java
    │   │   │   ├── Notepad.java
    │   │   │   └── Utils.java
    │   │   └── resources
    │   │       ├── conf.yml
    │   │       ├── keywords.css
    │   │       ├── notepad.fxml
    │   │       └── notepad.png
    │   └── test
    │       ├── java
    │       │   └── NotepadTest.java
   │       └── test_open_content.txt
    └── tree.txt

    9 directories, 19 files



### files description
*.java files is the source code, notepad.fxml is the configuration 
file for the FX Controller, and notepad.png is the 
icon of the Application. keywords.css is the style 
for the color of the keywords to be highlighted.
NotepadTest.java is a test file to test open, save and 
search. test_open_content.txt is for the test.

## a couple of the most significant git commit IDs
>
    commit 9b61f473f16beb20ab10fac81e95e24f3e6d135e
    Author: gaoyi <577981827@qq.com>
    Date:   Wed Oct 7 18:45:49 2020 +0800

        change gitignore
    
    commit 6bc9caffbe13e108faf89725831dc4e37dc0b280
    Merge: 3b9b97a 201daa2
    Author: gaoyi <577981827@qq.com>
    Date:   Wed Oct 7 18:43:58 2020 +0800
    
        Merge branch 'develop'
    
    commit 201daa26453995656a71e8662d179f63f0382e9a
    Merge: b4cbff6 0ca67ad
    Author: gaoyi <577981827@qq.com>
    Date:   Wed Oct 7 18:40:03 2020 +0800
    
        Merge branch 'develop' of https://github.com/pangqianjin/251-Assignment1-Qianjin-Yi into develop
    
    commit b4cbff62970a3b88c8e400b6b97e244997057ed1
    Author: gaoyi <577981827@qq.com>
    Date:   Wed Oct 7 18:37:54 2020 +0800
    
        fix test
    
    commit 34d1afcf122c8bf9712d0c5321e1d7167a324184
    Author: gaoyi <577981827@qq.com>
    Date:   Wed Oct 7 18:36:53 2020 +0800
    
        optimize utils & adapt to configuration
    
    commit 6f390c48c110aafa64f8307f1921dca24e01c522
    Author: gaoyi <577981827@qq.com>
    Date:   Wed Oct 7 18:26:34 2020 +0800
    
        optimize printer func
    
    commit 5fe2e9f0c7747f721714deff2140c88d26a34c6d
    Author: gaoyi <577981827@qq.com>
    Date:   Wed Oct 7 18:25:40 2020 +0800
    
        add about func configuration and syntex highlight configuration
    
    commit 3435dbd8eeafb2aac31234f2e31d868802222411
    Author: gaoyi <577981827@qq.com>
    Date:   Tue Oct 6 22:51:16 2020 +0800
    
        fix test bug for search
    
    commit 0ca67ad034f1cadf4358c2c53415fba68d3a80e8
    Author: qianjin <2594622441@qq.com>
    Date:   Tue Oct 6 22:02:01 2020 +0800
    
        add reports
    
    commit 5c7aaa25de222ddf60f1a42f434bcf183d89f2c1
    Author: qianjin <2594622441@qq.com>
    Date:   Tue Oct 6 21:17:35 2020 +0800
    
        updated README.md
    
    commit 3b9b97adee46bfb6bf3ab57a2a98ceae0ab6a7bf
    Merge: b68939f 69f7d13
    Author: gaoyi <577981827@qq.com>
    Date:   Tue Oct 6 19:36:43 2020 +0800
    
        Merge branch 'develop'
    
    commit 69f7d13139087c83783b2c82669d81cb5031aecb
    Author: gaoyi <577981827@qq.com>
    Date:   Tue Oct 6 19:35:27 2020 +0800
    
        add about func
    
    commit b68939f4ba2b64c9acb9716a3b5e3f26a744a3e4
    Author: gaoyi <577981827@qq.com>
    Date:   Tue Oct 6 19:28:06 2020 +0800
    
        add about func
    
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

