package club.easyutils.wepay.model.doubleLive;

import club.easyutils.wepay.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 双入口分析模型对象
 * @author rainyblossom
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoubleLiveAnalyzeModel extends BaseModel {

    /** 请求的地址 **/
    private String url;

    /** 是否成功 **/
    private Boolean isSuccess;

    /** 耗时（毫秒） **/
    private Double timeConsuming;

}
