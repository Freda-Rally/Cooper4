package org.freda.cooper4.admin.setting.service;

import org.freda.cooper4.framework.datastructure.Dto;

import java.util.List;

/**
 *
 * 部门树.
 *
 * Created by rally on 16/5/9.
 */
public interface DeptTreeService
{
    /**
     * 部门树.
     * @param pDto
     * @return
     */
    public abstract List treeInit(Dto pDto);
}
