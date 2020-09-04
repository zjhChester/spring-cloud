package xyz.zjhwork.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.zjhwork.resmodel.ResponseModel;
import xyz.zjhwork.service.FileService;

/**
 * Describe:
 * Author:zjhChester
 * Date:
 */
@RestController
@Api(tags = "FileOutPut Interfaces")
public class FileController {
    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @ApiOperation(value = "文件导出接口",notes = "需要传入id集合，用于导出需要的文章，前端建议做下拉复选框/搜索复选框")
    @GetMapping("file/fileoutput")
    public ResponseModel fileOutPut(Integer []ids){
        return fileService.fileOutPut(ids);
    }

}
