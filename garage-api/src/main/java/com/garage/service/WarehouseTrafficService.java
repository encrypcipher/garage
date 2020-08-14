package com.garage.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.garage.dao.IWarehouseTrafficDao;
import com.garage.document.WearhouseTraffic;
import com.garage.exception.GarageApiException;
import com.garage.model.TrafficType;
import com.garage.model.WarehouseTrafficReq;

import reactor.core.publisher.Mono;

@Service
public class WarehouseTrafficService implements IWarehouseTrafficService{
	
	@Autowired
	private IWarehouseTrafficDao warehouseTrafficDao;
	private static final int COUNT_ONE = 1; 
	private static final int COUNT_ZERO = 0; 
	
	@Override
	public Mono<WearhouseTraffic> increaseCounter(TrafficType trafficType) {
		Optional<WearhouseTraffic> trafficOptional = warehouseTrafficDao.findByTrafficType(trafficType);
	    if(trafficOptional.isPresent()){
	    	WearhouseTraffic wearhouseTraffic = trafficOptional.get();
	    	wearhouseTraffic.setCount((wearhouseTraffic.getCount()+1));
	    	return warehouseTrafficDao.save(wearhouseTraffic);
	    }else{
	    	WearhouseTraffic w=new WearhouseTraffic();
	    	w.setTrafficType(trafficType);
	    	w.setCount(COUNT_ONE);
	    	return warehouseTrafficDao.save(w);
	    }
	}
	
	public int claculateCount(WarehouseTrafficReq warehouseTrafficReq) {
		
		switch (warehouseTrafficReq.getTrafficCountType()) {
		case MIN:
			return getMinCount(warehouseTrafficReq.getTrafficType());
		case MAX:
			return getMaxCount(warehouseTrafficReq.getTrafficType());
		case AVERAGE:
			return getAvgCount(warehouseTrafficReq.getTrafficType());
		default:
			throw new GarageApiException(HttpStatus.BAD_REQUEST.toString());
		}
	  
	}
	
	private int getMinCount(TrafficType trafficType){
		Optional<WearhouseTraffic> traffic = warehouseTrafficDao.findByTrafficType(trafficType);
		if(traffic.isPresent()) {
			return COUNT_ONE;
		}else {
			return COUNT_ZERO;
		}
	}
	
	private int getMaxCount(TrafficType trafficType){
		Optional<WearhouseTraffic> traffic = warehouseTrafficDao.findByTrafficType(trafficType);
		if(traffic.isPresent()) {
			return traffic.get().getCount();
		}else {
			return COUNT_ZERO;
		}
	}
	
	private int getAvgCount(TrafficType trafficType){
		Optional<WearhouseTraffic> traffic = warehouseTrafficDao.findByTrafficType(trafficType);
		if(traffic.isPresent()) {
			return (traffic.get().getCount())/2;
		}else {
			return COUNT_ZERO;
		}
	}

}
