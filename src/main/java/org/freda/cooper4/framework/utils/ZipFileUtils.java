package org.freda.cooper4.framework.utils;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.*;

/**
 *
 * 文件压缩工具类
 *
 * Created by rally on 16/4/21.
 */
public class ZipFileUtils
{
    /**
     *
     * 批量压缩
     *
     * @param files
     * @param zipFilePath
     */
    public static void compressFiles2Zip(File[] files,String zipFilePath)
    {
        if (files != null && files.length > 0)
        {
            if (isEndsWithZip(zipFilePath))
            {
                ZipArchiveOutputStream zaos = null;

                File zipFile = new File(zipFilePath);

                InputStream is = null;

                try
                {
                    zaos = new ZipArchiveOutputStream(zipFile);

                    zaos.setUseZip64(Zip64Mode.AsNeeded);

                    for (File file : files)
                    {
                        if (file != null)
                        {
                            ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file,file.getName());

                            zaos.putArchiveEntry(zipArchiveEntry);

                            is = new FileInputStream(file);

                            byte[] buffer = new byte[1024 * 5];

                            int len = -1;

                            while((len = is.read(buffer)) != -1)
                            {
                                //把缓冲区的字节写入到ZipArchiveEntry
                                zaos.write(buffer, 0, len);
                            }
                            //Writes all necessary data for this entry.
                            zaos.closeArchiveEntry();

                        }
                    }
                    zaos.finish();
                }
                catch (Exception e)
                {
                    throw new  RuntimeException(e);
                }
                finally
                {
                    try
                    {
                        if (is != null)
                            is.close();
                        if (zaos != null)
                            zaos.close();
                    }
                    catch (IOException e)
                    {
                        throw new RuntimeException(e);
                    }

                }
            }
        }
    }

    /**
     *
     * 把zip文件解压到指定的文件夹
     *
     * @param zipFilePath zip文件路径, 如 "D:/test/aa.zip"
     * @param saveFileDir 解压后的文件存放路径, 如"D:/test/"
     */
    public static void decompressZip(String zipFilePath,String saveFileDir)
    {
        if (isEndsWithZip(zipFilePath))
        {
            File file = new File(saveFileDir);

            if (!file.exists())
            {
                file.mkdir();
            }
            InputStream is = null;

            OutputStream os = null;
            //can read Zip archives
            ZipArchiveInputStream zais = null;

            try
            {
                is = new FileInputStream(file);

                zais = new ZipArchiveInputStream(is);

                ArchiveEntry archiveEntry = null;
                //把zip包中的每个文件读取出来
                //然后把文件写到指定的文件夹
                while((archiveEntry = zais.getNextEntry()) != null)
                {
                    //获取文件名
                    String entryFileName = archiveEntry.getName();
                    //构造解压出来的文件存放路径
                    String entryFilePath = saveFileDir + entryFileName;

                    byte[] content = new byte[(int) archiveEntry.getSize()];

                    zais.read(content);

                    File entryFile = new File(entryFilePath);

                    os = new FileOutputStream(entryFile);

                    os.write(content);
                }
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
            finally
            {
                try
                {
                    if (is != null)
                        is.close();
                    if (os != null)
                        os.close();
                    if (zais != null)
                        zais.close();
                }
                catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
        }
    }


    /**
     *
     * 判断文件名是否以.zip为后缀
     *
     * @param fileName 需要判断的文件名
     * @return 是zip文件返回true,否则返回false
     */
    private static boolean isEndsWithZip(String fileName)
    {
        boolean flag = false;

        if(fileName != null && !"".equals(fileName.trim()))
        {
            if(fileName.endsWith(".ZIP")||fileName.endsWith(".zip"))
            {
                flag = true;
            }
        }
        return flag;
    }
}
