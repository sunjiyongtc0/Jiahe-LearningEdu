package sjy.jiahe.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;



public class ReadExcel {
    private static Map<String,String> reg=new HashMap<String,String>();
    private static  String[] s={"rtt_score","rate_score","rtt","rate"};
    static {
        reg.put("北京","110000");
        reg.put("天津","120000");
        reg.put("河北","130000");
        reg.put("山西","140000");
        reg.put("内蒙古","150000");
        reg.put("辽宁","210000");
        reg.put("吉林","220000");
        reg.put("黑龙江","230000");
        reg.put("上海","310000");
        reg.put("江苏","320000");
        reg.put("浙江","330000");
        reg.put("安徽","340000");
        reg.put("福建","350000");
        reg.put("江西","360000");
        reg.put("山东","370000");
        reg.put("河南","410000");
        reg.put("湖北","420000");
        reg.put("湖南","430000");
        reg.put("广东","440000");
        reg.put("广西","450000");
        reg.put("海南","460000");
        reg.put("重庆","500000");
        reg.put("四川","510000");
        reg.put("贵州","520000");
        reg.put("云南","530000");
        reg.put("西藏","540000");
        reg.put("陕西","610000");
        reg.put("甘肃","620000");
        reg.put("青海","630000");
        reg.put("宁夏","640000");
        reg.put("新疆","650000");
//        reg.put("台湾","710000");
    }
//    private static   String sql0="insert into t_t_monthly_quotascore(type_id,parent_code,recode_code,rtt,rate,rtt_score,rate_score,time_info,year_info，month_info) " +
//            "values(0,?,?.?,?,?,?,?,?,? )";
    public static void main(String[] args) {
        // 设定Excel文件所在路径
        String excelFileName = "C:/Users/Administrator/Desktop/aaaa.xls";
        ReadExcel obj = new ReadExcel();
        File file = new File(excelFileName);
        List excelList = obj.readExcel(file);
        System.out.println("list中的数据打印出来");
        for (int i = 0; i < excelList.size(); i++) {
            Map<String,String> m=(Map)excelList.get(i);
//            String sql0="insert into t_t_monthly_quotascore (type_id, parent_code, recode_code, rtt, rate, rtt_score, rate_score,  time_info, year_info, month_info) " +
//                    "values (0,'#','#','#','#','#','#','#','#','#' );";
//            sql0=sql0.replaceFirst("#",m.get("recode_code").trim());
//            sql0=sql0.replaceFirst("#",m.get("recode_code").trim());
//            sql0= sql0.replaceFirst("#",m.get("rtt").trim());
//            sql0=sql0.replaceFirst("#",m.get("rate").trim());
//            sql0= sql0.replaceFirst("#",m.get("rtt_score").trim());
//            sql0=sql0.replaceFirst("#",m.get("rate_score").trim());
//            sql0= sql0.replaceFirst("#","201912");
//            sql0=sql0.replaceFirst("#","2019");
//            sql0= sql0.replaceFirst("#","12");
            String sql0="update t_t_monthly_quotascore set rtt='#' ,rate='#',rtt_score='#',rate_score='#' where recode_code='#' and time_info='#' and year_info='#' and month_info='#' ;";
            sql0= sql0.replaceFirst("#",m.get("rtt").trim());
            sql0=sql0.replaceFirst("#",m.get("rate").trim());
            sql0= sql0.replaceFirst("#",m.get("rtt_score").trim());
            sql0=sql0.replaceFirst("#",m.get("rate_score").trim());
            sql0=sql0.replaceFirst("#",m.get("recode_code").trim());
            sql0= sql0.replaceFirst("#","201912");
            sql0=sql0.replaceFirst("#","2019");
            sql0= sql0.replaceFirst("#","12");
            System.out.println(sql0);
        }

    }
    // 去读Excel的方法readExcel，该方法的入口参数为一个File对象
    public List readExcel(File file) {
        List<Map> list=new ArrayList<Map>();

        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for(String key:reg.keySet()) { //keySet获取map集合key的集合  然后在遍历key即可
                Map<String,String> write=new HashMap<String,String>();
                String value = reg.get(key).toString();
                write.put("recode_code",value);
                for (int index = 0; index < sheet_size; index++) {
//                    List<List> outerList = new ArrayList<List>();
                    // 每个页签创建一个Sheet对象
                    Sheet sheet = wb.getSheet(index);
                    // sheet.getRows()返回该页的总行数
                    for (int i = 0; i < sheet.getRows(); i++) {
                        List innerList = new ArrayList();
                        // sheet.getColumns()返回该页的总列数
                        for (int j = 0; j < sheet.getColumns(); j++) {
                            String cellinfo = sheet.getCell(j, i).getContents();
                            if (cellinfo.isEmpty()) {
                                continue;
                            }
                            innerList.add(cellinfo);
//                        System.out.print(cellinfo);
                        }
                        if((innerList.get(0)+"").equals(key)){
                            write.put(s[index],innerList.get(1)+"");
                        }
//                        outerList.add(i, innerList);
                    }
                }
                list.add(write);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
