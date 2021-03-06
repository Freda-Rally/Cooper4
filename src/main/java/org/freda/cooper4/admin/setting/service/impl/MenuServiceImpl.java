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
import java.util.List;

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

        pDto.put("leaf" , 0);

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
        if (((Integer)super.getDao().queryForObject("admin.setting.Menu.isLastSub",pDto)) <= 1)
        {
            pDto.put("leaf",1);

            super.getDao().update("admin.setting.Menu.editMenuLeaf",pDto);
        }
        super.getDao().delete("admin.setting.Menu.delete",pDto);

        authority4OrganizationService.delete4MenuRm(pDto);

    }

    /**
     * 树
     *
     * @param pDto
     * @return
     */
    @Override
    public List<?> menuTreeInit(Dto pDto)
    {
        return super.getDao().queryForList("admin.setting.Menu.menuTreeInit",pDto);
    }
}
