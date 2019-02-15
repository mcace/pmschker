package io.mcsoft.aop.framework.copied;

import java.util.List;

public interface PermissionService {
    List<Permission> getPermissionsByAccountId(Integer accountId);
}
