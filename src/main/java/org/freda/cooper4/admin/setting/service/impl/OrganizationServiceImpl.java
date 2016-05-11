package org.freda.cooper4.admin.setting.service.impl;

import org.freda.cooper4.admin.setting.service.Authority4OrganizationService;
import org.freda.cooper4.admin.setting.service.DeptTreeService;
import org.freda.cooper4.admin.setting.service.OrganizationService;
import org.freda.cooper4.common.generator.dbid.Cooper4DBIdHelper;
import org.freda.cooper4.common.support.web.Cooper4AdminBaseServiceImpl;
import org.freda.cooper4.framework.datastructure.Dto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 人员.部门.角色.添加.删除.修改.
 *
 * Created by rally on 16/5/8.
 */
@Service
public class OrganizationServiceImpl extends Cooper4AdminBaseServiceImpl implements OrganizationService,DeptTreeService
{
    @Resource
    private Authority4OrganizationService authority4OrganizationService;
    /**
     * 人员新增
     * @param pDto
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean userAdd(Dto pDto)
    {
        pDto.put("userId", Cooper4DBIdHelper.getDbTableID("USERID"));

        if (((Integer)super.getDao().queryForObject("admin.setting.Organization.isAccountUsed",pDto)) == 0)
        {
            super.getDao().insert("admin.setting.Organization.userAdd",pDto);

            super.getDao().insert("admin.setting.Organization.",pDto);

            return true;
        }
        return false;
    }

    /**
     * 人员信息修改.
     * @param pDto
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void userEdit(Dto pDto)
    {
        super.getDao().update("admin.setting.Organization.userEdit",pDto);

        super.getDao().update("admin.setting.Organization.userInfoEdit",pDto);
    }

    /**
     * 人员删除.
     * @param pDto
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void userDelete(Dto pDto)
    {
        String[] ids = pDto.getAsString("ids").split(",");

        for (String id : ids)
        {
            pDto.put("userId",id);

            super.getDao().delete("admin.setting.Organization.userDelete",pDto);

            super.getDao().delete("admin.setting.Organization.userInfoDelete",pDto);

            authority4OrganizationService.delete4UserRm(pDto);
        }
    }

    /**
     * 部门新增.
     * @param pDto
     */
    @Override
    public void deptAdd(Dto pDto)
    {
        pDto.put("deptId",Cooper4DBIdHelper.getDbTableID("DEPTID"));

        pDto.put("leaf",0);

        super.getDao().update("admin.setting.Organization.deptLeafEdit",pDto);

        super.getDao().insert("admin.setting.Organization.deptAdd",pDto);
    }

    /**
     * 部门信息修改.
     * @param pDto
     */
    @Override
    public void deptEdit(Dto pDto)
    {
        super.getDao().update("admin.setting.Organization.deptEdit",pDto);
    }

    /**
     * 部门删除.
     * @param pDto
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deptDelete(Dto pDto)
    {
        List userList = super.getDao().queryForList("admin.setting.Organization.getUsersByDept",pDto);

        if (((Integer)super.getDao().queryForObject("admin.setting.Organization.getDeptSubCount",pDto)) > 1)
        {
            pDto.put("leaf",1);

            super.getDao().update("admin.setting.Organization.deptLeafEdit",pDto);
        }
        super.getDao().delete("admin.setting.Organization.deptDelete",pDto);//删除底级部门.

        super.getDao().delete("admin.setting.Organization.userDeleteByDept",pDto);//删除部门下人员.

        for (Object obj : userList)
        {
            Dto userDto = (Dto)obj;

            pDto.put("userId",userDto.getAsString("userId"));

            authority4OrganizationService.delete4UserRm(pDto);//删除每个用户的权限绑定.
        }
    }

    /**
     * 角色新增.
     * @param pDto
     */
    @Override
    public void roleAdd(Dto pDto)
    {
        pDto.put("roleId",Cooper4DBIdHelper.getDbTableID("ROLEID"));

        super.getDao().insert("admin.setting.Organization.roleAdd",pDto);
    }

    /**
     * 角色信息修改.
     * @param pDto
     */
    @Override
    public void roleEdit(Dto pDto)
    {
        super.getDao().update("admin.setting.Organization.roleEdit",pDto);
    }

    /**
     * 角色删除.
     * @param pDto
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void roleDelete(Dto pDto)
    {
        String[] ids = pDto.getAsString("ids").split(",");

        for (String id : ids)
        {
            pDto.put("roleId",id);

            super.getDao().delete("admin.setting.Organization.roleDelete",pDto);

            authority4OrganizationService.delete4RoleRm(pDto);
        }
    }

    /**
     * 部门树.
     *
     * @param pDto
     * @return
     */
    @Override
    public List treeInit(Dto pDto)
    {
        return super.getDao().queryForList("admin.setting.Organization.deptTreeInit",pDto);
    }
}
