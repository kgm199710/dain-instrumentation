package com.dain.instrumentation.model.dao.inf.management;

import java.util.ArrayList;

import com.dain.instrumentation.model.vo.SpotVO;


public interface ISpotDAO {
	
	ArrayList<SpotVO> selectAll();
	ArrayList<SpotVO> selectNeed();
}
