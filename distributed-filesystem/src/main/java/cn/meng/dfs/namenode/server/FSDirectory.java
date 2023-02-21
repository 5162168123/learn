package cn.meng.dfs.namenode.server;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

public class FSDirectory {
    private INodeDirectory dirTree;

    public FSDirectory(){
        this.dirTree = new INodeDirectory("/");

    }

    public synchronized void mkdir(String path){

        //分割/aa/bb/cc目录，从根目录递归查找
        //先查找aa，查找成引用，下次查找用，否则新建目录后再引用
        String[] paths = path.split("/");
        INodeDirectory parent = dirTree;
        for (String splitPath : paths){
            if(splitPath.equals(""))
                continue;

            INodeDirectory directory = findDirectory(parent, splitPath);
            if(directory !=null){
                parent = directory;
                continue;
            }
            INodeDirectory child = new INodeDirectory(splitPath);
            parent.add(child);
            parent = child;

        }

        System.out.println("创建了目录："+path);
    }


    /**
     * 查找目录
     * @param dir
     * @param path
     * @return
     */
    private INodeDirectory findDirectory(INodeDirectory dir,String path){

        //判目录是否为空
        if(dir.getPath() == null || dir.getChildren().isEmpty()){
            return null;
        }
        for(INode child : dir.getChildren()){
            INodeDirectory dirChild;
            if(child instanceof INodeDirectory){
                if((dirChild = (INodeDirectory)child).getPath().equals(path)){
                    return dirChild;
                }
            }
        }
        return null;
    }

    private interface INode{};


    /**
     * 代表文件目录树中的目录
     */
    @Data
    private class INodeDirectory implements INode{
        private String path;
        private List<INode> children;
        public INodeDirectory (String path){
            this.path = path;
            this.children = new LinkedList<>();
        }

        public void add(INode node) {
            this.children.add(node);
        }
    }


    /**
     * 代表文件目录树中的文件
     */
    @Data
    private class INodeFile implements INode{
        private String name;

    }
}
