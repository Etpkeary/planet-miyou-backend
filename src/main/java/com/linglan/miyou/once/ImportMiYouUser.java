package com.linglan.miyou.once;

import com.alibaba.excel.EasyExcel;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 导入星球用户到数据库
 * @author LingLan
 */
public class ImportMiYouUser {
    public static void main(String[] args) {
        String fileName = "C:\\UserStockpile\\CodeFiles\\linglan\\密友-匹配伙伴系统\\miyou-center-backend-master\\src\\main\\resources\\testExcel.xlsx";
        readByListener(fileName);
        synchronousRead(fileName);
    }

    private static void synchronousRead(String fileName) {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        List<MiYouTableUserInfo> userInfoList =
                EasyExcel.read(fileName).head(MiYouTableUserInfo.class).sheet().doReadSync();
        System.out.println("总数 = " + userInfoList.size());

        Map<String, List<MiYouTableUserInfo>> listMap =
                userInfoList.stream()
                        .filter(userInfo -> StringUtils.isNotEmpty(userInfo.getUsername()))
                        .collect(Collectors.groupingBy(MiYouTableUserInfo::getUsername));

        for (Map.Entry<String, List<MiYouTableUserInfo>> stringListEntry : listMap.entrySet()) {
            if(stringListEntry.getValue().size() > 1){
                System.out.println("username" + stringListEntry.getKey());
                System.out.println("=====================");
            }
        }
        System.out.println("不重复昵称数 = " + listMap.keySet().size());
    }

    private static void readByListener(String fileName) {
        EasyExcel.read(fileName, MiYouTableUserInfo.class, new TableListener()).sheet().doRead();
    }
    
}