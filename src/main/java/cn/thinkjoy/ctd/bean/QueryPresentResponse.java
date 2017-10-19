package cn.thinkjoy.ctd.bean;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

public class QueryPresentResponse extends HttpResponse
{
    private String code;

    private String description;

    private QueryPresentResult result;

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

    public QueryPresentResult getResult() {
        return result;
    }

    public void setResult(QueryPresentResult result) {
        this.result = result;
    }

    @Override
    public QueryPresentResponse parse() {
        if (!StringUtils.isEmpty(getRspContent()))
        {
            //JSONObject json = getJsonModelFromByte(getRspContent().getBytes());
            JSONObject json = JSONObject.parseObject(getRspContent());
            this.code = json.containsKey("code") ? json.getString("code") : StringUtils.EMPTY;
            this.description = json.containsKey("description") ? json.getString("description") : StringUtils.EMPTY;
            if (json.containsKey("result"))
            {
                this.result = json.getObject("result", QueryPresentResult.class);
            }
        }
        return this;
    }
}
