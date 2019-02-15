package io.mcsoft.aop.framework.annotation.permission.permissions;

/**
 * 菜单关键字
 * 对应数据库menu表中keyword_for_auth字段的enum
 * Created by MC on 2019/1/25.
 *
 * @author MC
 * @date 2019/1/25
 */
public enum MenuKeys {
    /**
     * 系统首页
     */
    SYSTEM_HOMEPAGE,
    /**
     * 账户设置
     */
    ACCOUNT_SETTING,
    /**
     * 企业信息
     */
    ENTERPRISE_INFO,
    /**
     * 新车投保
     */
    NEW_VEHICLE_INSURING,
    /**
     * 保险公司
     */
    INSURER,
    /**
     * 折扣率
     */
    FLOATING_RATE,
    /**
     * 订单管理
     */
    ORDER_MANAGEMENT,
    /**
     * 对账管理
     */
    RECONCILIATION_MANAGEMENT,
    /**
     * 企业管理
     */
    ENTERPRISE_MANAGEMENT,
    /**
     * 机构管理
     */
    ORGANIZATION_MANAGEMENT,
    /**
     * 账号管理
     */
    ACCOUNT_MANAGEMENT;
}
