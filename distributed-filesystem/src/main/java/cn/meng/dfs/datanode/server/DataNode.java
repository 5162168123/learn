package cn.meng.dfs.datanode.server;

public class DataNode {

    private volatile Boolean shouldRun;
    private NameNodeGroupOfferService offerService;
    public static void main(String[] args) {
        DataNode dataNode  = new DataNode();
        dataNode.initialize();
        dataNode.run();
    }

    private void run() {
        try{
            while (shouldRun){
                Thread.sleep(10 * 1000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initialize() {
        shouldRun = true;
        offerService = new NameNodeGroupOfferService();
        offerService.start();
    }
}
