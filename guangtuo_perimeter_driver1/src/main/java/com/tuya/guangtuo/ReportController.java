package com.tuya.guangtuo;

import com.tuya.could.tedge.sdk.common.model.DataType;
import com.tuya.could.tedge.sdk.common.model.DpData;
import com.tuya.could.tedge.sdk.domain.service.iface.DpModelApi;
import com.tuya.driver.DpDriverImpl;
import com.tuya.driver.handler.GtDpData;
import com.tuya.guangtuo.pojo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class ReportController {
    @Autowired
    private DpDriverImpl driver;

    @PostMapping("guangtuo/perimeter/alarm-report")
    public BaseResponse alarmReport(@RequestBody AlarmReportReq alarmReportReq) {
        if (alarmReportReq == null) {
            log.warn("alarmReport is null");
            return new BaseResponse(1, "fail");
        }
        List<DpData> gtDpDatas = new ArrayList<>();
        for (AlarmReportData alarm : alarmReportReq.getAlarms()) {
            gtDpDatas.add(new DpData("1", String.valueOf(alarm)));
        }
        DpModelApi api = driver.getApi();
        api.reportDpData("", gtDpDatas);
        return new BaseResponse(0, "success");
    }

    @PostMapping("guangtuo/perimeter/status-report")
    public BaseResponse statusReport(@RequestBody StatusReportReq statusReportReq) {
        if (statusReportReq == null) {
            log.warn("statusReport is null");
            return new BaseResponse(1, "fail");
        }
        List<DpData> gtDpDatas = new ArrayList<>();
        DpModelApi api = driver.getApi();
        for (StatusReportData status : statusReportReq.getStatuses()) {
               if (status.getStatustype()==6){
                   if (status.getStatusvalue()==0){ //撤防

                   } else if (status.getStatusvalue()==1){//布防

                   }
               }
               if (status.getStatusvalue()==1){
                   if (status.getStatusvalue()==0){//离线

                   }else if (status.getStatusvalue()==1){//在线

                   }
               }
        }
        return null;
    }
}
