package cn.mendao.resp;

import java.util.List;

/**
 * Created by warden on 19/1/15.
 */
public class MendaoTablesSetResp {

    private String tableName;
    private String tableColor;
    private List<TableTestMsg> tableTestMsg;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableColor() {
        return tableColor;
    }

    public void setTableColor(String tableColor) {
        this.tableColor = tableColor;
    }

    public List<TableTestMsg> getTableTestMsg() {
        return tableTestMsg;
    }

    public void setTableTestMsg(List<TableTestMsg> tableTestMsg) {
        this.tableTestMsg = tableTestMsg;
    }
}
