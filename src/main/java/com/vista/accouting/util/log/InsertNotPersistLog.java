package com.vista.accouting.util.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tosanboom.dataaccess.dto.mapper.AppLogMapper;
import com.tosanboom.dataaccess.entity.AppLogs;
import com.tosanboom.model.boom.core.common.RequestStatus;
import com.tosanboom.service.aspect.TosanBoomRegistry;
import com.tosanboom.util.GeneralUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertNotPersistLog {
    private static final Logger LOGGER = LoggerFactory.getLogger(InsertNotPersistLog.class);

    public static void insertNotPersistLogInFile(TosanBoomRegistry tbRegistry, long totalDuration,
            Long backendDuration, RequestStatus requestStatus, String backendStatusCode, String statusCode,
            AppLogs appLog) throws JsonProcessingException {
        ObjectMapper mapper = GeneralUtil.createMapper();
        if (appLog == null) {
            appLog = AppLogMapper.createNewAppLog(tbRegistry, backendDuration, totalDuration, requestStatus,
                    backendStatusCode, statusCode);
        }
        LOGGER.info(mapper.writeValueAsString(appLog));
        if (!GeneralUtil.isNullOrEmpty(tbRegistry.getLogs())) {
            LOGGER.info(mapper.writeValueAsString(tbRegistry.getLogs()));
        }
        if (!GeneralUtil.isNullOrEmpty(tbRegistry.getAppLogDetailDtoList())) {
            LOGGER.info(mapper.writeValueAsString(tbRegistry.getAppLogDetailDtoList()));
        }
    }
}