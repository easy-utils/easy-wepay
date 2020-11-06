package club.easyutils.wepay.config;

import org.springframework.stereotype.Service;

/**
 * Host 双入口开关，默认为关闭
 * @author rainyblossom
 */
public abstract class HostSwitchAbstractConfig implements HostSwitchConfig{


    @Override
    public Boolean doubleLiveSwitch() {
        return false;
    }
}
