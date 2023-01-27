package com.vista.accouting.util.log;

import com.tosanboom.model.boom.core.common.RequestStatus;
import com.tosanboom.service.aspect.TosanBoomRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class InsertCustomLog {
    private static final Logger LOGGER = LoggerFactory.getLogger(InsertCustomLog.class);

    public static void insertCustomLogInFile(TosanBoomRegistry tbRegistry, long duration,
            Long backendDuration, RequestStatus status,
            String backendStatusCode, String statusCode) {
        String swiftCode = "-";
        if (tbRegistry.getBank() != null) {
            swiftCode = (tbRegistry.getBank().getSwiftCode());
        }
        String requestStatus = "Fail";
        if (RequestStatus.SUCCESSFUL.equals(status)) {
            requestStatus = "Success";
        }
        LOGGER.info(swiftCode + "\t" +
                tbRegistry.getApp().getId() + "\t" +
                tbRegistry.getUserName() + "\t" +
                tbRegistry.getService().getId() + "\t" +
                backendDuration + "\t" +
                duration + "\t" +
                tbRegistry.getIp() + "\t" +
                tbRegistry.getClientIpAddress() + "\t" +
                requestStatus + "\t" +
                backendStatusCode + "\t" +
                statusCode
        );
    }
}
