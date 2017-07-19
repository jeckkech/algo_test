/**
 * Created by Max on 19.07.2017.
 */
class TestAlgo {
    static void main(String[] args){

        def currentPath = "D://ALGO_TEST";
     //   print new File(currentPath).list()
        iterateThroughList(currentPath)

        println listFiles;
    }

    static File[] listFiles = [];

    static void iterateThroughList(String currentPath){
        def currentDir = new File(currentPath);

        def listFls = []
        File[] listDirs = []
         currentDir.listFiles().each {it->
            if(it.isFile()){
                listFls += it;
            } else {
                listDirs += it;
            }
        };

        listFiles += listFls;

        if(!withDirectory(listDirs)){
           int counter = 0
            int dirTrigger = 0;
            int filesCounter = countFiles(listDirs);
            println filesCounter


            int fileNumber = 0;
           while(counter <= filesCounter){
                if(listDirs.length - 1 >= dirTrigger){
                    fileNumber = iterateThroughFiles(listDirs, dirTrigger, fileNumber)
                } else {
                    dirTrigger = 0;
                    fileNumber++;
                    fileNumber = iterateThroughFiles(listDirs, dirTrigger, fileNumber)
                }
               dirTrigger++
               counter++
           }
        } else {
            listDirs.each {it->
                iterateThroughList(it.absolutePath)
            }
        }

    }


    static int iterateThroughFiles(File[] dirs, int dir, int file){
        if(dirs.length - 1>= dir){
            if(dirs[dir].listFiles().length - 1 >= file){
                listFiles += dirs[dir].listFiles()[file]
            } else {
                if(dir == dirs.length){
                    dir = 0
                } else {
                    dir++
                }
                iterateThroughFiles(dirs, dir, file)
            }
        }
        return file;
    }

    static boolean withDirectory(File[] files){
        boolean hasDir = false
        files.each {it ->
            it.listFiles().each {fl->
                if(fl.isDirectory()){
                    hasDir = true
                    return false;
                }
            }
        }
        return hasDir
    }

    static int countFiles(File[] files){
        int count = 0
        files.each {it->
            it.listFiles().each {fl->
                if(fl.isFile()){
                    count++;
                }
            }
        }
        return count;

    }
}
