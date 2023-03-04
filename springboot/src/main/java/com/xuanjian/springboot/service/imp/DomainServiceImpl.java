package com.xuanjian.springboot.service.imp;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.xuanjian.springboot.pojo.entity.DomainInform;
import com.xuanjian.springboot.repository.DomainRepository;
import com.xuanjian.springboot.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DomainServiceImpl implements DomainService {

    @Resource
    private DomainRepository domainRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public ResponseEntity<DomainInform> domainOverview(){
        DomainInform domainInform = new DomainInform();
        domainInform.setDomainNumber((int) domainRepository.count());
        domainInform.setBusinessDomainNumber(domainRepository.countByFunction(32768));
        domainInform.setAdvertiseDomainNumber(domainRepository.countByFunction(16384));
        domainInform.setResourceDomainNumber(domainRepository.countByFunction(8192));
        domainInform.setThirdPartyDomainNumber(domainRepository.countByFunction(4096));
        domainInform.setDistributionDomainNumber(domainRepository.countByFunction(2048));
        domainInform.setBroadcastDomainNumber(domainRepository.countByFunction(1024));
        domainInform.setAlternativeDomainNumber(domainRepository.countByFunction(512));
        domainInform.setGraphDividedDomainNumber(domainRepository.countByFunction(256));
        domainInform.setPageDomainNumber(domainRepository.countByFunction(128));
        domainInform.setUpdateDomainNumber(domainRepository.countByFunction(64));
        domainInform.setUserDomainNumber(domainRepository.countByFunction(32));
        return new ResponseEntity<>(domainInform, HttpStatus.OK);
    }

    @Override
    @DS("mongodb")
    public ResponseEntity<Object> getDomainsByAppId(int appId) {
        Object result;
        try {
            //1、创建 Query对象
            Query query = new Query();
            //2、设置查询条件
            String temp = ""+appId+"";
            query.addCriteria(Criteria.where("_id").is(temp));
            result = mongoTemplate.find(query, Object.class, "domains");
        } catch (Exception e) {
            String msg = "FAILED";
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
