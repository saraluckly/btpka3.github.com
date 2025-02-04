/*
 * This file is generated by jOOQ.
*/
package test.generated.tables;


import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.jooq.types.ULong;

import test.generated.Indexes;
import test.generated.Keys;
import test.generated.SmetaApp;
import test.generated.tables.records.UfRiskTargetRecord;


/**
 * 风险对象
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UfRiskTarget extends TableImpl<UfRiskTargetRecord> {

    private static final long serialVersionUID = -1617958133;

    /**
     * The reference instance of <code>SMETA_APP.uf_risk_target</code>
     */
    public static final UfRiskTarget UF_RISK_TARGET = new UfRiskTarget();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UfRiskTargetRecord> getRecordType() {
        return UfRiskTargetRecord.class;
    }

    /**
     * The column <code>SMETA_APP.uf_risk_target.id</code>. 主键
     */
    public final TableField<UfRiskTargetRecord, ULong> ID = createField("id", org.jooq.impl.SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "主键");

    /**
     * The column <code>SMETA_APP.uf_risk_target.gmt_create</code>. 创建时间
     */
    public final TableField<UfRiskTargetRecord, Timestamp> GMT_CREATE = createField("gmt_create", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "创建时间");

    /**
     * The column <code>SMETA_APP.uf_risk_target.gmt_modified</code>. 修改时间
     */
    public final TableField<UfRiskTargetRecord, Timestamp> GMT_MODIFIED = createField("gmt_modified", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "修改时间");

    /**
     * The column <code>SMETA_APP.uf_risk_target.tenant_code</code>. 租户
     */
    public final TableField<UfRiskTargetRecord, String> TENANT_CODE = createField("tenant_code", org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "租户");

    /**
     * The column <code>SMETA_APP.uf_risk_target.code</code>. 代码
     */
    public final TableField<UfRiskTargetRecord, String> CODE = createField("code", org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "代码");

    /**
     * The column <code>SMETA_APP.uf_risk_target.name</code>. 名称
     */
    public final TableField<UfRiskTargetRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "名称");

    /**
     * The column <code>SMETA_APP.uf_risk_target.extra</code>. 附加信息
     */
    public final TableField<UfRiskTargetRecord, String> EXTRA = createField("extra", org.jooq.impl.SQLDataType.CLOB, this, "附加信息");

    /**
     * The column <code>SMETA_APP.uf_risk_target.description</code>. 描述
     */
    public final TableField<UfRiskTargetRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.CLOB, this, "描述");

    /**
     * The column <code>SMETA_APP.uf_risk_target.entity</code>. 对应的实体
     */
    public final TableField<UfRiskTargetRecord, String> ENTITY = createField("entity", org.jooq.impl.SQLDataType.VARCHAR(50), this, "对应的实体");

    /**
     * The column <code>SMETA_APP.uf_risk_target.complete_code</code>. 实全代码
     */
    public final TableField<UfRiskTargetRecord, String> COMPLETE_CODE = createField("complete_code", org.jooq.impl.SQLDataType.VARCHAR(250), this, "实全代码");

    /**
     * The column <code>SMETA_APP.uf_risk_target.operator</code>. 操作人
     */
    public final TableField<UfRiskTargetRecord, ULong> OPERATOR = createField("operator", org.jooq.impl.SQLDataType.BIGINTUNSIGNED.nullable(false), this, "操作人");

    /**
     * Create a <code>SMETA_APP.uf_risk_target</code> table reference
     */
    public UfRiskTarget() {
        this(DSL.name("uf_risk_target"), null);
    }

    /**
     * Create an aliased <code>SMETA_APP.uf_risk_target</code> table reference
     */
    public UfRiskTarget(String alias) {
        this(DSL.name(alias), UF_RISK_TARGET);
    }

    /**
     * Create an aliased <code>SMETA_APP.uf_risk_target</code> table reference
     */
    public UfRiskTarget(Name alias) {
        this(alias, UF_RISK_TARGET);
    }

    private UfRiskTarget(Name alias, Table<UfRiskTargetRecord> aliased) {
        this(alias, aliased, null);
    }

    private UfRiskTarget(Name alias, Table<UfRiskTargetRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "风险对象");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return SmetaApp.SMETA_APP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.UF_RISK_TARGET_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<UfRiskTargetRecord, ULong> getIdentity() {
        return Keys.IDENTITY_UF_RISK_TARGET;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<UfRiskTargetRecord> getPrimaryKey() {
        return Keys.KEY_UF_RISK_TARGET_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<UfRiskTargetRecord>> getKeys() {
        return Arrays.<UniqueKey<UfRiskTargetRecord>>asList(Keys.KEY_UF_RISK_TARGET_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UfRiskTarget as(String alias) {
        return new UfRiskTarget(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UfRiskTarget as(Name alias) {
        return new UfRiskTarget(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public UfRiskTarget rename(String name) {
        return new UfRiskTarget(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public UfRiskTarget rename(Name name) {
        return new UfRiskTarget(name, null);
    }
}
