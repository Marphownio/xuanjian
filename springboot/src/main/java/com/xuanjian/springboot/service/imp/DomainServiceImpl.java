package com.xuanjian.springboot.service.imp;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.xuanjian.springboot.pojo.entity.DomainInform;
import com.xuanjian.springboot.repository.DomainRepository;
import com.xuanjian.springboot.service.DomainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DomainServiceImpl implements DomainService {

    @Resource
    private DomainRepository domainRepository;

    @Override
    public ResponseEntity<DomainInform> domainOverview(){
        DomainInform domainInform = new DomainInform();
        domainInform.setDomainNumber((int) domainRepository.count());
        domainInform.setBusinessDomainNumber((int) domainRepository.count());
        domainInform.setAdvertiseDomainNumber((int) domainRepository.count());
        domainInform.setResourceDomainNumber((int) domainRepository.count());
        domainInform.setThirdPartyDomainNumber((int) domainRepository.count());
        domainInform.setDistributionDomainNumber((int) domainRepository.count());
        domainInform.setBroadcastDomainNumber((int) domainRepository.count());
        domainInform.setAlternativeDomainNumber((int) domainRepository.count());
        domainInform.setGraphDividedDomainNumber((int) domainRepository.count());
        domainInform.setPageDomainNumber((int) domainRepository.count());
        domainInform.setUpdateDomainNumber((int) domainRepository.count());
        domainInform.setUserDomainNumber((int) domainRepository.count());
        return new ResponseEntity<>(domainInform, HttpStatus.OK);
    }

}
