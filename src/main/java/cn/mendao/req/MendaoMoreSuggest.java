package cn.mendao.req;

import java.util.List;

/**
 * Created by warden on 19/1/16.
 */
public class MendaoMoreSuggest {

    private String suggestCode;
    private List<MendaoSuggestArray> suggestArray;

    public String getSuggestCode() {
        return suggestCode;
    }

    public void setSuggestCode(String suggestCode) {
        this.suggestCode = suggestCode;
    }

    public List<MendaoSuggestArray> getSuggestArray() {
        return suggestArray;
    }

    public void setSuggestArray(List<MendaoSuggestArray> suggestArray) {
        this.suggestArray = suggestArray;
    }
}
