package cn.meng.demo.register.server.cluster;

import cn.meng.demo.register.server.web.AbstractRequest;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * 待同步信息打包
 */
@Data
public class PeersReplicatorBatch {

    /**
     *存放打包的request
     */
    private List<AbstractRequest> requests = new LinkedList<>();
    public void add(AbstractRequest request){
        requests.add(request);
    }


}
