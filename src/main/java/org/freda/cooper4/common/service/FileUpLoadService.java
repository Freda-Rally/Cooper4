package org.freda.cooper4.common.service;

import org.freda.cooper4.framework.datastructure.Dto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 文件上传服务
 *
 * Created by rally on 16/5/14.
 */
public interface FileUpLoadService
{
    /**
     * 上传文件
     *
     * @param file
     * @param pDto
     */
    public abstract void upload(MultipartFile file,Dto pDto)throws IOException;

    /**
     * 获取批量上传文件明细
     *
     * @param fileSequence
     * @return
     */
    public abstract List<Dto> listUploadFiles(String fileSequence);

    /**
     * 获取单个
     *
     * @param fileId
     * @return
     */
    public abstract Dto getUploadFile(String fileId);
}
