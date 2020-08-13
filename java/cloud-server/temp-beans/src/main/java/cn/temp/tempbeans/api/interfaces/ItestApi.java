package cn.temp.tempbeans.api.interfaces;

import cn.temp.tempbeans.api.dto.TestReqBodyDTO;
import cn.temp.tempbeans.api.dto.ValidDTO;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
public interface ItestApi {

    @PostMapping("/1")
    @ApiOperation(value = "@RequestBody测试", notes = "api实现测试")
    R<@Valid TestReqBodyDTO> test(
            @RequestHeader("api")String api ,
            @RequestBody @Valid TestReqBodyDTO testReqBodyDTO);
}
