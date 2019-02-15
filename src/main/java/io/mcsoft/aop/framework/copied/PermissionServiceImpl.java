package io.mcsoft.aop.framework.copied;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Override
    public List<Permission> getPermissionsByAccountId(Integer accountId){
        return new ArrayList<>();
    }

}
