package io.mcsoft.aop.framework.copied;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by MC on 2019/2/15.
 *
 * @author MC
 * @date 2019/2/15
 */
@Service
public class IndexService {
    public Response<AccountPO> getLoginUserInfo(HttpServletRequest request) {
        return ResponseUtils.error("");
    }
}
