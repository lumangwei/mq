package guangtuo;

import com.tuya.could.tedge.sdk.DriverClient;
import com.tuya.could.tedge.sdk.domain.driver.DpDriver;

public class Main {
    public static void main(String[] args) {
        String configPath ="";
        DriverClient driverClient = null;
        try {
            driverClient = new DriverClient(configPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //dp模型
        if (driverClient.isDpModel()) {
            //将实现的回调接口 DpModelGatewayCallback 的对象设置进去
            DpDriver driver = driverClient.DpModel()
                    .setGatewayCallback(new DpDriverImpl())
                    .start();

        }

    }

}
