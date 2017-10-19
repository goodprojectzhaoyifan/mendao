package cn.thinkjoy.ctd.bean;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;

public class PresentDataResponse extends HttpResponse
{
    private String code;

    private String description;

    private PresentDataResult result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PresentDataResult getResult() {
        return result;
    }

    public void setResult(PresentDataResult result) {
        this.result = result;
    }

    @Override
    public PresentDataResponse parse() {
        if (!StringUtils.isEmpty(this.getRspContent()))
        {
            //JSONObject json = getJsonModelFromByte(getRspContent().getBytes());
            JSONObject json = JSONObject.parseObject(getRspContent());
            this.code = json.containsKey("code") ? json.getString("code") : StringUtils.EMPTY;
            this.description = json.containsKey("description") ? json.getString("description") : StringUtils.EMPTY;
            if (json.containsKey("result"))
            {
                this.result = json.getObject("result", PresentDataResult.class);
            }
        }
        return this;
    }
}
