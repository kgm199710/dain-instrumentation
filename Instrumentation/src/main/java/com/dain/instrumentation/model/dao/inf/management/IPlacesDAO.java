package com.dain.instrumentation.model.dao.inf.management;

import java.util.ArrayList;

import com.dain.instrumentation.model.vo.PlacesVO;

public interface IPlacesDAO {

	ArrayList<PlacesVO> selectNeed(String pl);

}
