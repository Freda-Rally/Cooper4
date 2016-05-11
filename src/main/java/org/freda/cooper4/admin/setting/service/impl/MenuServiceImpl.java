package org.freda.cooper4.admin.setting.service.impl;

import org.freda.cooper4.admin.setting.service.Authority4OrganizationService;
import org.freda.cooper4.admin.setting.service.MenuManagerService;
import org.freda.cooper4.common.generator.dbid.Cooper4DBIdHelper;
import org.freda.cooper4.common.support.web.Cooper4AdminBaseServiceImpl;
import org.freda.cooper4.framework.datastructure.Dto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *
 * 菜单管理.
 *
 * Created by rally on 16/5/11.
 */
@Service
public class MenuServiceImpl extends Cooper4AdminBaseServiceImpl implements MenuManagerService
{
    @Resource
    Authority4OrganizationService authority4OrganizationService;

    /**
     * 添加菜单.
     *
     * @param pDto
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Dto pDto)
    {
        pDto.put("menuId", Cooper4DBIdHelper.getDbTableID("MENUID"));

        super.getDao().insert("admin.setting.Menu.add",pDto);

        super.getDao().update("admin.setting.Menu.editMenuLeaf",pDto);
    }

    /**
     * 修改菜单信息.
     *
     * @param pDto
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void edit(Dto pDto)
    {
        super.getDao().update("admin.setting.Menu.edit",pDto);
    }

    /**
     * 删除菜单.
     *
     * @param pDto
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Dto pDto)
    {
        String[] ids = pDto.getAsString("ids").split(",");

        for (String id : ids)
        {
            pDto.put("menuId",id);

            super.getDao().delete("admin.setting.Menu.delete",pDto);

            authority4OrganizationService.delete4MenuRm(pDto);
        }
    }
}
