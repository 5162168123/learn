package cn.meng.dfs.namenode.server;

public class FSNameSystem {

    private FSDirectory fsDirectory;

    private FSEditLog fsEditLog;

    public FSNameSystem(){
        fsDirectory = new FSDirectory();
        fsEditLog = new FSEditLog();
    }

    public boolean mkdir(String path){
        this.fsDirectory.mkdir(path);
        this.fsEditLog.logEdit(path);
        return true;
    }
}
