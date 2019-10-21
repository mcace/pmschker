package io.mcsoft.aop.framework;

/**
 * Created by MC on 2019/2/11.
 *
 * @author MC
 * @date 2019/2/11
 */
public class Doc {
    /**
     * 权限校验扩展开发说明：
     * <p>
     * 一个方法、类的权限控制主要通过注解来实现，每种注解都有一个专属的校验处理里，在包validator/permission/handler中来实现
     * <p>
     * 举例：
     * 比如当前版本使用KEYWORD_FOR_AUTH来关联数据库中的权限项，因此我设计的RequireFunctionPermission/RequireMenuPermission/
     * RequireModulePermission三个注解都分别使用了对应的enum类XXXKeys，这些enum的内容就是KEYWORD_FOR_AUTH。
     * 那么我如何处理这些KEY，首先我定义了三个Handler：FunctionHandler/MenuHandler/ModuleHandler，分别处理这三种注解，然后在具体的
     * Handler中来处理类。
     * <p>
     * 所以未来有新的权限校验方式时，首先在包annotation/permission/permissions中定义好注解（该注解必须被打上注解@RequirePermission）,
     * 然后在包validator/permission/handler中来定义好相关注解的处理实现类（该类必须继承自AbstractAnnotationValidateHandler或实现
     * AnnotationValidateHandler接口，并且能被Spring创建bean），然后再在需要增加权限校验的地方打上注解即可，PermissionValidator会
     * 自动注入AnnotationValidateHandler接口实现类。
     */
    public void extend() {
    }

    /**
     * 权限校验使用说明：
     * <p>
     * 实现原理：主要使用注解+拦截器的方式来实现校验。
     * 使用方式：在需要校验的Controller类或方法上标记注解，即可实现权限校验
     * <p>
     * 注解目前有校验登陆和校验角色权限两种，在{@code io.mcsoft.aop.framework.annotation}包下，login包内是校验登陆的注解，
     * permission包内是校验角色权限的注解。同时，校验角色权限的注解已经和校验登陆的注解相结合，所以如果使用了校验角色权限的注解，
     * 则不需要重复使用RequireLogin注解。
     * <p>
     * 举例（登陆校验）：
     * AccountController内的所有方法需要用户登陆，则在类上标记@RequireLogin，此时用户通过spring mvc调用该Controller下方法
     * 时，如果没有登陆则会返回未登录的错误信息。
     * <p>
     * 校验角色权限的注解还需要将对应的Key数组设置到注解里，设置方法有两种，一种是通过注解默认的value，使用对应的enum类内的实例，
     * 比如@RequireMenuPermission可以设置MenuKeys中的实例；另一种是注解的keysString参数，该参数是String数组类型，可以跳过enum
     * 直接手动设置String数组
     * <p>
     * 举例（权限校验）：
     * AccountController内的所有方法都需要调用的用户具有Menu权限表中Key为'ACCOUNT_MANAGEMENT'的权限，那么就可以在类上标记
     * {@code @RequireMenuPermission({MenuKeys.ACCOUNT_MANAGEMENT})}，此时该类下所有方法在通过spring mvc调用时都会校验用户
     * 是否具有该权限。
     */
    public void use() {
    }
}
