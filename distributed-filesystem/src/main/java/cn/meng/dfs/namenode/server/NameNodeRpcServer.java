package cn.meng.dfs.namenode.server;

public class NameNodeRpcServer {

    private FSNameSystem fsNameSystem;

    public NameNodeRpcServer(FSNameSystem fsNameSystem){
        this.fsNameSystem = fsNameSystem;
    }

    /**
     * 创建目录接口
     * @param path 目录路径
     * @return true 创建成功
     */
    public boolean mkdir(String path){
        return fsNameSystem.mkdir(path);
    }
}
