package org.freda.cooper4.common.service.impl;

import org.freda.cooper4.common.generator.dbid.Cooper4DBIdHelper;
import org.freda.cooper4.common.service.FileUpLoadService;
import org.freda.cooper4.common.support.web.Cooper4AdminBaseServiceImpl;
import org.freda.cooper4.framework.datastructure.Dto;
import org.freda.cooper4.framework.utils.FredaUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 文件上传实现
 *
 * Created by rally on 16/5/14.
 */
@Service
public class FileUpLoadServiceImpl extends Cooper4AdminBaseServiceImpl implements FileUpLoadService
{
    /**
     * 上传文件
     *
     * @param file
     * @param pDto
     */

    private static final String FILE_REAL_NAME = "fileRealName";

    private static final String FILE_TYPE = "fileType";

    private static final String FILE_SYSTEM_NAME = "fileSystemName";

    private static final String FILE_PATH = "filePath";

    private static final String FILE_SEQUENCE = "fileSequence";

    private static final String FILE_SORT = "fileSortNo";

    @Value("${uploadFileRootPath}")
    private String fileRootPath;

    @Value("${filePathUrlHead}")
    private String filePathUrlHead;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void upload(MultipartFile file, Dto pDto) throws IOException
    {
        pDto.put(FILE_REAL_NAME,file.getOriginalFilename());

        pDto.put(FILE_TYPE,file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),file.getOriginalFilename().length()));

        pDto.put(FILE_SYSTEM_NAME, System.nanoTime() + pDto.getAsString(FILE_TYPE));

        pDto.put(FILE_PATH,filePathUrlHead + FredaUtils.getCurDate() + "/" );

        if (FredaUtils.isEmpty(pDto.getAsString(FILE_SEQUENCE)))//设置批次
        {
            pDto.put(FILE_SEQUENCE, Cooper4DBIdHelper.getDbTableID("FILESEQUENCE"));
        }
        //创建真实上传目录
        File fileRealPath = new File(fileRootPath + FredaUtils.getCurDate());

        if (!fileRealPath.exists())
        {
            fileRealPath.mkdirs();
        }
        File outFile = new File(fileRootPath + FredaUtils.getCurDate() + "/" + pDto.getAsString(FILE_SYSTEM_NAME));

        FileCopyUtils.copy(file.getBytes(),outFile);//上传至指定目录

        pDto.put("fileId",Cooper4DBIdHelper.getDbTableID("FILEID"));

        if (FredaUtils.isEmpty(pDto.getAsString(FILE_SORT)))//如果不指定排序号..则统一设置为0
        {
            pDto.put(FILE_SORT,0);
        }
        super.getDao().insert("common.UploadFile.add",pDto);//保存上传文件明细
    }

    /**
     * 获取批量上传文件明细
     *
     * @param fileSequence
     * @return
     */
    @Override
    public List<Dto> listUploadFiles(String fileSequence)
    {
        return super.getDao().queryForList("common.UploadFile.list",fileSequence);
    }

    /**
     * 获取单个
     *
     * @param fileId
     * @return
     */
    @Override
    public Dto getUploadFile(String fileId)
    {
        return (Dto)super.getDao().queryForObject("common.UploadFile.load",fileId);
    }
}
