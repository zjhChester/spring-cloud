package xyz.zjhwork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import xyz.zjhwork.controller.HisController;
import xyz.zjhwork.dao.ExceptionDao;
import xyz.zjhwork.dao.FavDao;
import xyz.zjhwork.dao.HisDao;
import xyz.zjhwork.entity.Exception;
import xyz.zjhwork.resmodel.ResponseModel;
import xyz.zjhwork.service.FileService;
import xyz.zjhwork.utils.DateUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Describe: 内容文件导出业务实现
 * Author: zjhChester
 * Date:2020-09-04 15:38
 */
@Service
public class FileServiceImpl implements FileService {
    private final ExceptionDao exceptionDao;
    private final HisDao hisDao;
    private final FavDao favDao;

    @Autowired
    public FileServiceImpl(ExceptionDao exceptionDao, HisDao hisDao, FavDao favDao) {
        this.exceptionDao = exceptionDao;
        this.hisDao = hisDao;
        this.favDao = favDao;
    }

    /**
     * 导出指定id数组的异常文章
     * @param ids 传入的id数组
     * @return 统一返回集
     */
    @Override
    public ResponseModel fileOutPut(Integer[] ids) {
        //读取配置地址
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new ClassPathResource("application.yml"));
        Properties properties = yamlPropertiesFactoryBean.getObject();

        //ids的拼接
        Arrays.stream(ids).map(integer -> integer.toString()).collect(Collectors.toList());
        List<String> collect = Arrays.stream(ids).map(Object::toString).collect(Collectors.toList());
        String join = String.join(",",collect);
        List<Exception> listByIds = exceptionDao.findListByIds(join);
        if(listByIds==null || listByIds.size()==0||properties==null){
            return ResponseModel.failResModel(0,"没有可导出的文章");
        }else{
            for (Exception e : listByIds) {
            String dirPath = properties.get("config."+properties.getProperty("tomcat.current-system")+".doc-base").toString()+"/markDownDirectory/"+DateUtils.TODAY+"/";
            File dir = new File(dirPath);
                dir.setLastModified(System.currentTimeMillis());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = dirPath+DateUtils.formatYearMonthDay(e.getCreateTime())+"-"+e.getTitle()+".md";
            File file = new File(fileName);
            if(!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            OutputStreamWriter os=null;
            try {
                os = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
                StringBuilder markDownContent = new StringBuilder("---");
                markDownContent.append("\nlayout:     post\nauthor:     ").append(e.getAuthor()).append("\nheader-img: img/home-bg-o.jpg\ncatalog: true\ntags:\n    - ").append(e.getType()).append("\n---\n");
                markDownContent.append(e.getContent());
                os.write(markDownContent.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }finally {
                try {
                    if(os!=null)
                        os.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            }
        return ResponseModel.successResModel(1,"SUCCESS",listByIds.toArray());
        }
    }
}
