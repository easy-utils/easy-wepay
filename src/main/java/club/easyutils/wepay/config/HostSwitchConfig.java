package club.easyutils.wepay.config;

import org.springframework.stereotype.Service;

/**
 * Host 双入口开关，默认为关闭
 * @author rainyblossom
 */
@Service
public interface HostSwitchConfig {

    /**
     * 是否激活双入口
     * @return
     */
    public Boolean doubleLiveSwitch();
}
