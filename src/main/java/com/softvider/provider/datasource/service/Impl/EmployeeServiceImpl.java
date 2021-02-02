package com.softvider.provider.datasource.service.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.softvider.model.BaseResponse;
import com.softvider.provider.datasource.entity.EmployeeEntity;
import com.softvider.provider.datasource.repository.EmployeeRepository;
import com.softvider.provider.datasource.service.EmployeeService;
import com.softvider.utils.AppUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger log = LogManager.getLogger(EmployeeServiceImpl.class);

    @Inject
    EmployeeRepository employeeRepository;

    @Override
    public BaseResponse get() {
        List<EmployeeEntity> jsonNode = employeeRepository.findAll();
        return new BaseResponse(AppUtil.convert(jsonNode, JsonNode.class));
    }

    @Override
    public BaseResponse Insert(JsonNode data) {
        EmployeeEntity entity = AppUtil.convert(data, EmployeeEntity.class);
        try {
            employeeRepository.save(entity);
            log.info("Success save user");
            return new BaseResponse();
        }catch (Exception ex){
            log.info(ex.getMessage());
            return new BaseResponse(ex.getMessage());
        }
    }
}
