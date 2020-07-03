package cn.temp.tempbeans.controller;

import cn.temp.tempbeans.api.dto.ValidDTO;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Valid注解测试接口
 */
@RestController
@Api(tags = "@Valid注解测试接口"
)
public class ValidTestController {
    @ApiOperation(value = "validGet测试", notes = "Get测试")
    @RequestMapping(value = "/testValid",method = RequestMethod.GET)
    public R<ValidDTO> testValid(@Valid ValidDTO validDTO){
        return R.ok(validDTO);
    }

}
