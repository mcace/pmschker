package io.mcsoft.aop.framework.validator.permission.handler;

import io.mcsoft.aop.framework.annotation.permission.permissions.MenuKeys;
import io.mcsoft.aop.framework.annotation.permission.permissions.RequireMenuPermission;
import io.mcsoft.aop.framework.copied.AccountPO;
import io.mcsoft.aop.framework.copied.Permission;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Menu权限Handler
 * Created by MC on 2019/2/11.
 *
 * @author MC
 * @date 2019/2/11
 */
@Component
public class MenuHandler extends AbstractAnnotationValidateHandler {
    /**
     * KEY_FOR_AUTH的字符串值映射到Module Id的Map
     */
    private Map<String, Integer> keyToIdMap;

    public MenuHandler() {
        super(RequireMenuPermission.class);
    }

    @Override
    public boolean validate(AccountPO account, List<Permission> permissionList, Annotation annotation) {
        boolean result = false;

        RequireMenuPermission permissionAnnotation = (RequireMenuPermission) annotation;
        MenuKeys[] keys = permissionAnnotation.value();
        String[] keysString = permissionAnnotation.keysString();
        //权限KEY对应ID的集合
        HashSet<Integer> permissionIdSet = new HashSet<>();

        for (MenuKeys key : keys) {
            permissionIdSet.add(keyToIdMap.get(key.name()));
        }
        for (String key : keysString) {
            permissionIdSet.add(keyToIdMap.get(key));
        }

        //用户的权限和注解里的权限KEY做匹配删除，最后如果权限KEY集合为空，则表示用户具有调用方法的所有权限
        for (Permission permission : permissionList) {
            permissionIdSet.remove(permission.getMenuId());
            if (permissionIdSet.isEmpty()) {
                result = true;
                break;
            }
        }

        return result;
    }

    @PostConstruct
    private void beanInit() {
        keyToIdMap = new HashMap<>();
        //省略key映射代码
    }
}