package com.rekam.medis.util;

import java.util.HashMap;
import java.util.Map;

/**
 * provides utilities for generating pagination.
 *
 */
public class PaginationUtils {


	public static Map <String,Object> generatePage(Map<String,Object> model, int page, int recordCount) {
		
		return generatePage(model, page, recordCount, 30);
	
	}

	public static Map <String, Object> generatePage(Map<String, Object> model, int page, int recordCount, int maxPageSize) {
		
		if(model == null)
			model = new HashMap<String,Object>();

		if(maxPageSize <= 0)
			maxPageSize = 30;

		int pageCount = recordCount/maxPageSize;
	    
		if(recordCount%maxPageSize != 0)
	    	pageCount++;

	    int lowerLimit = page - 3;
	    
	    if(lowerLimit<0)
	    	lowerLimit=0;
	    
	    int upperLimit = lowerLimit+6;
	    
	    if(upperLimit>(pageCount-1))
	    	upperLimit=(int)(pageCount-1);
	    
	    if(pageCount>8&&(upperLimit-lowerLimit)<6)
	        lowerLimit-=(6-(upperLimit-lowerLimit));
	    
	    model.put("recordCount", recordCount);
	    
	    model.put("pageCount", pageCount);
	    
	    model.put("lowerLimit", lowerLimit);
	    
	    model.put("upperLimit", upperLimit);
	    
	    model.put("selPage", page);

	    return model;
	}

	public static Map <String, Object> generatePage(Map<String, Object> model, int page, Long recordCount, int maxPageSize) {
		
		if(model == null)
			model = new HashMap<String,Object>();

		if(maxPageSize <= 0)
			maxPageSize = 30;

		Long pageCount = recordCount/maxPageSize;
	    
		if(recordCount%maxPageSize != 0)
	    	pageCount++;

	    int lowerLimit = page - 3;
	    
	    if(lowerLimit<0)
	    	lowerLimit=0;
	    
	    int upperLimit = lowerLimit+6;
	    
	    if(upperLimit>(pageCount-1))
	    	upperLimit=(int)(pageCount-1);
	    
	    if(pageCount>8&&(upperLimit-lowerLimit)<6)
	        lowerLimit-=(6-(upperLimit-lowerLimit));
	    
	    model.put("recordCount", recordCount);
	    
	    model.put("pageCount", pageCount);
	    
	    model.put("lowerLimit", lowerLimit);
	    
	    model.put("upperLimit", upperLimit);
	    
	    model.put("selPage", page);

	    return model;
	}
}
